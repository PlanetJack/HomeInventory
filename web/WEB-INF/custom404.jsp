<%-- 
    Document   : custom404
    Created on : Aug 19, 2023, 10:30:16 PM
    Author     : Kihyun Kim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Page Not Found</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                text-align: center;
                padding: 50px;
            }

            h1 {
                font-size: 48px;
                color: #333;
            }

            p {
                font-size: 20px;
                color: #666;
            }

            a {
                color: #007BFF;
                text-decoration: none;
            }

            a:hover {
                text-decoration: underline;
            }
        </style>
    </head>
    <body>
        <h1>Oops!</h1>
        <p>WE CANNOT FIND THE PAGE YOU ARE LOOKING FOR.</p>
        <br>
        <p><a href="<%= request.getContextPath() %>/home">Return to Home</a></p>
    </body>
</html>
