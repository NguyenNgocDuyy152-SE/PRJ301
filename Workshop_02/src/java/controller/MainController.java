package controller;

import dao.ExamDAO;
import dao.UserDAO;
import dto.ExamCategoryDTO;
import dto.ExamDTO;
import dto.UserDTO;
import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    private static final String LOGIN_PAGE = "login.jsp";
    private static final String DASHBOARD_PAGE = "dashboard.jsp";
    private static final String VIEW_EXAMS_BY_CATEGORY_PAGE = "viewExamsByCategory.jsp";
    private static final String CREATE_EXAM_PAGE = "createExam.jsp";

    private ExamDAO examDAO = new ExamDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String url = LOGIN_PAGE;
        try {
            String action = request.getParameter("action");
            if (action != null) {
                switch (action) {
                    case "login":
                        url = processLogin(request, response);
                        break;
                    case "logout":
                        url = processLogout(request, response);
                        break;
                    case "viewExamsByCategory":
                        url = processViewExamsByCategory(request, response);
                        break;
                    case "createExam":
                        url = processCreateExam(request, response);
                        break;
                    default:
                        request.setAttribute("message", "Invalid action.");
                        break;
                }
            }
        } catch (Exception e) {
            log("Error in MainController: " + e.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

    private String processLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        String url = LOGIN_PAGE;
        String username = request.getParameter("txtusername");
        String password = request.getParameter("txtpassword");
        UserDTO user = UserDAO.readById(username);

        if (user != null && user.getPassword().equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("role", user.getRole());

            List<ExamCategoryDTO> categories = examDAO.getAllCategories();
            session.setAttribute("examCategories", categories);

            url = DASHBOARD_PAGE;
        } else {
            request.setAttribute("message", "Incorrect username or password.");
        }
        return url;
    }

    private String processLogout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return LOGIN_PAGE;
    }

    private String processViewExamsByCategory(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        String url = DASHBOARD_PAGE;
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("user") != null) {
            String categoryIdStr = request.getParameter("categoryId");
            try {
                int categoryId = Integer.parseInt(categoryIdStr);
                List<ExamDTO> exams = examDAO.getExamsByCategory(categoryId);
                request.setAttribute("exams", exams);
                url = VIEW_EXAMS_BY_CATEGORY_PAGE;
            } catch (NumberFormatException e) {
                request.setAttribute("message", "Invalid category ID format.");
            }
        } else {
            request.setAttribute("message", "Please login to view exams.");
        }
        return url;
    }

private String processCreateExam(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        String url = LOGIN_PAGE;
        HttpSession session = request.getSession();

        if ("Instructor".equals(session.getAttribute("role"))) {
            String examTitle = request.getParameter("examTitle");
            String subject = request.getParameter("subject");
            String categoryIdStr = request.getParameter("categoryId");
            String totalMarksStr = request.getParameter("totalMarks");
            String durationStr = request.getParameter("duration");
            request.removeAttribute("errorMessage");
            if (examTitle == null || examTitle.trim().isEmpty()) {
                request.setAttribute("errorMessage", "Exam title cannot be empty.");
                url = CREATE_EXAM_PAGE;
            } else if (subject == null || subject.trim().isEmpty()) {
                request.setAttribute("errorMessage", "Subject cannot be empty.");
                url = CREATE_EXAM_PAGE;
            } else if (categoryIdStr == null || categoryIdStr.trim().isEmpty()) {
                request.setAttribute("errorMessage", "Category is required.");
                url = CREATE_EXAM_PAGE;
            } else {
                if (!Character.isUpperCase(examTitle.charAt(0))) {
                    request.setAttribute("errorMessage", "Exam title must start with an uppercase letter.");
                    url = CREATE_EXAM_PAGE;
                } else if (!Character.isUpperCase(subject.charAt(0))) {
                    request.setAttribute("errorMessage", "Subject must start with an uppercase letter.");
                    url = CREATE_EXAM_PAGE;
                } else {
                    try {
                        int categoryId = Integer.parseInt(categoryIdStr);
                        int totalMarks = Integer.parseInt(totalMarksStr);
                        int duration = Integer.parseInt(durationStr);

                        if (totalMarks <= 0 || duration <= 0) {
                            request.setAttribute("errorMessage", "Total Marks and Duration must be greater than 0.");
                            url = CREATE_EXAM_PAGE;
                        } else {
                            ExamDTO exam = new ExamDTO(0, examTitle, subject, categoryId, totalMarks, duration);
                            examDAO.createExam(exam);
                            List<ExamDTO> exams = examDAO.getExamsByCategory(categoryId);
                            request.setAttribute("exams", exams);
                            url = "viewExamsByCategory.jsp?categoryId=" + categoryId;
                        }
                    } catch (NumberFormatException e) {
                        request.setAttribute("errorMessage", "Invalid number format for Category ID, Total Marks or Duration.");
                        url = CREATE_EXAM_PAGE;
                    }
                }
}
        }

        return url;
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
