<%-- 
    Document   : home
    Created on : Aug 19, 2023, 10:31:20 PM
    Author     : Kihyun Kim
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home page </title>
    </head>
    <body>
        <p>${message}</p>
        <h1>Home Page</h1> 

        <h3>Hello!
            <br>
            First Name: ${user.firstName}
            <br>
            Last Name: ${user.lastName} </h3>

        <p>${message_edit}</p>

        <br>

        <!-- Display "View Category" btn for admin users -->
        <c:if test="${user.role.roleId == 1}">
            <a href="/category">Manage Category</a><br><br>
        </c:if>   
        <br>

        <a href="edit">Manage User Account (Edit)</a><br><br>
        <br>
        <c:if test="${user.role.roleId == 1}">
            <a href="/admin">View all account</a><br><br>
        </c:if>

        <p><a href="login?login">Log out</a></p>
    </body>
</html>