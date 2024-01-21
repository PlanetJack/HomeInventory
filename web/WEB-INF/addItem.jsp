<%-- 
    Document   : addItem
    Created on : Aug 20, 2023, 12:31:48 AM
    Author     : Kihyun Kim
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inventory Page</title>
    </head>
    <body>
        <c:if test="${not empty errorMessage}">
            <p style="color: red;">${errorMessage}</p>
        </c:if>


        <h1>Add a new item</h1>

        <form action="inventory" method="post">
            Category: 
            <select name="category">
                <c:forEach items="${categories}" var="category">
                    <option value="${category.id}">${category.name}</option>
                </c:forEach>
            </select><br><br>

            Name: <input type="text" name="name" required><br><br>
            Price: <input type="text" name="price" required><br><br>

            
            <input type="hidden" name="action" value="add">
            <input type="submit" value="Add Item">
        </form>

        <br>
        <a href="home">Go back to Home</a>
    </body>
</html>
