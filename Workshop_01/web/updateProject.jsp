<%-- 
    Document   : udateProject
    Created on : Mar 9, 2025, 3:08:07 PM
    Author     : anhqu
--%>

<%@page import="utils.AuthUtils"%>
<%@page import="dto.UserDTO"%>
<%@page import="dto.ProjectDTO"%>
<%@page import="dao.ProjectDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    if (session == null || session.getAttribute("user") == null) {
        response.sendRedirect("login.jsp"); // Chuyển về login nếu không có session
        return;
    }

    int projectId = Integer.parseInt(request.getParameter("project_id"));
    ProjectDAO projectDAO = new ProjectDAO();
    ProjectDTO project = projectDAO.readById(projectId);

    if (project == null) {
%>
<p style="color: red;">Project not found!</p>
<%
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Update Project</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            text-align: center;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 50%;
            margin: auto;
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0px 0px 10px 0px #aaa;
            margin-top: 50px;
        }
        input, select, button {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        button {
            background-color: #007BFF;
            color: white;
            border: none;
            cursor: pointer;
        }
        button:hover {
            background-color: #0056b3;
        }
        .error {
            color: red;
        }
    </style>
</head>
<body>
    <div class="container">
        <% if (AuthUtils.isLoggedIn(session)) {
                UserDTO user = AuthUtils.getUser(session);
                if (AuthUtils.isAdmin(session)) {
        %>
        <h2>Update Project Status</h2>
        <form action="MainController" method="post">
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="project_id" value="<%= project.getProjectId()%>">
            <label>Project Name: <%= project.getProjectName()%></label><br>
            <label>Status:
                <select name="status">
                    <option value="Ideation" <%= "Ideation".equals(project.getStatus()) ? "selected" : ""%>>Ideation</option>
                    <option value="Development" <%= "Development".equals(project.getStatus()) ? "selected" : ""%>>Development</option>
                    <option value="Launch" <%= "Launch".equals(project.getStatus()) ? "selected" : ""%>>Launch</option>
                    <option value="Scaling" <%= "Scaling".equals(project.getStatus()) ? "selected" : ""%>>Scaling</option>
                </select>
            </label><br>
            <button type="submit">Update</button>
        </form>
        <form action="MainController" method="post">
            <input type="hidden" name="action" value="viewAllProjects">
            <button type="submit">Comeback</button>
        </form>
        <%} else {%>
        <div>
            <h1>403 Error</h1>
            <p>You do not have permission to access this content!</p>
            <a href="projects.jsp">Back to Project List</a>
        </div>
        <%}
        } else {%>
        <div>
            <h1>Access Denied</h1>
            <p>Please log in to access this page.</p>
            <a href="login.jsp">Go to Login</a>
        </div>
        <%}%>
    </div>
</body>
</html>


