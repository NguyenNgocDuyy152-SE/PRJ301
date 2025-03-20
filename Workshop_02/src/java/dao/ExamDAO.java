package dao;

import dto.ExamCategoryDTO;
import dto.ExamDTO;
import dto.QuestionDTO;
import dto.UserDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;
import static utils.DBUtils.getConnection;

public class ExamDAO {

    // Get all exam categories
    public List<ExamCategoryDTO> getAllCategories() throws ClassNotFoundException {
        List<ExamCategoryDTO> categories = new ArrayList<>();
        String query = "SELECT * FROM tblExamCategories";

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(query);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ExamCategoryDTO category = new ExamCategoryDTO(
                        rs.getInt("category_id"),
                        rs.getString("category_name"),
                        rs.getString("description")
                );
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }

    // Get exam categories by ID
    public ExamCategoryDTO getCategoryById(int categoryId) throws ClassNotFoundException {
        ExamCategoryDTO category = null;
        String query = "SELECT * FROM tblExamCategories WHERE category_id = ?";

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, categoryId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    category = new ExamCategoryDTO(
                            rs.getInt("category_id"),
                            rs.getString("category_name"),
                            rs.getString("description")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return category;
    }
    // Trong ExamDAO.java

    public UserDTO readById(String username) throws ClassNotFoundException {
        UserDTO user = null;
        String query = "SELECT * FROM tblUsers WHERE username = ?";

        try (Connection conn = getConnection(); PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                user = new UserDTO();
                user.setUsername(rs.getString("username"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    // Get exams by category ID
    public List<ExamDTO> getExamsByCategory(int categoryId) throws ClassNotFoundException {
        List<ExamDTO> exams = new ArrayList<>();
        String query = "SELECT * FROM tblExams WHERE category_id = ?";

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, categoryId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ExamDTO exam = new ExamDTO(
                            rs.getInt("exam_id"),
                            rs.getString("exam_title"),
                            rs.getString("subject"),
                            rs.getInt("category_id"),
                            rs.getInt("total_marks"),
                            rs.getInt("duration")
                    );
                    exams.add(exam);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exams;
    }

    // Get exam by ID
    public ExamDTO getExamById(int examId) throws ClassNotFoundException {
        ExamDTO exam = null;
        String query = "SELECT * FROM tblExams WHERE exam_id = ?";

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, examId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    exam = new ExamDTO(
                            rs.getInt("exam_id"),
                            rs.getString("exam_title"),
                            rs.getString("subject"),
                            rs.getInt("category_id"),
                            rs.getInt("total_marks"),
                            rs.getInt("duration")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exam;
    }

    // Create a new exam
public boolean createExam(ExamDTO exam) throws ClassNotFoundException {
    String sql = "INSERT INTO tblExams (exam_title, subject, category_id, total_marks, duration) VALUES (?, ?, ?, ?, ?)";

    try (Connection conn = DBUtils.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, exam.getExamTitle());
        ps.setString(2, exam.getSubject());
        ps.setInt(3, exam.getCategoryId());
        ps.setInt(4, exam.getTotalMarks());
        ps.setInt(5, exam.getDuration());

        int rowsInserted = ps.executeUpdate();
        return rowsInserted > 0;  // Nếu có ít nhất một dòng được chèn, trả về true

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}


    // Add question to exam
    public void addQuestion(QuestionDTO question) throws ClassNotFoundException {
        String query = "INSERT INTO tblQuestions (exam_id, question_text, option_a, option_b, option_c, option_d, correct_option) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, question.getExamId());
            ps.setString(2, question.getQuestionText());
            ps.setString(3, question.getOptionA());
            ps.setString(4, question.getOptionB());
            ps.setString(5, question.getOptionC());
            ps.setString(6, question.getOptionD());
            ps.setString(7, question.getCorrectOption());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get questions by exam ID
    public List<QuestionDTO> getQuestionsByExamId(int examId) throws ClassNotFoundException {
        List<QuestionDTO> questions = new ArrayList<>();
        String query = "SELECT * FROM tblQuestions WHERE exam_id = ?";

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, examId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    QuestionDTO question = new QuestionDTO(
                            rs.getInt("question_id"),
                            rs.getInt("exam_id"),
                            rs.getString("question_text"),
                            rs.getString("option_a"),
                            rs.getString("option_b"),
                            rs.getString("option_c"),
                            rs.getString("option_d"),
                            rs.getString("correct_option")
                    );
                    questions.add(question);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return questions;
    }

    // Update exam details
    public void updateExam(ExamDTO exam) throws ClassNotFoundException {
        String query = "UPDATE tblExams SET exam_title = ?, subject = ?, category_id = ?, total_marks = ?, duration = ? WHERE exam_id = ?";

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, exam.getExamTitle());
            ps.setString(2, exam.getSubject());
            ps.setInt(3, exam.getCategoryId());
            ps.setInt(4, exam.getTotalMarks());
            ps.setInt(5, exam.getDuration());
            ps.setInt(6, exam.getExamId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update question details
    public void updateQuestion(QuestionDTO question) throws ClassNotFoundException {
        String query = "UPDATE tblQuestions SET question_text = ?, option_a = ?, option_b = ?, option_c = ?, option_d = ?, correct_option = ? WHERE question_id = ?";

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, question.getQuestionText());
            ps.setString(2, question.getOptionA());
            ps.setString(3, question.getOptionB());
            ps.setString(4, question.getOptionC());
            ps.setString(5, question.getOptionD());
            ps.setString(6, question.getCorrectOption());
            ps.setInt(7, question.getQuestionId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete exam by ID
    public void deleteExam(int examId) throws ClassNotFoundException {
        String query = "DELETE FROM tblExams WHERE exam_id = ?";

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, examId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete question by ID
    public void deleteQuestion(int questionId) throws ClassNotFoundException {
        String query = "DELETE FROM tblQuestions WHERE question_id = ?";

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, questionId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
