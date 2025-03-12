<%-- 
    Document   : login
    Created on : Mar 11, 2025, 9:01:08 PM
    Author     : anhqu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
    </head>
    <body>
        <h2>Login</h2>
        <form action="MainController" method="post">
            <input type="hidden" name="action" value="login" />

            <input type="text" name="txtUserID" placeholder="Username" required />
            <input type="password" name="txtPassword" placeholder="Password" required />
            <button type="submit">Login</button>
            <div class="message">
                <% String message = (String) request.getAttribute("message"); %>
                <% if (message != null) {%>
                <%= message%>
                <% }%>
            </div>
    </body>
</html>
