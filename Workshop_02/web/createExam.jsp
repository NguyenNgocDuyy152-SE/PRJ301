

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Exam</title>
    <link rel="stylesheet" href="css/createExam.css">
</head>
<body>
    <h2>Create New Exam</h2>

    <c:if test="${not empty sessionScope.errorMessage}">
        <p style="color: red;">${sessionScope.errorMessage}</p>
        
        <c:remove var="errorMessage" scope="session"/>
    </c:if>

    <form action="MainController" method="POST">
        <input type="hidden" name="action" value="createExam"/>

        <label for="examTitle">Exam Title:</label>
        <input type="text" id="examTitle" name="examTitle" required/><br>

        <label for="subject">Subject:</label>
        <input type="text" id="subject" name="subject" required/><br>

        <label for="categoryId">Category:</label>
        <select name="categoryId" id="categoryId" required>
            <option value="1">Quiz</option>
            <option value="2">Midterm</option>
            <option value="3">Final</option>
        </select><br>

        <label for="totalMarks">Total Marks:</label>
        <input type="number" id="totalMarks" name="totalMarks" required/><br>

        <label for="duration">Duration (minutes):</label>
        <input type="number" id="duration" name="duration" required/><br>

        <input type="submit" value="Create Exam"/>
    </form>
<c:if test="${not empty requestScope.errorMessage}">
    <p style="color: red;">${requestScope.errorMessage}</p>
</c:if>
    <a href="dashboard.jsp">Back to Dashboard</a>
</body>
</html>
