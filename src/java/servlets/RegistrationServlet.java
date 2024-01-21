package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Role;
import models.User;
import services.UserService;

/**
 *
 * @author Kihyun Kim
 */
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmpassword = request.getParameter("confirmpassword");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        if (email == null || email.isEmpty() || password == null || password.isEmpty() || confirmpassword == null || confirmpassword.isEmpty() || firstName == null || firstName.isEmpty() || lastName == null || lastName.isEmpty()) {
            request.setAttribute("message", "Please fill in all fields");
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            return;
        }

        if (!password.equals(confirmpassword)) {
            request.setAttribute("message", "Passwords do not match");
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            return;
        }

        Role userRole = new Role(2, "regular user");
        User user = new User(email, true, firstName, lastName, password, userRole);

        UserService us = new UserService();

        try {
            us.insert(user);
            request.setAttribute("message", "Registration Successful");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("message", "Email already exists");
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
        }
    }
}
