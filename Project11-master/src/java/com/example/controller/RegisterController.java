package com.example.controller;

import com.example.dao.UserDAO;
import com.example.dto.User;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class RegisterController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Lấy dữ liệu từ form, bao gồm cả userID và roleID
        String userID = request.getParameter("userID");
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String password = request.getParameter("password");
        String roleID = request.getParameter("roleID");  // role từ lựa chọn của người dùng

        // Tạo đối tượng User với các trường đã nhận được
        User user = new User(userID, fullName, email, phoneNumber, roleID, password);
        
        try {
            boolean success = new UserDAO().registerUser(user);
            if (success) {
                request.setAttribute("MESSAGE", "Registration successful. Please login.");
                request.getRequestDispatcher("views/login.jsp").forward(request, response);
            } else {
                request.setAttribute("ERROR", "Registration failed. Please try again.");
                request.getRequestDispatcher("views/register.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("ERROR", "Registration error: " + e.getMessage());
            request.getRequestDispatcher("views/register.jsp").forward(request, response);
        }
    }
}
