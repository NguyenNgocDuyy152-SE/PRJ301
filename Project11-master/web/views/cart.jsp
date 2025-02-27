<%@ include file="/header.jsp" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.dto.CartItem" %>
<html>
<head>
    <title>Your Cart</title>
    <!-- Bootstrap CSS (sử dụng CDN) -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ775/0fS3XfKpGxUu6rZf3/nCJdFj3c4bFt" crossorigin="anonymous">

    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
    <h2>Your Shopping Cart</h2>
    <table>
        <tr>
            <th>Food Name</th>
            <th>Quantity</th>
            <th>Price</th>
            <th>Total</th>
            <th>Actions</th>
        </tr>
        <%
            List<CartItem> cartItems = (List<CartItem>) request.getAttribute("cartItems");
            if (cartItems != null && !cartItems.isEmpty()) {
                for (CartItem item : cartItems) {
        %>
        <tr>
            <td><%= item.getFoodName() %></td>
            <td><%= item.getQuantity() %></td>
            <td>$<%= item.getPrice() %></td>
            <td>$<%= item.getTotal() %></td>
            <td>
                <form action="MainController" method="post" style="display:inline;">
                    <input type="hidden" name="action" value="UpdateCart">
                    <input type="hidden" name="foodID" value="<%= item.getFoodID() %>">
                    <input type="number" name="quantity" value="<%= item.getQuantity() %>" min="1" style="width:50px;">
                    <input type="submit" value="Update">
                </form>
                <form action="MainController" method="post" style="display:inline;">
                    <input type="hidden" name="action" value="RemoveFromCart">
                    <input type="hidden" name="foodID" value="<%= item.getFoodID() %>">
                    <input type="submit" value="Remove">
                </form>
            </td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="5">Your cart is empty.</td>
        </tr>
        <%
            }
        %>
    </table>
    <br>
   <br>
<a href="${pageContext.request.contextPath}/MainController?action=ViewFoods">Continue Shopping</a>

    <br><br>
    <form action="MainController" method="post">
        <input type="hidden" name="action" value="Checkout">
        <input type="submit" value="Checkout">
    </form>
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