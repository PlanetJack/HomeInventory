<%-- 
    Document   : error
    Created on : Aug 19, 2023, 10:31:15 PM
    Author     : Kihyun Kim
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Error Occurred</title>
    </head>
    <body>
        <h1>An Error Occurred</h1>
        <p>${errorMessage}</p>
        <br>
        <a href="/home">Return to Home</a>
    </body>
</html>