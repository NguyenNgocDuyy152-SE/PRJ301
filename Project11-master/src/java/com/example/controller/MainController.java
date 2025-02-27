package com.example.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class MainController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        String url = "views/login.jsp";  // Default redirection

        try {
            if (action == null) {
                action = "Login";
            }
            switch (action) {
                case "Login":
                    url = "LoginController";
                    break;
                case "Register":
                    url = "RegisterController";
                    break;
                case "ViewFoods":
                    url = "FoodController?action=ViewFoods";
                    break;
                case "AddToCart":
                    url = "CartController?action=AddToCart";
                    break;
                case "ViewCart":
                    url = "CartController";
                    break;
                case "UpdateCart":
                    url = "CartController";
                    break;
                case "RemoveFromCart":
                    url = "CartController";
                    break;
                            case "ManageFood":
            
                            url = "FoodController?action=ManageFood";
                            break;
                case "Checkout":
                    url = "OrderController";
                    break;
                case "PlaceOrder":
                    url = "OrderController";
                    break;
                case "CreateFood":
                    url = "FoodController";
                    break;
                case "UpdateFood":
                     url = "FoodController?action=UpdateFood";
                    break;
                case "DeleteFood":
                    url = "FoodController?action=DeleteFood";
                    break;
                case "EditFood":
                      url = "FoodController?action=EditFood";
                    break;
                case "ViewOrders":
                    url = "OrderController?action=ViewOrders";
                    break;
                default:
                    request.setAttribute("ERROR", "Action not supported.");
            }
        } catch (Exception e) {
            log("Error at MainController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
