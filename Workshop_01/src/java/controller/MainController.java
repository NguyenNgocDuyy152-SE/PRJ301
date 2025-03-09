/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ProjectDAO;
import dto.ProjectDTO;
import dto.UserDTO;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.AuthUtils;

/**
 *
 * @author anhqu
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    private ProjectDAO projectDAO = new ProjectDAO();
    private static final String LOGIN_PAGE = "login.jsp";
    private static final String PROJECTS_PAGE = "projects.jsp";

    private String processLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = LOGIN_PAGE;
        //
        String strUserID = request.getParameter("txtUserID");
        String strPassword = request.getParameter("txtPassword");

        if (AuthUtils.isValidLogin(strUserID, strPassword)) {
            UserDTO user = AuthUtils.getUser(strUserID);
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("role", user.getRole());

            url = PROJECTS_PAGE; // Điều hướng đến projects.jsp nếu login thành công
        } else {
            request.setAttribute("message", "Incorrect UserID or Password");
        }
        return url;
    }

    private String processSearch(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "projects.jsp"; // Đảm bảo trỏ đến đúng trang dự án
        HttpSession session = request.getSession();

        if (AuthUtils.isLoggedIn(session)) {
            String role = (String) session.getAttribute("role");
            if ("Founder".equals(role)) {
                // Lấy từ khóa tìm kiếm
                String searchTerm = request.getParameter("searchTerm");
                if (searchTerm == null) {
                    searchTerm = "";
                }

                // Thực hiện tìm kiếm
                List<ProjectDTO> projectList = projectDAO.search(searchTerm);

                // Đặt kết quả tìm kiếm vào request
                request.setAttribute("projectList", projectList);
                request.setAttribute("searchTerm", searchTerm);
            } else {
                request.setAttribute("message", "You do not have permission to search.");
            }
        } else {
            url = LOGIN_PAGE; // Nếu chưa đăng nhập, chuyển hướng về trang login
        }
        return url;
    }

    private String processCreateProject(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "createProject.jsp"; // Trả về trang tạo dự án nếu có lỗi
        HttpSession session = request.getSession();

        if (AuthUtils.isLoggedIn(session)) {
            String role = (String) session.getAttribute("role");
            if ("Founder".equals(role)) {
                // Lấy thông tin từ form
                boolean checkError = false;
                String projectName = request.getParameter("project_name");
                String description = request.getParameter("description");
                String status = request.getParameter("status");
                String launchDateStr = request.getParameter("estimated_launch");

                // Kiểm tra rỗng hoặc chỉ chứa khoảng trắng
                if (projectName == null || projectName.trim().isEmpty()) {
                    checkError = true;
                    request.setAttribute("projectName_error", "Project name cannot be empty.");
                }

                if (description == null || description.trim().isEmpty()) {
                    checkError = true;
                    request.setAttribute("description_error", "Description cannot be empty.");
                }

                // Kiểm tra ngày hợp lệ
                java.sql.Date launchDate = null;
                try {
                    launchDate = java.sql.Date.valueOf(launchDateStr);

                    // Kiểm tra nếu ngày nhỏ hơn hoặc bằng hôm nay
                    java.sql.Date today = new java.sql.Date(System.currentTimeMillis());
                    if (!launchDate.after(today)) {
                        checkError = true;
                        request.setAttribute("date_error", "Estimated Launch Date must be later than today.");
                    } else {
                        request.setAttribute("estimated_launch", launchDateStr);
                    }

                } catch (IllegalArgumentException e) {
                    checkError = true;
                    request.setAttribute("date_error", "Invalid date format.");
                }

                // Nếu không có lỗi, tạo dự án
                if (!checkError) {
                    ProjectDTO newProject = new ProjectDTO(0, projectName, description, status, launchDate);
                    projectDAO.create(newProject);
                    request.setAttribute("message", "Project created successfully!");
                    url = "projects.jsp"; // Chuyển hướng về danh sách dự án nếu thành công
                }
            } else {
                request.setAttribute("message", "You do not have permission to create a project.");
                url = "projects.jsp";
            }
        }
        return url;
    }

    private String processLogout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = LOGIN_PAGE;
        HttpSession session = request.getSession();
        if (AuthUtils.isLoggedIn(session)) {
            session.invalidate(); // Hủy bỏ session
        }
        return url;
    }

    private String processUpdateProject(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            return LOGIN_PAGE;
        }
        // Kiểm tra quyền Founder
        String role = (String) session.getAttribute("role");
        if (!"Founder".equals(role)) {
            request.setAttribute("message", "You do not have permission to update projects.");
            return "projects.jsp"; // Hoặc trang lỗi nào đó
        }

        try {
            int projectId = Integer.parseInt(request.getParameter("project_id"));
            String newStatus = request.getParameter("status");

            boolean isUpdated = projectDAO.updateStatus(projectId, newStatus);
            if (isUpdated) {
                request.setAttribute("message", "Project updated successfully!");
            } else {
                request.setAttribute("message", "Failed to update project.");
            }
        } catch (NumberFormatException e) {
            request.setAttribute("message", "Invalid project ID.");
        }

        return "projects.jsp"; // Load lại trang projects.jsp để thấy thay đổi
    }

    private String processViewAllProjects(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = PROJECTS_PAGE;
        HttpSession session = request.getSession();

        if (AuthUtils.isLoggedIn(session)) {
            List<ProjectDTO> projects = projectDAO.readAll();
            request.setAttribute("projectList", projects);
        } else {
            url = LOGIN_PAGE; // Nếu chưa đăng nhập, chuyển hướng về login
        }
        return url;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = LOGIN_PAGE;

        try {
            String action = request.getParameter("action");
            if (action == null) {
                url = LOGIN_PAGE;
            } else {
                if (action.equals("login")) {
                    url = processLogin(request, response);
                } else if (action.equals("logout")) {
                    url = processLogout(request, response);
                } else if (action.equals("search")) {
                    url = processSearch(request, response);
                } else if (action.equals("viewAllProjects")) {
                    url = processViewAllProjects(request, response);
                } else if (action.equals("createProject")) {
                    url = processCreateProject(request, response);
                } else if (action.equals("update")) {
                    url = processUpdateProject(request, response);
                }
            }
        } catch (Exception e) {
            log("Error at MainController: " + e.toString());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
