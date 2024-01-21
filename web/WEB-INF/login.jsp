<%-- 
    Document   : login
    Created on : Aug 19, 2023, 10:31:38 PM
    Edited on  : Jan 05, 2023, 11:57:30 AM
    Author     : Kihyun Kim
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HOME Inventory </title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-image: url('inventory.png');
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
            text-align: center;
            padding: 50px;
            
        }

        h1, h2 {
            color: #333;
        }

          form {
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            display: inline-block;
            margin-top: 20px;
            text-align: left; /* 폼 내부 텍스트를 왼쪽 정렬 */
            width: 300px; /* 폼의 너비 지정 */
        }

        label {
            margin-left: 10px; /* 라벨의 왼쪽 여백 */
        }

        input[type=email], input[type=password] {
            width: 94%; /* 입력 필드 너비 조정 */
            padding: 10px;
            margin: 8px 10px; /* 상하 여백 및 좌우 여백 */
            display: inline-block;
            border: 1px solid #ccc;
            box-sizing: border-box;
            border-radius: 4px;
        }

        input[type=submit] {
            background-color: #4CAF50;
            color: white;
            padding: 14px 20px;
            margin: 8px 10px;
            border: none;
            cursor: pointer;
            border-radius: 4px;
            width: 94%;
        }

        input[type=submit]:hover {
            background-color: #45a049;
        }

        a {
            color: black;
            display: block;
            margin-top: 20px;
            text-align: center; /* 링크를 중앙에 정렬 */
        }
    </style>
</head>
<body>
    <h1>Welcome to HOME Inventory</h1>
    <form action="login" method="post">
        <h2>Login</h2>
        <p>${message}</p>
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>
        <br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>
        <br><br>
        <input type="submit" value="Login">
    </form>
    <a href="/registration">Registration</a>
</body>
</html>