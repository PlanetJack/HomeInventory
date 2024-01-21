package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Role;
import models.User;
import services.UserService;

public class EditServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        request.setAttribute("user", user);
        getServletContext().getRequestDispatcher("/WEB-INF/edit.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User loggedInUser = (User) session.getAttribute("user");
        String email = loggedInUser.getEmail();
        int currentRoleId = loggedInUser.getRole().getRoleId();

        String action = request.getParameter("action");
        boolean active = false;

        if (action == null) {
            getServletContext().getRequestDispatcher("/WEB-INF/edit.jsp").forward(request, response);
            return;
        }
        UserService us = new UserService();

        if (action.equals("update")) {
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String password = request.getParameter("password");
            String confirmpassword = request.getParameter("confirmpassword");

            if (!password.equals(confirmpassword)) {
                request.setAttribute("message", "Passwords do not match");
                getServletContext().getRequestDispatcher("/WEB-INF/edit.jsp").forward(request, response);
                return;
            }

            active = true;

            try {
                Role userRole = us.getRoleById(currentRoleId);
                User user = new User(email, active, firstName, lastName, password, userRole);
                us.update(email, active, firstName, lastName, password, currentRoleId);
                request.setAttribute("message", "Update Successful");
                getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
            } catch (Exception e) {
                request.setAttribute("message", "Update failed");
                getServletContext().getRequestDispatcher("/WEB-INF/edit.jsp").forward(request, response);
            }
        }
    }
}
