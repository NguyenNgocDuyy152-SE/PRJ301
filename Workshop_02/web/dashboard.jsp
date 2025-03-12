<%@page import="dto.UserDTO, java.util.List, dto.ExamCategoryDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    UserDTO user = (UserDTO) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    List<ExamCategoryDTO> categoryList = (List<ExamCategoryDTO>) request.getAttribute("categoryList");
%>

<html>
    <head>
        <title>Exam Dashboard</title>
    </head>
    <body>
        <h2>Welcome, <%= user.getName()%>!</h2>

        <h3>Exam Categories</h3>
        <% if (categoryList != null && !categoryList.isEmpty()) { %>
        <table border="1">
            <tr>
                <th>Category Name</th>
                <th>Description</th>
            </tr>
            <% for (ExamCategoryDTO category : categoryList) {%>
            <tr>
                <td><%= category.getCategoryName()%></td>
                <td><%= category.getDescription()%></td>
            </tr>
            <% } %>
        </table>
        <% } else { %>
        <p>No exam categories available.</p>
        <% }%>

        <h3>View Exams</h3>
        <form action="ExamServlet" method="get">
            <input type="hidden" name="action" value="viewExams">
            <label>Category ID:</label>
            <input type="text" name="category_id">
            <button type="submit">View Exams</button>
        </form>

        <br>
        <form action="MainController" method="post">
            <input type="hidden" name="action" value="logout">
            <button type="submit">Logout</button>
        </form>
    </body>
</html>
