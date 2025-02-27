package com.example.dto;

public class CartItem {
    private String foodID;
    private String foodName;
    private int quantity;
    private double price;
    
    public CartItem() {}
    
    public CartItem(String foodID, String foodName, int quantity, double price) {
        this.foodID = foodID;
        this.foodName = foodName;
        this.quantity = quantity;
        this.price = price;
    }
    
    // Getters and Setters
    public String getFoodID() { return foodID; }
    public void setFoodID(String foodID) { this.foodID = foodID; }
    
    public String getFoodName() { return foodName; }
    public void setFoodName(String foodName) { this.foodName = foodName; }
    
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    
    public double getTotal() {
        return price * quantity;
    }
}
