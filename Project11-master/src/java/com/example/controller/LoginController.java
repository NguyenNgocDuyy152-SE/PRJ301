package com.example.controller;

import com.example.dao.UserDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class LoginController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userID = request.getParameter("userID");
        String password = request.getParameter("password");

        try {
            boolean isAuthenticated = new UserDAO().authenticateUser(userID, password);

            if (isAuthenticated) {
                HttpSession session = request.getSession();
                session.setAttribute("userID", userID);
                response.sendRedirect("MainController?action=ViewFoods");
            } else {
                request.setAttribute("ERROR", "Invalid credentials.");
                request.getRequestDispatcher("views/login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("views/login.jsp").forward(request, response);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
