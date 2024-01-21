<%-- 
    Document   : categories
    Created on : Aug 19, 2023, 10:30:07 PM
    Author     : Kihyun Kim
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Manage Categories</title>
    </head>
    <body>
        <h1>Manage Categories</h1>
        <br>
        <form action="category" method="post">
            <label for="categoryName">Enter the name of the category to add:</label>
            <input type="text" id="categoryName" name="categoryName" required>
            <br>
            <input type="hidden" name="action" value="add">
            <input type="submit" value="Add Category">
        </form>
        <br><br>

        <table border="1">
            <tr>
                <th>Category Name</th>
                <th>Action</th>
            </tr>
            <c:forEach var="category" items="${categories}">
                <tr>
                    <td>
                        <c:choose>
                            <c:when test="${category.id == editCategoryId}">
                                <form action="category" method="post">
                                    <input type="text" name="categoryName" value="${category.name}">
                                    <input type="hidden" name="categoryId" value="${category.id}">
                                    <input type="hidden" name="action" value="edit">
                                    <input type="submit" value="Save">
                                </form>
                            </c:when>
                            <c:otherwise>
                                ${category.name}
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <form action="category" method="post">
                            <input type="hidden" name="categoryId" value="${category.id}">
                            <input type="hidden" name="action" value="setEditMode">
                            <input type="submit" value="Edit">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br><br>
        <a href="home">Back to Home</a>
    </body>
</html>
