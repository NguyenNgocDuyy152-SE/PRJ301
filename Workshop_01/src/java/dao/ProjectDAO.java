
package dao;

import dto.ProjectDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;


public class ProjectDAO {
    
    public boolean create(ProjectDTO project) {
        String sql = "INSERT INTO tblStartupProjects (project_name, Description, Status, estimated_launch) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, project.getProjectName());
            ps.setString(2, project.getDescription());
            ps.setString(3, project.getStatus());
            ps.setDate(4, new java.sql.Date(project.getEstimatedLaunch().getTime()));
            return ps.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public List<ProjectDTO> readAll() {
        List<ProjectDTO> projects = new ArrayList<>();
        String sql = "SELECT * FROM tblStartupProjects";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                projects.add(new ProjectDTO(
                    rs.getInt("project_id"),
                    rs.getString("project_name"),
                    rs.getString("Description"),
                    rs.getString("Status"),
                    rs.getDate("estimated_launch")
                ));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return projects;
    }
    
    public boolean updateStatus(int projectId, String newStatus) {
        String sql = "UPDATE tblStartupProjects SET Status = ? WHERE project_id = ?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, newStatus);
            ps.setInt(2, projectId);
            return ps.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public List<ProjectDTO> search(String searchTerm) {
        List<ProjectDTO> projects = new ArrayList<>();
        String sql = "SELECT * FROM tblStartupProjects WHERE project_name LIKE ?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + searchTerm + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    projects.add(new ProjectDTO(
                        rs.getInt("project_id"),
                        rs.getString("project_name"),
                        rs.getString("Description"),
                        rs.getString("Status"),
                        rs.getDate("estimated_launch")
                    ));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return projects;
    }
    public ProjectDTO readById(int projectId) {
    String sql = "SELECT * FROM tblStartupProjects WHERE project_id = ?";
    try (Connection conn = DBUtils.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, projectId);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return new ProjectDTO(
                    rs.getInt("project_id"),
                    rs.getString("project_name"),
                    rs.getString("Description"),
                    rs.getString("Status"),
                    rs.getDate("estimated_launch")
                );
            }
        }
    } catch (SQLException | ClassNotFoundException e) {
        e.printStackTrace();
    }
    return null;
}

}
