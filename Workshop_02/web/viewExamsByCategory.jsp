<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Exams in Category</title>
    <link rel="stylesheet" href="css/viewExamsByCategory.css">
</head>
<body>
    <h2>Exams in Category: 
        <% 
            String categoryId = request.getParameter("categoryId");
            if (categoryId != null && !categoryId.isEmpty()) {
                out.print(categoryId);
            } else {
                out.print("<span style='color:red;'>Invalid Category</span>");
            }
        %>
    </h2>

    <%
        List<dto.ExamDTO> exams = (List<dto.ExamDTO>) request.getAttribute("exams");
        if (exams != null && !exams.isEmpty()) {
    %>
        <table border="1">
            <thead>
                <tr>
                    <th>Exam Title</th>
                    <th>Subject</th>
                    <th>Total Marks</th>
                    <th>Duration</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (dto.ExamDTO exam : exams) {
                %>
                    <tr>
                        <td><%= exam.getExamTitle() %></td>
                        <td><%= exam.getSubject() %></td>
                        <td><%= exam.getTotalMarks() %></td>
                        <td><%= exam.getDuration() %></td>
                        <td><a href="MainController?action=takeExam&examId=<%= exam.getExamId() %>">Take Exam</a></td>
                    </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    <%
        } else {
    %>
        <p style="color:red;">No exams found for this category.</p>
    <%
        }
    %>

    <div class="back-button">
        <a href="dashboard.jsp">Back to Dashboard</a>
    </div>
</body>
</html>
