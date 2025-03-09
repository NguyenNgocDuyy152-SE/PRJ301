<%@page import="dto.UserDTO"%>
<%@page import="utils.AuthUtils"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
<head>
    <title>Create Project</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            text-align: center;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 40%;
            margin: 50px auto;
            background: white;
            padding: 25px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        h2 {
            color: #343a40;
        }
        input, select, button {
            width: 100%;
            padding: 12px;
            margin: 10px 0;
            border: 1px solid #ced4da;
            border-radius: 5px;
            font-size: 16px;
        }
        button {
            background-color: #007bff;
            color: white;
            border: none;
            cursor: pointer;
            transition: background 0.3s ease;
        }
        button:hover {
            background-color: #0056b3;
        }
        .error {
            color: red;
            font-size: 14px;
        }
        .error-container {
            background: #ffebee;
            color: #d32f2f;
            padding: 10px;
            margin: 10px 0;
            border-radius: 5px;
        }
        .back-link {
            display: inline-block;
            margin-top: 15px;
            color: #007bff;
            text-decoration: none;
            font-size: 16px;
        }
        .back-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="container">
        <% if (AuthUtils.isLoggedIn(session)) {
            UserDTO user = AuthUtils.getUser(session);
            if (AuthUtils.isAdmin(session)) { %>
        <h2>Create New Project</h2>
        <form action="MainController" method="post">
            <input type="hidden" name="action" value="createProject">

            <label>Project Name:</label>
            <input type="text" name="project_name" value="<%= (request.getAttribute("projectName") != null) ? request.getAttribute("projectName") : ""%>" required>
            <% if (request.getAttribute("projectName_error") != null) { %>
            <div class="error-container"><%= request.getAttribute("projectName_error") %></div>
            <% } %>

            <label>Description:</label>
            <input type="text" name="description" value="<%= (request.getAttribute("description") != null) ? request.getAttribute("description") : ""%>" required>
            <% if (request.getAttribute("description_error") != null) { %>
            <div class="error-container"><%= request.getAttribute("description_error") %></div>
            <% } %>

            <label>Status:</label>
            <select name="status">
                <option value="Ideation">Ideation</option>
                <option value="Development">Development</option>
                <option value="Launch">Launch</option>
                <option value="Scaling">Scaling</option>
            </select>

            <label>Estimated Launch:</label>
            <input type="date" name="estimated_launch" value="<%= (request.getAttribute("estimated_launch") != null) ? request.getAttribute("estimated_launch") : ""%>" required>
            <% if (request.getAttribute("date_error") != null) { %>
            <div class="error-container"><%= request.getAttribute("date_error") %></div>
            <% } %>

            <button type="submit">Create Project</button>
        </form>

        <form action="MainController" method="post">
            <input type="hidden" name="action" value="viewAllProjects">
            <button type="submit">Comeback</button>
        </form>

        <% } else { %>
        <div>
            <h1>403 Error</h1>
            <p>You do not have permission to access this content!</p>
            <a href="projects.jsp" class="back-link">Back to Project List</a>
        </div>
        <% } } else { %>
        <div>
            <h1>Access Denied</h1>
            <p>Please log in to access this page.</p>
            <a href="login.jsp" class="back-link">Go to Login</a>
        </div>
        <% } %>
    </div>
</body>
</html>
