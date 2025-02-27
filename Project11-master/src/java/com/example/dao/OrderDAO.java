package com.example.dao;

import com.example.dto.CartItem;
import com.example.dto.Order;
import com.example.util.DatabaseConnection;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderDAO {
    
    public void placeOrder(String userID, double totalAmount, HttpSession session) throws Exception {
        // Generate a unique orderID
        String orderID = "ORD" + UUID.randomUUID().toString().replace("-", "").substring(0, 10);
        String insertOrderSQL = "INSERT INTO tblOrders (orderID, userID, orderDate, totalAmount) VALUES (?, ?, ?, ?)";
        String insertOrderDetailSQL = "INSERT INTO tblOrderDetails (orderID, foodID, quantity, price) VALUES (?, ?, ?, ?)";
        
        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cartItems");
        
        try (Connection conn = DatabaseConnection.initializeDatabase()) {
            // Begin transaction
            conn.setAutoCommit(false);
            
            try (PreparedStatement orderStmt = conn.prepareStatement(insertOrderSQL)) {
                orderStmt.setString(1, orderID);
                orderStmt.setString(2, userID);
                orderStmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
                orderStmt.setDouble(4, totalAmount);
                orderStmt.executeUpdate();
            }
            
            try (PreparedStatement detailStmt = conn.prepareStatement(insertOrderDetailSQL)) {
                for (CartItem item : cartItems) {
                    detailStmt.setString(1, orderID);
                    detailStmt.setString(2, item.getFoodID());
                    detailStmt.setInt(3, item.getQuantity());
                    detailStmt.setDouble(4, item.getPrice());
                    detailStmt.addBatch();
                }
                detailStmt.executeBatch();
            }
            
            conn.commit();
        }
    }
        public List<Order> getAllOrders() throws Exception {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM tblOrders";
        try (Connection conn = DatabaseConnection.initializeDatabase();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Order order = new Order();
                order.setOrderID(rs.getString("orderID"));
                order.setUserID(rs.getString("userID"));
                order.setOrderDate(rs.getDate("orderDate"));
                order.setTotalAmount(rs.getDouble("totalAmount"));
                // Bạn có thể mở rộng để lấy chi tiết đơn hàng nếu cần
                orders.add(order);
            }
        }
        return orders;
    }
}
