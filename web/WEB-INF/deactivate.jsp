<%-- 
    Document   : deactivate
    Created on : Aug 19, 2023, 10:30:38 PM
    Author     : Kihyun Kim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Deactivate Account</title>
    </head>
    <body>
        <h1>Deactivate Account</h1>
        <br>
        <p>Are you sure you want to deactivate your account?</p>
        <form action="deactivate" method="post">
            <input type="submit" value="Yes, Deactivate">
        </form>
        <br>
        <a href="home">Cancel</a>
    </body>
</html>
