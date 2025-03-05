/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import dto.UserDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;
/**
 *
 * @author Huy
 */

public class UserDAO {

    public static UserDTO readById(String username) {
        String sql = "SELECT * FROM tblUsers WHERE username = ?";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new UserDTO(
                        rs.getString("Username"),
                        rs.getString("Name"),
                        rs.getString("Password"),
                        rs.getString("Role")
                    );
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<UserDTO> readAll() {
        List<UserDTO> users = new ArrayList<>();
        String sql = "SELECT * FROM tblUsers";
        try (Connection conn = DBUtils.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                users.add(new UserDTO(
                    rs.getString("Username"),
                    rs.getString("Name"),
                    rs.getString("Password"),
                    rs.getString("Role")
                ));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return users;
    }
}
