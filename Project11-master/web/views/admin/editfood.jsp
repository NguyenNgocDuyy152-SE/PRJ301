
<%@ include file="/header.jsp" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.dto.FoodItem" %>
<html>
<head>
    
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ775/0fS3XfKpGxUu6rZf3/nCJdFj3c4bFt" crossorigin="anonymous">
    <title>Edit Food</title>
    <!-- Sử dụng context path để đảm bảo đường dẫn đúng -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
    <%
        FoodItem food = (FoodItem) request.getAttribute("food");
        if (food == null) {
            out.println("Food item is not available.");
        }
    %>
    <h2>Edit Food: <%= food.getFoodName() %></h2>
    <!-- Sử dụng EL để tạo đường dẫn tuyệt đối -->
    <form action="${pageContext.request.contextPath}/MainController" method="post">
        <input type="hidden" name="action" value="UpdateFood">
        <input type="hidden" name="foodID" value="<%= food.getFoodID() %>">
        
        <label for="foodName">Food Name:</label>
        <input type="text" name="foodName" value="<%= food.getFoodName() %>" required><br><br>
        
        <label for="price">Price:</label>
        <input type="number" step="0.01" name="price" value="<%= food.getPrice() %>" required><br><br>
        
        <label for="quantity">Quantity:</label>
        <input type="number" name="quantity" value="<%= food.getQuantity() %>" required><br><br>
        
        <label for="category">Category:</label>
        <input type="text" name="category" value="<%= food.getCategory() %>" required><br><br>
        
        <input type="submit" value="Update Food">
    </form>
    <br>
    <!-- Liên kết quay lại quản lý món ăn sử dụng context path và action ManageFood -->
    <a href="${pageContext.request.contextPath}/MainController?action=ManageFood">Back to Manage Food</a>
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