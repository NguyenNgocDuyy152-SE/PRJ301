<%-- 
    Document   : updateProject
    Created on : Feb 28, 2025, 1:25:30 AM
    Author     : Huy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="dao.ProjectDAO, dto.ProjectDTO" %>
<%
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
</head>
<body>
    <h2>Update Project Status</h2>
    <form action="MainController" method="post">
        <input type="hidden" name="action" value="updateStatus">
        <input type="hidden" name="project_id" value="<%= project.getProjectId() %>">
        <label>Project Name: <%= project.getProjectName() %></label><br>
        <label>Status:
            <select name="status">
                <option value="Ideation" <%= "Ideation".equals(project.getStatus()) ? "selected" : "" %>>Ideation</option>
                <option value="Development" <%= "Development".equals(project.getStatus()) ? "selected" : "" %>>Development</option>
                <option value="Launch" <%= "Launch".equals(project.getStatus()) ? "selected" : "" %>>Launch</option>
                <option value="Scaling" <%= "Scaling".equals(project.getStatus()) ? "selected" : "" %>>Scaling</option>
            </select>
        </label><br>
        <button type="submit">Update</button>
    </form>
</body>
</html>