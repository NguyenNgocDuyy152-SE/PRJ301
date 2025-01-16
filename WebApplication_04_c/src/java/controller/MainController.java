/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author anhqu
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    public boolean isValidLogin(String username, String password) {
        return username.equals("admin") && password.equals("12345678");
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String txtUsername = request.getParameter("txtUsername");
        String txtPassword = request.getParameter("txtPassword");

        if (txtUsername.trim().length() == 0) {
            out.println("Please enter username");
            return;
        }
        if (txtPassword.trim().length() == 0 || txtPassword.trim().length() < 8) {
            out.println("Password must be greate than 8 characters!");
            return;
        }
        // login process
        if (isValidLogin(txtUsername, txtPassword)) {
            // forward search.html
            RequestDispatcher rd = request.getRequestDispatcher("search.html");
            rd.forward(request, response);
        } else {
            // forward / redirect invalid.html
            // forward search.html
            // RequestDispatcher rd = request.getRequestDispatcher("invalid.html");
            // rd.forward(request, response); 

            // redirect search.html
            response.sendRedirect("invalid.html");

            //compare RequestDispatcher /  response.sendRedirect? khac biet? khi nao nen dung?
//Khi nào nên sử dụng?
//RequestDispatcher:
//Khi điều hướng trong cùng ứng dụng.
//Khi cần chia sẻ dữ liệu hoặc thuộc tính yêu cầu giữa các thành phần.
//Ví dụ: Chuyển tiếp yêu cầu đến một JSP sau khi xử lý dữ liệu trong servlet.
//response.sendRedirect:
//Khi điều hướng đến ứng dụng hoặc miền khác.
//Khi muốn thay đổi URL hiển thị trên trình duyệt.
//Ví dụ: Chuyển hướng người dùng đến trang đăng nhập hoặc trang web bên ngoài sau khi đăng xuất.
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}