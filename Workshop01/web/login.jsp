<%-- 
    Document   : login
    Created on : Feb 28, 2025, 1:46:28 AM
    Author     : Huy
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(to right, #ff758c, #ff7eb3);
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .login-container {
            background: white;
            padding: 20px;
            box-shadow: 0px 0px 15px rgba(0, 0, 0, 0.2);
            border-radius: 10px;
            text-align: left;
            width: 350px;
        }
        h2 {
            text-align: center;
            color: #333;
        }
        .form-group {
            display: flex;
            flex-direction: column;
            margin: 10px 0;
        }
        label {
            font-weight: bold;
            margin-bottom: 5px;
        }
        input {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        button {
            width: 100%;
            background: #ff4b5c;
            color: white;
            font-size: 16px;
            padding: 10px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            margin-top: 15px;
        }
        button:hover {
            background: #e63950;
        }
        .error-message {
            color: red;
            margin-bottom: 10px;
            text-align: center;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <h2>Login</h2>
        
        <% String message = (String) request.getAttribute("message");
           if (message != null) { %>
           <p class="error-message"><%= message %></p>
        <% } %>
        
        <form action="MainController" method="post">
            <input type="hidden" name="action" value="login">
            
            <div class="form-group">
                <label>Username:</label>
                <input type="text" name="username" required>
            </div>
            
            <div class="form-group">
                <label>Password:</label>
                <input type="password" name="password" required>
            </div>
            
            <button type="submit">Login</button>
        </form>
    </div>
</body>
</html>
