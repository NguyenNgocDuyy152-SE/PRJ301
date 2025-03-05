<%-- 
    Document   : dashboard
    Created on : Feb 28, 2025, 1:22:56 AM
    Author     : Huy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="dto.UserDTO" %>
<%
    // Kiểm tra xem có user đăng nhập không
    UserDTO user = (UserDTO) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp"); // Chuyển hướng về trang login nếu chưa đăng nhập
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            text-align: center;
            margin: 0;
            padding: 0;
        }
        h2 {
            color: #333;
        }
        .container {
            width: 50%;
            margin: auto;
            background: white;
            padding: 20px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            border-radius: 5px;
        }
        input, button {
            width: 90%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        button {
            background: #007BFF;
            color: white;
            font-size: 16px;
            cursor: pointer;
        }
        button:hover {
            background: #0056b3;
        }
        a {
            text-decoration: none;
            color: #007BFF;
            font-weight: bold;
            display: inline-block;
            margin: 10px;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Welcome, <%= user.getName() %>!</h2>
        <p>You are logged in as: <strong><%= user.getRole() %></strong></p>
        
        <form action="searchProject.jsp" method="get">
            <input type="text" name="searchTerm" placeholder="Search by project name">
            <button type="submit">Search</button>
        </form>
        
        <a href="projects.jsp">View All Projects</a> |
        <a href="createProject.jsp">Create New Project</a> |
        <a href="logout.jsp">Logout</a>
    </div>
</body>
</html>