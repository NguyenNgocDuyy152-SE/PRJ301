<%-- 
    Document   : output
    Created on : Feb 10, 2025, 11:06:38 AM
    Author     : anhqu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            int n = (int) request.getAttribute("n");
         %> 
         <h3>Bang Cuu Chuong <%=n%></h3>
            <%
                for(int j=1; j<=10; j++){
            %>        
            <%=n%> * <%=j%> = <%=(n*j)%></br>
                <%
                }   
                %>
    </body>
</html>
