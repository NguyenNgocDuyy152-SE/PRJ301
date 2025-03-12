/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author anhqu
 */
import dto.ExamCategoryDTO;
import dto.ExamDTO;
import utils.DBUtils;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExamDAO {
    // Lấy danh sách danh mục kỳ thi

    public List<ExamCategoryDTO> getAllExamCategories() {
        List<ExamCategoryDTO> categories = new ArrayList<>();
        String sql = "SELECT Category_ID, Category_Name, Description FROM tblExamCategories";

        try (Connection conn = DBUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ExamCategoryDTO category = new ExamCategoryDTO(
                        rs.getInt("Category_ID"),
                        rs.getString("Category_Name"),
                        rs.getString("Description")
                );
                categories.add(category);
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi lấy danh mục kỳ thi: " + e.getMessage());
        }
        return categories;
    }

// Lấy danh sách tất cả danh mục kỳ thi
public List<String> getExamCategories() {
        List<String> categories = new ArrayList<>();
        String sql = "SELECT Category_Name FROM tblExamCategories";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                categories.add(rs.getString("Category_Name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categories;
    }

    // Lấy danh sách kỳ thi theo danh mục
    public List<ExamDTO> getExamsByCategory(int categoryId) {
        List<ExamDTO> exams = new ArrayList<>();
        String sql = "SELECT * FROM tblExams WHERE Category_ID = ?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, categoryId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                exams.add(new ExamDTO(
                        rs.getInt("Exam_ID"),
                        rs.getString("Exam_Title"),
                        rs.getString("Subject"),
                        rs.getInt("Total_Marks"),
                        rs.getInt("Duration"),
                        rs.getInt("Category_ID")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exams;
    }

    // Thêm một kỳ thi mới
    public boolean createExam(ExamDTO exam) {
        String sql = "INSERT INTO tblExams (Exam_Title, Subject, Category_ID, Total_Marks, Duration) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, exam.getExamTitle());
            ps.setString(2, exam.getSubject());
            ps.setInt(3, exam.getCategoryId());
            ps.setInt(4, exam.getTotalMarks());
            ps.setInt(5, exam.getDuration());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Thêm câu hỏi vào kỳ thi
    public boolean addQuestion(int examId, String questionText, String optionA, String optionB, String optionC, String optionD, char correctOption) {
        String sql = "INSERT INTO tblQuestions (Exam_ID, Question_Text, Option_A, Option_B, Option_C, Option_D, Correct_Option) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, examId);
            ps.setString(2, questionText);
            ps.setString(3, optionA);
            ps.setString(4, optionB);
            ps.setString(5, optionC);
            ps.setString(6, optionD);
            ps.setString(7, String.valueOf(correctOption));
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Sinh viên làm bài thi và xem kết quả
    public int submitExam(int examId, List<Character> answers) {
        String sql = "SELECT Correct_Option FROM tblQuestions WHERE Exam_ID = ?";
        int score = 0;
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, examId);
            ResultSet rs = ps.executeQuery();
            int index = 0;
            while (rs.next() && index < answers.size()) {
                if (rs.getString("Correct_Option").charAt(0) == answers.get(index)) {
                    score++;
                }
                index++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return score;
    }
}
