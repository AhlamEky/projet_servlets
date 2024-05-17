<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Hospital Management System - Login</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            align-items: center;
            justify-content: center;
            min-height: 100vh;
            background-color: #f2f2f2;
        }

        .login-form {
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .login-form h2 {
            text-align: center;
            margin-bottom: 20px;
        }

        .login-form label {
            display: block;
            margin-bottom: 5px;
        }

        .login-form input[type="text"],
        .login-form input[type="password"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 3px;
            box-sizing: border-box;
            margin-bottom: 10px;
        }

        .login-form button {
            background-color: #007bff;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 3px;
            cursor: pointer;
        }

        .login-form button:hover {
            background-color: #0056b3;
        }

        .login-form p {
            text-align: center;
            margin-top: 10px;
        }

        .login-form a {
            color: #007bff;
            text-decoration: none;
        }
    </style>
</head>
<body>
    <div class="login-form">
        <h2>Hospital Management System</h2>
        <form action="connect" method="post">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" placeholder="Enter your username">

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" placeholder="Enter your password">

            <button type="submit">Sign In</button>

            <p>Don't have an account? <a href="#">Sign Up</a></p>
        </form>
    </div>
</body>
</html>
