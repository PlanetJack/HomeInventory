<%-- 
    Document   : addUser
    Created on : Aug 19, 2023, 10:29:50 PM
    Author     : Kihyun Kim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add New User</title>
    </head>
    <body>
        <h1>Create New User</h1>

        <form action="admin" method="post">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required><br>

            <label for="firstName">First Name:</label>
            <input type="text" id="firstName" name="firstName" required><br>

            <label for="lastName">Last Name:</label>
            <input type="text" id="lastName" name="lastName" required><br>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required><br>

            <label for="role">Role:</label>
            <select id="role" name="role">
                <option value="2" ${user.role.roleId == 2 ? 'selected' : ''}>Regular User</option>
                <option value="1" ${user.role.roleId == 1 ? 'selected' : ''}>Admin User</option>       
            </select><br>

            <input type="hidden" name="action" value="createUser">
            <input type="submit" value="Add User">
        </form>

        <a href="admin">Back to Manage Users</a>
    </body>
</html>


