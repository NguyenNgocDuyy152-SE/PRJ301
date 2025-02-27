<%@ include file="/header.jsp" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <!-- Bootstrap CSS (sử dụng CDN) -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ775/0fS3XfKpGxUu6rZf3/nCJdFj3c4bFt" crossorigin="anonymous">

    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
    <h2>Login</h2>
    <form action="${pageContext.request.contextPath}/LoginController" method="post">
    <label for="userID">User ID:</label>
    <input type="text" name="userID" required><br><br>
    <label for="password">Password:</label>
    <input type="password" name="password" required><br><br>
    <input type="submit" value="Login">
</form>

    <br>
    <a href="views/register.jsp">Register Here</a>
    <%
        String error = (String) request.getAttribute("ERROR");
        if (error != null) {
    %>
    <p style="color:red;"><%= error %></p>
    <%
        }
        String message = (String) request.getAttribute("MESSAGE");
        if (message != null) {
    %>
    <p style="color:green;"><%= message %></p>
    <%
        }
    %>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" 
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" 
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" 
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa/3o7f+6JVBxJvH29pPvg4+" 
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" 
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIyVYhS+2W6uCJAnmVQm4LxmAzR0Bf5SBvR41zKw" 
        crossorigin="anonymous"></script>
</body>
</html>
