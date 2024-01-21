package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import services.UserService;

/**
 *
 * @author Kihyun Kim
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (request.getParameter("login") != null) {
            session.invalidate();
            request.setAttribute("message", "You have successfully logged out");
        }
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String inputEmail = request.getParameter("email");
        String inputPassword = request.getParameter("password");

        if (inputEmail == null || inputEmail.trim().isEmpty() || inputPassword == null || inputPassword.trim().isEmpty()) {
            request.setAttribute("message", "Please enter email and password");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }

        UserService us = new UserService();
        try {
            User user = us.authenticate(inputEmail, inputPassword);

            if (user != null) {
                if (user.isActive()) {
                    // Redirect to HomeServlet after successful login
                    session.setAttribute("user", user);
                    response.sendRedirect("home");
                } else {
                    // Case where the user is authenticated but the account is deactivated
                    request.setAttribute("message", "This account has been deactivated");
                    getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                }
            } else {
                // Case where the user authentication failed
                request.setAttribute("message", "Invalid email or password (The authentication failed)");
                getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            request.setAttribute("message", "An unexpected error occurred.");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
    }
}
