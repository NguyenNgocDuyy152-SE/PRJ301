<%-- 
    Document   : createProject
    Created on : Feb 28, 2025, 1:24:57 AM
    Author     : Huy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Create Project</title>
</head>
<body>
    <h2>Create New Project</h2>
    <form action="MainController" method="post">
        <input type="hidden" name="action" value="createProject">
        <label>Project Name: <input type="text" name="project_name" required></label><br>
        <label>Description: <input type="text" name="description" required></label><br>
        <label>Status:
            <select name="status">
                <option value="Ideation">Ideation</option>
                <option value="Development">Development</option>
                <option value="Launch">Launch</option>
                <option value="Scaling">Scaling</option>
            </select>
        </label><br>
        <label>Estimated Launch: <input type="date" name="estimated_launch" required></label><br>
        <button type="submit">Create Project</button>
    </form>
</body>
</html>
