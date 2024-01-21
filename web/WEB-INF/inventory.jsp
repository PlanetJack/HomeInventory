<%-- 
    Document   : inventory
    Created on : Aug 19, 2023, 10:31:29 PM
    Author     : Kihyun Kim
--%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inventory Page</title>

        <style>
            table {
                width: 100%;
                border-collapse: collapse; 
            }

            table, th, td {
                border: 1px solid #dddddd;
            }

            th, td {
                text-align: left;
                padding: 8px;
            }

            tr:nth-child(even) {
                background-color: #f2f2f2;
            }

            th {
                background-color: #4CAF50;
                color: white;
            }

            /* Delete button styling */
            input[type="submit"] {
                background-color: red;
                color: white;
                border: none;
                padding: 5px 10px;
                cursor: pointer;
                border-radius: 3px;
                transition: background-color 0.3s;
            }

            input[type="submit"]:hover {
                background-color: darkred;
            }
        </style>

    </head>
    <body>
        <h1>Inventory Items</h1>

        <h3>First Name: ${user.firstName} 
            <br>
            Last Name: ${user.lastName} </h3>
        
        <c:choose>
            <c:when test="${empty items}">
                <p>No Inventory Items</p>
            </c:when>
            <c:otherwise>
                <table border="1">
                    <tr>
                        <td>Category</td>
                        <td>Item name</td>
                        <td>Price</td>
                        <td>Edit</td>
                        <td>Delete</td>
                    </tr>
                    <c:forEach items="${items}" var="item">
                        <tr>
                            <td>${item.category.name}</td>
                            <td>${item.itemName}</td>
                            <td>${item.price}</td>
                            <td><a href="/inventory?action=edit&itemId=${item.itemId}">Edit</a></td>
                            <td>
                                <form action="/inventory" method="post" style="display: inline;">
                                    <input type="hidden" name="action" value="delete">
                                    <input type="hidden" name="itemId" value="${item.itemId}">
                                    <input type="submit" value="Delete" 
                                           style="background-color: red; color: white; border: none; padding: 2px 5px; cursor: pointer;">
                                </form>
                            </td>          
                        </tr>   
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>
        <br>
        <a href="/inventory?action=add">Add Item</a>
        <br><br>
        <a href="/edit">Go to Manage User Account</a>
    </body>
</html>