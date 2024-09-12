package DAO;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

public class UserDB implements DatabaseInfo {

    public static Connection getConnect() {
        try {
            Class.forName(DRIVERNAME);
        } catch (ClassNotFoundException e) {
            System.out.println("Error loading driver" + e);
        }
        try {
            Connection con = DriverManager.getConnection(DBURL, USERDB, PASSDB);
            return con;
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return null;
    }

    //Lấy all user ra
    public User getUsers(String username, String password) {
        User user = null;
        String query = "Select username, password, userStatus, role, "
                + "firstName, lastName, email, phone, address, createdAt"
                + "from Users where username =? and password=? ";

        try (Connection con = getConnect(); PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt(1);
                username = rs.getString(2);
                password = rs.getString(3);
                String userStatus = rs.getString(4);
                String role = rs.getString(5);
                String firstName = rs.getString(6);
                String lastName = rs.getString(7);
                String email = rs.getString(8);
                String phone = rs.getString(9);
                String address = rs.getString(10);
                String createdAt = rs.getString(11);
                user = new User(id, username, password, userStatus, role, firstName, lastName, email, phone, address, createdAt);
            }

        } catch (Exception ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    //Thêm user mới
    public void insertUser(User user) {
        String sql = "INSERT INTO Users (username, password, role, email) VALUES (?, ?, ?, ?)";

        try (Connection con = getConnect(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getRole());
            stmt.setString(5, user.getEmail());
            stmt.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Change password
    public boolean updatePassword(int userId, String newPassword) {
        String query = "UPDATE Users SET password=? WHERE UserId=?";

        try (Connection con = getConnect(); PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, newPassword);
            stmt.setInt(2, userId);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated == 0) {
                throw new SQLException("Update failed, no rows affected.");
            }
            return true;
        } catch (Exception ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
            return false; // Password update failed
        }
    }

    //Edit profile function
    public boolean updateUser(User user) {
        boolean result = false;
        String sql = "UPDATE Users SET "
                + "firstName =?, lastName=?, email=?, phone=?, address=?, username=?"
                + "from Users WHERE userId=?";
        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPhone());
            stmt.setString(5, user.getAddress());
            stmt.setString(6, user.getUsername());
            stmt.setInt(7, user.getUserId());

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public User getUserFromSession(HttpSession session, HttpServletRequest request) {
        String userName = (String) session.getAttribute("user");
        String pass = (String) session.getAttribute("pass");

        if (userName == null || pass == null) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("user")) {
                        userName = cookie.getValue();
                    }
                    if (cookie.getName().equals("pass")) {
                        pass = cookie.getValue();
                    }
                }
            }
        }

        if (userName != null && pass != null) {
            return getUsers(userName, pass);
        }
        return null;
    }

}
