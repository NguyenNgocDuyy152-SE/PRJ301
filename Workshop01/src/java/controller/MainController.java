/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ProjectDAO;
import dao.UserDAO;
import dto.ProjectDTO;
import dto.UserDTO;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Huy
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    private static final String LOGIN_PAGE = "login.jsp";
    private static final String DASHBOARD_PAGE = "dashboard.jsp";
    private static final String PROJECTS_PAGE = "projects.jsp";

    private UserDAO userDAO = new UserDAO();
    private ProjectDAO projectDAO = new ProjectDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = LOGIN_PAGE;

        try {
            String action = request.getParameter("action");
            if (action == null) {
                url = LOGIN_PAGE;
            } else {
                HttpSession session = request.getSession();

                switch (action) {
                    case "login":
                        String username = request.getParameter("username");
                        String password = request.getParameter("password");
                        UserDTO user = UserDAO.readById(username);
                        if (user != null && user.getPassword().equals(password)) {
                            session.setAttribute("user", user);
                            url = DASHBOARD_PAGE;
                        } else {
                            request.setAttribute("message", "Invalid username or password");
                        }
                        break;

                    case "logout":
                        session.invalidate();
                        url = LOGIN_PAGE;
                        break;

                    case "viewProjects":
                        List<ProjectDTO> projects = projectDAO.readAll();
                        request.setAttribute("projects", projects);
                        url = PROJECTS_PAGE;
                        break;

                    case "createProject":
                        HttpSession currentSession = request.getSession(false);
                        if (currentSession == null || currentSession.getAttribute("user") == null) {
                            url = LOGIN_PAGE; // Chuyển về trang đăng nhập nếu chưa đăng nhập
                            break;
                        }

                        String projectName = request.getParameter("project_name");
                        String description = request.getParameter("description");
                        String status = request.getParameter("status");
                        String launchDateStr = request.getParameter("estimated_launch");

                        Date launchDate = null;
                        try {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            launchDate = new Date(sdf.parse(launchDateStr).getTime());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        ProjectDTO newProject = new ProjectDTO(0, projectName, description, status, launchDate);
                        projectDAO.create(newProject);

                        url = PROJECTS_PAGE;
                        break;

                    case "updateStatus":
                        int projectId = Integer.parseInt(request.getParameter("project_id"));
                        String newStatus = request.getParameter("status");
                        projectDAO.updateStatus(projectId, newStatus);
                        url = PROJECTS_PAGE;
                        break;

                    case "searchProject":
                        String searchTerm = request.getParameter("searchTerm");
                        List<ProjectDTO> searchResults = projectDAO.search(searchTerm);
                        request.setAttribute("projects", searchResults);
                        url = PROJECTS_PAGE;
                        break;
                }
            }
        } catch (Exception e) {
            log("Error at MainController: " + e.toString());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
