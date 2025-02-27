package com.example.controller;

import com.example.dao.CartDAO;
import com.example.dto.CartItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/CartController")
public class CartController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        CartDAO cartDAO = new CartDAO();

        try {
            List<CartItem> cartItems = cartDAO.getCartItems(session);
            request.setAttribute("cartItems", cartItems);
            request.getRequestDispatcher("views/cart.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("ERROR", "Failed to retrieve cart items.");
            request.getRequestDispatcher("views/error.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        CartDAO cartDAO = new CartDAO();

        try {
            if ("AddToCart".equals(action)) {
                String foodID = request.getParameter("foodID");
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                cartDAO.addToCart(session, foodID, quantity);
                response.sendRedirect("MainController?action=ViewCart");
            } else if ("UpdateCart".equals(action)) {
                String foodID = request.getParameter("foodID");
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                cartDAO.updateCart(session, foodID, quantity);
                response.sendRedirect("MainController?action=ViewCart");
            } else if ("RemoveFromCart".equals(action)) {
                String foodID = request.getParameter("foodID");
                cartDAO.removeFromCart(session, foodID);
                response.sendRedirect("MainController?action=ViewCart");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("ERROR", "Failed to process cart action.");
            request.getRequestDispatcher("views/error.jsp").forward(request, response);
        }
    }
}
