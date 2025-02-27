<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ include file="/header.jsp" %>

<%@ page import="com.example.dto.FoodItem" %>
<html>
<head>
    <title>Food Items</title>
    <!-- Bootstrap CSS (sử dụng CDN) -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ775/0fS3XfKpGxUu6rZf3/nCJdFj3c4bFt" crossorigin="anonymous">

    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
    <h2>Available Food Items</h2>
    <table>
        <tr>
            <th>Food Name</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Category</th>
            <th>Action</th>
        </tr>
        <%
            List<FoodItem> foodItems = (List<FoodItem>) request.getAttribute("foodItems");
            if (foodItems != null && !foodItems.isEmpty()) {
                for (FoodItem food : foodItems) {
        %>
        <tr>
            <td><%= food.getFoodName() %></td>
            <td>$<%= food.getPrice() %></td>
            <td><%= food.getQuantity() %></td>
            <td><%= food.getCategory() %></td>
            <td>
                <form action="MainController" method="post">
                    <input type="hidden" name="action" value="AddToCart">
                    <input type="hidden" name="foodID" value="<%= food.getFoodID() %>">
                    <input type="number" name="quantity" value="1" min="1" style="width:50px;">
                    <input type="submit" value="Add to Cart">
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
    <br>
<a href="${pageContext.request.contextPath}/MainController?action=ViewCart">View Cart</a>

    <br><br>
    <%
        String userID = (String) session.getAttribute("userID");
        if (userID != null && "admin".equals(userID)) {
    %>
    <a href="${pageContext.request.contextPath}/MainController?action=ManageFood">Manage Food Items (Admin)</a>

    <br><br>
    <form action="${pageContext.request.contextPath}/MainController" method="post">
    <input type="hidden" name="action" value="ViewOrders">
    <input type="submit" value="View Orders (Admin)">
</form>

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
<%@ include file="/footer.jsp" %>