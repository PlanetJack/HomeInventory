<%-- 
    Document   : editUser
    Created on : Aug 20, 2023, 6:47:11 AM
    Author     : Kihyun Kim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit User</title>
    </head>
    <body>
        <p>${message}</p>
        <h1>Edit User</h1>

        <form action="user" method="post">
            Email: <input type="email" name="email" value="${selectedUser.email}" readonly/><br>
            First Name: <input type="text" name="firstName" value="${selectedUser.firstName}"/><br>
            Last Name: <input type="text" name="lastName" value="${selectedUser.lastName}"/><br>
            Password: <input type="password" name="password" /><br>
            Confirm Password: <input type="password" name="confirmpassword" /><br>
            <br>
            
            <label for="role"> Role: </label>
            <select id="role" name="role">
                <option value="2" ${selectedUser.role.roleId == 2 ? 'selected' : ''}>Regular User</option>
                <option value="1" ${selectedUser.role.roleId == 1 ? 'selected' : ''}>Admin User</option>       
            </select>
            <br><br>
            
            <input type="hidden" name="action" value="selectedUser">
            <input type="submit" value="Update User">
        </form>
            
            <br>
        <a href="admin">Back to Manage Users</a>
    </body>
</html>