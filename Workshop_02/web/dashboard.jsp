<%@page import="dto.UserDTO, java.util.List, dto.ExamCategoryDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    UserDTO user = (UserDTO) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    String role = user.getRole();
    List<ExamCategoryDTO> categories = (List<ExamCategoryDTO>) session.getAttribute("examCategories");
%>

<html>
    <head>
        <title>Dashboard</title>
    </head>
    <body>
        <h2>Welcome, <%= user.getName()%>!</h2>
        <p>You are logged in as: <%= user.getRole()%></p>

        <form action="MainController" method="post" style="float: right;">
            <input type="hidden" name="action" value="logout">
            <button type="submit">Logout</button>
        </form>

        <% if ("Instructor".equals(role)) { %>
        <a href="createExam.jsp">Create New Exam</a>
        <% } %>

        <h2>Exam Categories</h2>
        <table border="1">
            <tr>

                <th>Category Name</th>
                <th>Description</th>
                <th>Actions</th>
            </tr>
            <%
                if (categories != null && !categories.isEmpty()) {
                    for (ExamCategoryDTO category : categories) {
            %>
            <tr>
                <td><%= category.getCategoryName()%></td>
                <td><%= category.getDescription()%></td>
                <td>
                    <form action="MainController" method="POST">
                        <input type="hidden" name="action" value="viewExamsByCategory"/>
                        <input type="hidden" name="categoryId" value="<%= category.getCategoryId()%>"/>
                        <button type="submit">View Exams</button>
                    </form>
                </td>            </tr>
                <%
                    }
                } else {
                %>
            <tr>
                <td colspan="3">No categories available.</td>
            </tr>
            <%
                }
            %>
        </table>
    </body>
</html>
