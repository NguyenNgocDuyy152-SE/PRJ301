package com.example.dao;

import com.example.dto.User;
import com.example.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
    public boolean authenticateUser(String userID, String password) throws Exception {
        boolean isValid = false;
        String sql = "SELECT * FROM tblUsers WHERE userID = ? AND password = ?";
        try (Connection conn = DatabaseConnection.initializeDatabase();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userID);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    isValid = true;
                }
            }
        }
        return isValid;
    }
    
    public boolean registerUser(User user) throws Exception {
        String sql = "INSERT INTO tblUsers (userID, fullName, email, phoneNumber, roleID, password) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.initializeDatabase();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUserID());
            stmt.setString(2, user.getFullName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPhoneNumber());
            stmt.setString(5, user.getRoleID());
            stmt.setString(6, user.getPassword());
            int rows = stmt.executeUpdate();
            return rows > 0;
        }
    }
}
