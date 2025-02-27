package com.example.dao;

import com.example.dto.FoodItem;
import com.example.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FoodDAO {
    
    public List<FoodItem> getAllFoodItems() throws Exception {
        List<FoodItem> foodItems = new ArrayList<>();
        String sql = "SELECT * FROM tblFoodItems";
        try (Connection conn = DatabaseConnection.initializeDatabase();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                FoodItem food = new FoodItem();
                food.setFoodID(rs.getString("foodID"));
                food.setFoodName(rs.getString("foodName"));
                food.setPrice(rs.getDouble("price"));
                food.setQuantity(rs.getInt("quantity"));
                food.setCategory(rs.getString("category"));
                foodItems.add(food);
            }
        }
        return foodItems;
    }
    
    public FoodItem getFoodById(String foodID) throws Exception {
        FoodItem food = null;
        String sql = "SELECT * FROM tblFoodItems WHERE foodID = ?";
        try (Connection conn = DatabaseConnection.initializeDatabase();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, foodID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    food = new FoodItem();
                    food.setFoodID(rs.getString("foodID"));
                    food.setFoodName(rs.getString("foodName"));
                    food.setPrice(rs.getDouble("price"));
                    food.setQuantity(rs.getInt("quantity"));
                    food.setCategory(rs.getString("category"));
                }
            }
        }
        return food;
    }
    
    public void addFood(FoodItem food) throws Exception {
        String sql = "INSERT INTO tblFoodItems (foodID, foodName, price, quantity, category) VALUES (?, ?, ?, ?, ?)";
        // Generate a unique foodID if not provided
        if (food.getFoodID() == null || food.getFoodID().isEmpty()) {
            food.setFoodID("F" + System.currentTimeMillis());
        }
        try (Connection conn = DatabaseConnection.initializeDatabase();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, food.getFoodID());
            stmt.setString(2, food.getFoodName());
            stmt.setDouble(3, food.getPrice());
            stmt.setInt(4, food.getQuantity());
            stmt.setString(5, food.getCategory());
            stmt.executeUpdate();
        }
    }
    
    public void updateFood(FoodItem food) throws Exception {
        String sql = "UPDATE tblFoodItems SET foodName = ?, price = ?, quantity = ?, category = ? WHERE foodID = ?";
        try (Connection conn = DatabaseConnection.initializeDatabase();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, food.getFoodName());
            stmt.setDouble(2, food.getPrice());
            stmt.setInt(3, food.getQuantity());
            stmt.setString(4, food.getCategory());
            stmt.setString(5, food.getFoodID());
            stmt.executeUpdate();
        }
    }
    
    public void deleteFood(String foodID) throws Exception {
        String sql = "DELETE FROM tblFoodItems WHERE foodID = ?";
        try (Connection conn = DatabaseConnection.initializeDatabase();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, foodID);
            stmt.executeUpdate();
        }
    }
}
