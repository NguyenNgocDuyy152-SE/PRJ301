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

            Username: <input type="text" name="txtusername" required /><br/>
            Password: <input type="password" name="txtpassword" required /><br/>
            <button type="submit">Login</button><br/>
        </form>
        <div>
            <% String message = (String) request.getAttribute("message"); %>
            <% if (message != null) {%>
            <div style="color: red;"><%= message%></div>
            <% }%>
        </div>
    </body>
</html>
