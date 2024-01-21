<%-- 
    Document   : register
    Created on : Aug 19, 2023, 10:31:45 PM
    Author     : Kihyun Kim
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Register</title>
    </head>
    <body>
        <h1>Register New User</h1>
        <p>${message}</p>
        
        <form action="registration" method="post">
            Email: <input type="text" name="email" required><br><br>
            Password: <input type="password" name="password" required><br><br>
            Confirm Password: <input type="password" name="confirmpassword" required><br><br>
            First Name: <input type="text" name="firstName" required><br><br>
            Last Name: <input type="text" name="lastName" required><br><br>
            <input type="submit" value="Register">
        </form>
        <br>
        <a href="/login">Already have an account? Login here</a>
    </body>
</html>