<%@ include file="/header.jsp" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.dto.FoodItem" %>
<html>
<head>
    <!-- Bootstrap CSS (sử dụng CDN) -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ775/0fS3XfKpGxUu6rZf3/nCJdFj3c4bFt" crossorigin="anonymous">

    <title>Manage Food</title>
    <link rel="stylesheet" type="text/css" href="../css/styles.css">
</head>
<body>
    <h2>Manage Food Items</h2>
    <a href="${pageContext.request.contextPath}/MainController?action=ManageFood">Add New Food Item</a>
    <table>
        <tr>
            <th>Food Name</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Category</th>
            <th>Actions</th>
        </tr>
        <%
            List<FoodItem> foodItems = (List<FoodItem>) request.getAttribute("foodItems");
            if (foodItems != null && !foodItems.isEmpty()) {
                for (FoodItem item : foodItems) {
        %>
        <tr>
            <td><%= item.getFoodName() %></td>
            <td>$<%= item.getPrice() %></td>
            <td><%= item.getQuantity() %></td>
            <td><%= item.getCategory() %></td>
            <td>
<form action="${pageContext.request.contextPath}/MainController" method="post">
    <input type="hidden" name="action" value="EditFood">
    <input type="hidden" name="foodID" value="${item.foodID}">
    <input type="submit" value="Edit">
</form>

<form action="${pageContext.request.contextPath}/MainController" method="post" style="display:inline;">
    <input type="hidden" name="action" value="DeleteFood">
    <input type="hidden" name="foodID" value="${item.foodID}">
    <input type="submit" value="Delete">
</form>

    
            </td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="5">No food items available.</td>
        </tr>
        <%
            }
        %>
    </table>
    <br>
    <a href="../../MainController?action=ViewFoods">Return to Home</a>
    <!-- jQuery, Popper.js, và Bootstrap JS -->
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
<%@ include file="/footer.jsp" %>