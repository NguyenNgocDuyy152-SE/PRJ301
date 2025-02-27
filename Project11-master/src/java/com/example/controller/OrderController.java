package com.example.controller;

import com.example.dao.CartDAO;
import com.example.dao.OrderDAO;
import com.example.dto.CartItem;
import com.example.dto.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/OrderController")
public class OrderController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        OrderDAO orderDAO = new OrderDAO();
        CartDAO cartDAO = new CartDAO();

        try {
            if ("Checkout".equals(action)) {
                List<CartItem> cartItems = cartDAO.getCartItems(session);
                request.setAttribute("cartItems", cartItems);
                request.getRequestDispatcher("views/checkout.jsp").forward(request, response);
            } else if ("PlaceOrder".equals(action)) {
                String userID = (String) session.getAttribute("userID");
                double totalAmount = Double.parseDouble(request.getParameter("totalAmount"));
                orderDAO.placeOrder(userID, totalAmount, session);
                session.removeAttribute("cartItems");  // Clear the cart after placing order
                response.sendRedirect("views/orderConfirmation.jsp");
            } else if ("ViewOrders".equals(action)) {
                // Lấy danh sách đơn hàng từ cơ sở dữ liệu thông qua OrderDAO
                List<Order> orders = orderDAO.getAllOrders();
                // Đặt danh sách đơn hàng vào request attribute
                request.setAttribute("orders", orders);
                // Chuyển tiếp đến trang viewOrders.jsp (chú ý sử dụng đường dẫn tuyệt đối)
                request.getRequestDispatcher("/views/admin/viewOrders.jsp").forward(request, response); }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("ERROR", "Failed to process order.");
            request.getRequestDispatcher("views/error.jsp").forward(request, response);
        }
    }
    @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    // Có thể gọi doPost() nếu logic của GET và POST giống nhau:
    doPost(request, response);
}
}
