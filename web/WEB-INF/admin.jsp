<%-- 
    Document   : admin
    Created on : Aug 19, 2023, 10:29:57 PM
    Author     : Kihyun Kim
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>View All Users Account</title>
        <style>
            table {
                width: 100%;
                border-collapse: collapse;
            }
            th, td {
                border: 1px solid black;
                padding: 8px;
                text-align: left;
            }
            th {
                background-color: #f2f2f2;
            }
        </style>
    </head>
    <body>
        <p>${message}</p>
        <h1>View All Users Account</h1>
        <table>
            <tr>
                <th>Email</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Password</th>
                <th>Role</th>
                <th>Status</th>
                <th>Active/Inactive</th>
                <th colspan="2">Actions</th>

            </tr>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>${user.email}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.password}</td>
                    <td>${user.role.roleName}</td>
                    <td>${user.active ? "Active" : "Inactive"}</td>
                    <td>
                        <form action="admin" method="post">
                            <input type="hidden" name="email" value="${user.email}">
                            <c:choose>
                                <c:when test="${user.active}">
                                    <input type="hidden" name="action" value="deactivate">
                                    <input type="submit" value="Deactivate">
                                </c:when>
                                <c:otherwise>
                                    <input type="hidden" name="action" value="activate">
                                    <input type="submit" value="Activate">
                                </c:otherwise>
                            </c:choose>
                        </form>
                    </td>

                    <td>
                        <!-- Edit button -->
                        <form action="user" method="post" style="display: inline;">
                            <input type="hidden" name="email" value="${user.email}">
                            <input type="hidden" name="action" value="selectedUser">
                            <input type="submit" value="Edit">
                        </form>
                    </td>

                    <td>
                        <!-- Delete button -->
                        <form action="admin" method="post">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="email" value="${user.email}">
                            <input type="submit" value="Delete"
                                   style="background-color: red; color: white; border: none; padding: 2px 5px; cursor: pointer;">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <br>
        <!-- New User Button -->
        <a href="admin?action=showAddUserForm">Add User</a>


        <br><br>
        <a href="home">Back to Home</a>
    </body>
</html>