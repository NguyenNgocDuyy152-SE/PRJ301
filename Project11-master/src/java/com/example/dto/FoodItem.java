package com.example.dto;

public class FoodItem {
    private String foodID;
    private String foodName;
    private double price;
    private int quantity;
    private String category;
    
    public FoodItem() {}
    
    public FoodItem(String foodID, String foodName, double price, int quantity, String category) {
        this.foodID = foodID;
        this.foodName = foodName;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }
    
    // Getters and Setters
    public String getFoodID() { return foodID; }
    public void setFoodID(String foodID) { this.foodID = foodID; }
    
    public String getFoodName() { return foodName; }
    public void setFoodName(String foodName) { this.foodName = foodName; }
    
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
}
