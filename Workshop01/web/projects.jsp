<%-- 
    Document   : projects
    Created on : Feb 28, 2025, 1:23:59 AM
    Author     : Huy
--%>

<%@page import="java.util.List"%>
<%@page import="dto.ProjectDTO"%>
<%@page import="dao.ProjectDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Projects</title>
</head>
<body>
    <h2>Startup Projects</h2>
    <table border="1">
        <tr>
            <th>Project ID</th>
            <th>Project Name</th>
            <th>Description</th>
            <th>Status</th>
            <th>Estimated Launch</th>
            <th>Actions</th>
        </tr>
        <% 
            ProjectDAO projectDAO = new ProjectDAO();
            List<ProjectDTO> projects = projectDAO.readAll();
            for (ProjectDTO project : projects) {
        %>
        <tr>
            <td><%= project.getProjectId() %></td>
            <td><%= project.getProjectName() %></td>
            <td><%= project.getDescription() %></td>
            <td><%= project.getStatus() %></td>
            <td><%= project.getEstimatedLaunch() %></td>
            <td>
                <a href="updateProject.jsp?project_id=<%= project.getProjectId() %>">Update</a>
            </td>
        </tr>
        <% } %>
    </table>
</body>
</html>
