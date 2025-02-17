<%-- 
    Document   : login
    Created on : Feb 13, 2025, 11:07:37 AM
    Author     : anhqu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="MainController" method="post">
            <input type="hidden" name="action" value="login" />
            Username <input type="text" name="txtUsername" /> <br/>
            Password <input type="password" name="txtPassword" /> <br/>
            <input type="submit" value="Login" />
            
        </form>
    </body>
</html>
