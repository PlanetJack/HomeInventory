<%-- 
    Document   : editItem
    Created on : Aug 19, 2023, 10:30:54 PM
    Author     : Kihyun Kim
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Item</title>
    </head>
    <body>
        <h1>Edit Item</h1>
        <c:if test="${not empty errorMessage}">
            <p style="color: red;">${errorMessage}</p>
        </c:if>

        <form action="inventory" method="post">
            <input type="hidden" name="itemId" value="${selectedItem.itemId}" />
            <input type="hidden" name="action" value="edit" />

            Category:
            <select name="category">
                <c:forEach items="${categories}" var="category">
                    <option value="${category.id}" <c:if test="${category.id == selectedItem.category.id}">selected</c:if>>${category.name}</option>
                </c:forEach>
            </select>
            <br>

            Name:
            <input type="text" name="name" value="${selectedItem.itemName}" required />
            <br>

            Price:
            <input type="text" name="price" value="${selectedItem.price}" required />
            <br><br>

            <input type="submit" value="Update" />
        </form>
        <br>
        <a href="/inventory">Go to Inventory Page</a>
    </body>
</html>