<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Food Order Management System</title>
    <!-- Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" 
          integrity="sha384-9ndCyUa6mY1Yh2ZlCkY2gU6G7Yj3w1L6owK5EoF0xov+VzYp9GTwPp7F6gq7hR9s" crossorigin="anonymous">
    <!-- Custom CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
    <!-- Navigation Bar chuyên nghiệp -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
      <div class="container">
          <a class="navbar-brand" href="${pageContext.request.contextPath}/MainController?action=ViewFoods">Food Order</a>
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" 
                  aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarNav">
              <ul class="navbar-nav ms-auto">
                  <li class="nav-item">
                      <a class="nav-link" href="${pageContext.request.contextPath}/MainController?action=ViewFoods">Home</a>
                  </li>
                  <% 
                      // Kiểm tra quyền để hiển thị menu admin
                      String role = (String) session.getAttribute("roleID");
                      if(role != null && role.equalsIgnoreCase("ADM")) {
                  %>
                  <li class="nav-item">
                      <a class="nav-link" href="${pageContext.request.contextPath}/MainController?action=ManageFood">Manage Food</a>
                  </li>
                  <li class="nav-item">
                      <a class="nav-link" href="${pageContext.request.contextPath}/MainController?action=ViewOrders">View Orders</a>
                  </li>
                  <% } %>
<!--                  <li class="nav-item">
                      <a class="nav-link" href="${pageContext.request.contextPath}/LogoutController">Logout</a>
                  </li>-->
              </ul>
          </div>
      </div>
    </nav>
    <div class="container mt-4">
