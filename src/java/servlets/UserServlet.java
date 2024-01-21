package servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Role;
import models.User;
import services.RoleService;
import services.UserService;

/**
 *
 * @author Kihyun Kim
 */
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        UserService userService = new UserService();
        RoleService roleService = new RoleService();

        try {
            User user = userService.get(email);
            request.setAttribute("selectedUser", user);

            List<Role> roles = roleService.getAll();
            request.setAttribute("roles", roles);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Error retrieving user details.");
            getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
            return;
        }

        getServletContext().getRequestDispatcher("/WEB-INF/editUser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmpassword");
        String roleParam = request.getParameter("role");
        int roleId = 0;

        // Debug "Edit drop box"
        System.out.println("Role parameter value: " + roleParam);

        if (roleParam != null && !roleParam.isEmpty()) {
            try {
                roleId = Integer.parseInt(roleParam);
            } catch (NumberFormatException e) {
                request.setAttribute("message", "Invalid role value!");
                doGet(request, response);
                return;
            }
        } else if (roleParam == null || roleParam.isEmpty()) {
            doGet(request, response);
            return;
        }

        if (!password.equals(confirmPassword)) {
            request.setAttribute("message", "Passwords do not match!");
            doGet(request, response);
            return;
        }

        UserService userService = new UserService();
        try {
            User user = userService.get(email);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPassword(password);
            user.getRole().setRoleId(roleId);

            userService.update(email, user.isActive(), firstName, lastName, password, roleId);

            request.setAttribute("message", "User updated successfully!");

            // Fetch the updated list of users
            List<User> users = userService.getAll();
            request.setAttribute("users", users);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Error updating user. Please try again.");
            doGet(request, response);
            return;
        }

        getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
    }
}