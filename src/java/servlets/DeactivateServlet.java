package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.UserService;
import models.User;

/**
 *
 * @author Kihyun Kim
 */
public class DeactivateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        request.setAttribute("email", user.getEmail());
        request.setAttribute("firstName", user.getFirstName());
        request.setAttribute("lastName", user.getLastName());

        getServletContext().getRequestDispatcher("/WEB-INF/deactivate.jsp").forward(request, response);
    }


     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        UserService us = new UserService();

        try {
            us.update(user.getEmail(), false, user.getFirstName(), user.getLastName(), user.getPassword(), user.getRole().getRoleId());
            request.setAttribute("message", "deactivated account");
        } catch (Exception ex) {
            request.setAttribute("message", "An error occurred while deactivating the account.");
            ex.printStackTrace();
        }
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }
}