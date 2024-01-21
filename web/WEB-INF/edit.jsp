<%-- 
    Document   : edit
    Created on : Aug 19, 2023, 10:30:45 PM
    Author     : Kihyun Kim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <title>Manage User Account</title>
    </head>
    <body>
        <h1>Manage User Account</h1>
        <p>${message}</p>

        <form action="edit" method="post">
            <input type="hidden" name="action" value="update">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" value="${user.email}" required readonly>
            <br>
            <label for="firstName">First Name:</label>
            <input type="text" id="firstName" name="firstName" value="${user.firstName}" required>
            <br>
            <label for="lastName">Last Name:</label>
            <input type="text" id="lastName" name="lastName" value="${user.lastName}" required>
            <br>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required> 
            <br>
            <label for="confirmpassword">Confirm Password:</label>
            <input type="password" id="confirmpassword" name="confirmpassword" required> 
            <br>
            <br>
            <input type="submit" value="Update">
        </form>
        <br>
        <br>

        <a href="/inventory">Manage Inventory</a>
        <br><br>
        <br>

        <br>
        <a href="deactivate">Deactivate the account</a><br><br><br>
        
        <br>
        <a href="home">Back to Home</a>
        <p><a href="login?login">Log out</a></p>

    </body>
</html>