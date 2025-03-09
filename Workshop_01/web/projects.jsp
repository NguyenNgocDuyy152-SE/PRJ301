<%@page import="dto.UserDTO"%>
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
        <%
            UserDTO user = (UserDTO) session.getAttribute("user");
            if (user == null) {
                response.sendRedirect("login.jsp");
                return;
            }
            String role = user.getRole();
            String message = (String) request.getAttribute("message");
            String searchTerm = (String) request.getAttribute("searchTerm");
            List<ProjectDTO> projects = (List<ProjectDTO>) request.getAttribute("projectList");

            // Nếu không có kết quả tìm kiếm, hiển thị tất cả dự án
            if (projects == null) {
                ProjectDAO projectDAO = new ProjectDAO();
                projects = projectDAO.readAll();
            }
        %>
        <!DOCTYPE html>
    <html>
        <head>
            <title>Projects</title>
            <style>
                body {
                    font-family: Arial, sans-serif;
                    margin: 20px;
                    background-color: #f4f4f4;
                    text-align: center;
                }
                h2 {
                    color: #333;
                }
                .container {
                    width: 80%;
                    margin: auto;
                    background: white;
                    padding: 20px;
                    box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
                    border-radius: 10px;
                }
                table {
                    width: 100%;
                    border-collapse: collapse;
                    margin-top: 20px;
                }
                table, th, td {
                    border: 1px solid #ddd;
                }
                th, td {
                    padding: 10px;
                    text-align: left;
                }
                th {
                    background-color: #007BFF;
                    color: white;
                }
                tr:nth-child(even) {
                    background-color: #f2f2f2;
                }
                button, a {
                    display: inline-block;
                    background-color: #007BFF;
                    color: white;
                    padding: 10px 20px;
                    text-decoration: none;
                    border: none;
                    border-radius: 5px;
                    cursor: pointer;
                    margin: 10px 5px;
                }
                button:hover, a:hover {
                    background-color: #0056b3;
                }
                input[type="text"] {
                    padding: 8px;
                    width: 250px;
                    border: 1px solid #ccc;
                    border-radius: 5px;
                }
            </style>
        </head>
        <body>
            <div class="container">
                <h2>Welcome, <%= user.getName()%>!</h2>
                <p>You are logged in as: <%= role%></p>

                <% if (message != null) {%>
                <p style="color: red;"><%= message%></p>
                <% } %>

                <h2>All Projects</h2>
                <% if ("Founder".equals(role)) { %>
                <a href="createProject.jsp">Create Project</a>
                <% } %>

                <% if ("Founder".equals(role)) {%>
                <form action="MainController" method="post">
                    <input type="hidden" name="action" value="search">
                    <input type="text" name="searchTerm" placeholder="Search projects..." value="<%= (searchTerm != null) ? searchTerm : ""%>">
                    <button type="submit">Search</button>
                </form>
                <% } %>

                <table>
                    <tr>
                        <th>Project Name</th>
                        <th>Description</th>
                        <th>Status</th>
                        <th>Estimated Launch</th>
                            <% if ("Founder".equals(role)) {%>                      
                        <th>Action</th>
                            <% } %>

                    </tr>
                    <% if (projects.isEmpty()) { %>
                    <tr>
                        <td colspan="5" style="text-align: center; color: red;">No projects found.</td>
                    </tr>
                    <% } else {
                        for (ProjectDTO project : projects) {
                    %>
                    <tr>
                        <td><%= project.getProjectName()%></td>
                        <td><%= project.getDescription()%></td>
                        <td><%= project.getStatus()%></td>
                        <td><%= project.getEstimatedLaunch()%></td>
                        <% if ("Founder".equals(role)) {%>
                    <form action="MainController" method="post">
                        <td>
                            <a href="updateProject.jsp?project_id=<%= project.getProjectId()%>">Update</a>
                        </td>
                    </form>
                    <% } %>
                    </tr>
                    <% }
                        }%>
                </table>

                <form action="MainController" method="post">
                    <input type="hidden" name="action" value="logout">
                    <button type="submit">Logout</button>
                </form>
                <% if (searchTerm != null && !searchTerm.isEmpty()) { %>
                <form action="MainController" method="post">
                    <input type="hidden" name="action" value="viewAllProjects">
                    <button type="submit">Comeback</button>
                </form>
                <% }%>
            </div>
        </body>
    </html>

