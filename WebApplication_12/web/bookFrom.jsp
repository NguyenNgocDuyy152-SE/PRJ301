<%-- 
    Document   : bookFrom
    Created on : Feb 27, 2025, 11:12:32 AM
    Author     : anhqu
--%>

<%@page import="dto.BookDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%
        BookDTO book = new BookDTO();
        try {
            book = (BookDTO) request.getAttribute("book");
        }catch (Exception e) {
            book = new BookDTO(); 
        }
        if (book == null){
            book = new BookDTO();
        }
        %>        
    </head>
    <body>
        <form action="MainController" method="post">
            <input type="hidden" name="action" value="add"/>
            
            <input type="text" id="BookID" name="txtBookID" value="<%=book.getBookID()%>"/> <br/> 
            <input type="text" id="txtTitle" name="txtTitle" value="<%=book.getTitle()%>"/> <br/> 
            <input type="text" id="txtAuthor" name="txtAuthor" value="<%=book.getAuthor()%>"/> <br/> 
            <input type="number" id="PublishYear" name="txtPublishYear" value="<%=book.getPublishYear()%>"/> <br/> 
            <input type="number" id="Price" name="txtPrice" value="<%=book.getPrice()%>"/> <br/> 
            <input type="number" id="Quantity" name="txtQuantity" value="<%=book.getQuantity()%>"/> <br/> 
        </form>
        <input type="submit" value="Save" />
        <input type="reset" value="Reset" />
    </body>
</html>
