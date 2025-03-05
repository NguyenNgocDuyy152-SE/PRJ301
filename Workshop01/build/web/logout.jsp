<%-- 
    Document   : logout
    Created on : Feb 28, 2025, 1:30:46 AM
    Author     : Huy
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%
    HttpSession sessionObj = request.getSession(false);
    if (sessionObj != null) {
        sessionObj.invalidate();
    }
    response.sendRedirect("login.jsp");
%>

