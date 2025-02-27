<%@ include file="/header.jsp" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Food</title>
    <link rel="stylesheet" type="text/css" href="../css/styles.css">
</head>
<body>
    <h2>Create a New Food Item</h2>
    <form action="../../MainController" method="post">
        <input type="hidden" name="action" value="CreateFood">
        <label for="foodName">Food Name:</label>
        <input type="text" name="foodName" required><br><br>
        <label for="price">Price:</label>
        <input type="number" step="0.01" name="price" required><br><br>
        <label for="quantity">Quantity:</label>
        <input type="number" name="quantity" required><br><br>
        <label for="category">Category:</label>
        <input type="text" name="category" required><br><br>
        <input type="submit" value="Create Food">
    </form>
    <br>
    <a href="manageFood.jsp">Back to Manage Food</a>
</body>
</html>
