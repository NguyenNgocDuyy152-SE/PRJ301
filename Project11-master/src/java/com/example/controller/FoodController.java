//package com.example.controller;
//
//import com.example.dao.FoodDAO;
//import com.example.dto.FoodItem;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.*;
//import java.io.IOException;
//import java.util.List;
//
//@WebServlet("/FoodController")
//public class FoodController extends HttpServlet {
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String action = request.getParameter("action");
//        FoodDAO foodDAO = new FoodDAO();
//
//        try {
//            if ("ViewFoods".equals(action)) {
//                List<FoodItem> foodItems = foodDAO.getAllFoodItems();
//                request.setAttribute("foodItems", foodItems);
//                request.getRequestDispatcher("views/foodList.jsp").forward(request, response);
//            } else if ("EditFood".equals(action)) {
//                String foodID = request.getParameter("foodID");
//                FoodItem food = foodDAO.getFoodById(foodID);
//                request.setAttribute("food", food);
//                request.getRequestDispatcher("views/admin/editFood.jsp").forward(request, response);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            request.setAttribute("ERROR", "Error processing food request.");
//            request.getRequestDispatcher("views/error.jsp").forward(request, response);
//        }
//    }
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String action = request.getParameter("action");
//        FoodDAO foodDAO = new FoodDAO();
//
//        try {
//            if ("CreateFood".equals(action)) {
//                String foodName = request.getParameter("foodName");
//                double price = Double.parseDouble(request.getParameter("price"));
//                int quantity = Integer.parseInt(request.getParameter("quantity"));
//                String category = request.getParameter("category");
//
//                FoodItem food = new FoodItem(null, foodName, price, quantity, category);
//                foodDAO.addFood(food);
//                response.sendRedirect("MainController?action=ViewFoods");
//            } else if ("UpdateFood".equals(action)) {
//                String foodID = request.getParameter("foodID");
//                String foodName = request.getParameter("foodName");
//                double price = Double.parseDouble(request.getParameter("price"));
//                int quantity = Integer.parseInt(request.getParameter("quantity"));
//                String category = request.getParameter("category");
//
//                FoodItem food = new FoodItem(foodID, foodName, price, quantity, category);
//                foodDAO.updateFood(food);
//                response.sendRedirect("MainController?action=ViewFoods");
//            } else if ("DeleteFood".equals(action)) {
//                String foodID = request.getParameter("foodID");
//                foodDAO.deleteFood(foodID);
//                response.sendRedirect("MainController?action=ViewFoods");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            request.setAttribute("ERROR", "Error processing food request.");
//            request.getRequestDispatcher("views/error.jsp").forward(request, response);
//        }
//    }
//}
package com.example.controller;

import com.example.dao.FoodDAO;
import com.example.dto.FoodItem;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
import javax.servlet.annotation.WebServlet;
@WebServlet("/FoodController")
public class FoodController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        FoodDAO foodDAO = new FoodDAO();

        try {
            if ("ViewFoods".equals(action)) {
                List<FoodItem> foodItems = foodDAO.getAllFoodItems();
                request.setAttribute("foodItems", foodItems);
                request.getRequestDispatcher("views/foodList.jsp").forward(request, response);
            } else if ("ManageFood".equals(action)) { // Thêm branch này
                List<FoodItem> foodItems = foodDAO.getAllFoodItems();
                request.setAttribute("foodItems", foodItems);
                request.getRequestDispatcher("views/admin/manageFood.jsp").forward(request, response);
            } else if ("EditFood".equals(action)) {
                String foodID = request.getParameter("foodID");
                FoodItem food = foodDAO.getFoodById(foodID);
                request.setAttribute("food", food);
                request.getRequestDispatcher("/views/admin/editfood.jsp").forward(request, response);
            }
             else if ("DeleteFood".equals(action)) {
            String foodID = request.getParameter("foodID");
            foodDAO.deleteFood(foodID);
            response.sendRedirect(request.getContextPath() + "/MainController?action=ViewFoods");
        }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("ERROR", "Error processing food request.");
            request.getRequestDispatcher("views/error.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
