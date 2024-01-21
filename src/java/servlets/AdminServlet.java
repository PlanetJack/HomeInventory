package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.User;
import services.UserService;
import java.util.List;
import models.Role;

/**
 *
 * @author Kihyun Kim
 */
public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("showAddUserForm".equals(action)) {
            getServletContext().getRequestDispatcher("/WEB-INF/addUser.jsp").forward(request, response);
            return;
        }

        try {
            UserService userService = new UserService();
            List<User> users = userService.getAll();
            request.setAttribute("users", users);
            getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String email = request.getParameter("email");
        UserService userService = new UserService();

        try {
            if ("deactivate".equals(action)) {
                userService.deactivate(email);
            } else if ("activate".equals(action)) {
                userService.activate(email);
            } else if ("delete".equals(action)) {
                // Delete all associated items of the user (if any)
                // This logic can be implemented in the UserService if needed
                userService.delete(email);
            } else if ("createUser".equals(action)) {
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                String password = request.getParameter("password");
                int roleId = Integer.parseInt(request.getParameter("role"));

                Role role = userService.getRoleById(roleId);
                User newUser = new User(email, true, firstName, lastName, password, role);
                userService.insert(newUser);
            }

            // Redirect back to the admin page to refresh the user list
            response.sendRedirect("admin");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}