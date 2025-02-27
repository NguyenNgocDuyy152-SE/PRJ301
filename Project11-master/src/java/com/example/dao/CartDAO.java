package com.example.dao;

import com.example.dto.CartItem;
import com.example.dto.FoodItem;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class CartDAO {
    @SuppressWarnings("unchecked")
    public List<CartItem> getCartItems(HttpSession session) {
        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cartItems");
        if (cartItems == null) {
            cartItems = new ArrayList<>();
            session.setAttribute("cartItems", cartItems);
        }
        return cartItems;
    }
    
    public void addToCart(HttpSession session, String foodID, int quantity) throws Exception {
        List<CartItem> cartItems = getCartItems(session);
        // Check if item already exists in the cart
        for (CartItem item : cartItems) {
            if (item.getFoodID().equals(foodID)) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        // Retrieve food details using FoodDAO and add a new cart item
        FoodItem food = new FoodDAO().getFoodById(foodID);
        if (food != null) {
            CartItem newItem = new CartItem(food.getFoodID(), food.getFoodName(), quantity, food.getPrice());
            cartItems.add(newItem);
        }
    }
    
    public void updateCart(HttpSession session, String foodID, int quantity) {
        List<CartItem> cartItems = getCartItems(session);
        for (CartItem item : cartItems) {
            if (item.getFoodID().equals(foodID)) {
                item.setQuantity(quantity);
                break;
            }
        }
    }
    
    public void removeFromCart(HttpSession session, String foodID) {
        List<CartItem> cartItems = getCartItems(session);
        cartItems.removeIf(item -> item.getFoodID().equals(foodID));
    }
}
