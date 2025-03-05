<%-- 
    Document   : searchProject
    Created on : Feb 28, 2025, 1:34:55 AM
    Author     : Huy
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, dto.ProjectDTO" %>
<%@ page import="dao.ProjectDAO" %>
<html>
<head>
    <title>Search Projects</title>
</head>
<body>
    <h2>Search Projects</h2>
    
    <form action="searchProject.jsp" method="get">
        <input type="text" name="searchTerm" placeholder="Enter project name" required>
        <button type="submit">Search</button>
    </form>
    
    <% 
        String searchTerm = request.getParameter("searchTerm");
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            ProjectDAO projectDAO = new ProjectDAO();
            List<ProjectDTO> searchResults = projectDAO.search(searchTerm);
            if (searchResults.isEmpty()) {
    %>
                <p>No projects found matching "<%= searchTerm %>".</p>
    <%      } else { %>
                <table border="1">
                    <tr>
                        <th>Project ID</th>
                        <th>Project Name</th>
                        <th>Description</th>
                        <th>Status</th>
                        <th>Estimated Launch</th>
                    </tr>
                    <% for (ProjectDTO project : searchResults) { %>
                    <tr>
                        <td><%= project.getProjectId() %></td>
                        <td><%= project.getProjectName() %></td>
                        <td><%= project.getDescription() %></td>
                        <td><%= project.getStatus() %></td>
                        <td><%= project.getEstimatedLaunch() %></td>
                    </tr>
                    <% } %>
                </table>
    <%      }
        } %>
    
    <br>
    <a href="dashboard.jsp">Back to Dashboard</a>
</body>
</html>
