package DAO;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;
import model.User;
import java.util.Date;

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
                Date createdAt = rs.getDate(11);
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

    public boolean registerUser(User user) {
        String sql = "INSERT INTO Users(username, password, firstName, lastName, phone, email, address, createdAt, verified) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFirstName());
            ps.setString(4, user.getLastName());
            ps.setString(5, user.getPhone());
            ps.setString(6, user.getEmail());
            ps.setString(7, user.getAddress());
            ps.setTimestamp(8, new Timestamp(new Date().getTime()));
            ps.setBoolean(9, false);  // Set user as unverified
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void verifyUser(String email) {
        String sql = "UPDATE [User] SET verified = ? WHERE email = ?";
        try (Connection conn = getConnect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setBoolean(1, true);
            ps.setString(2, email);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User authenticate(String email, String password) {
        String sql = "SELECT * FROM Users WHERE email = ? AND password = ?";
        try (Connection conn = getConnect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("userId"), rs.getString("username"),
                        rs.getString("password"), rs.getString("userStatus"), rs.getString("role"),
                        rs.getString("firstName"),
                        rs.getString("lastName"), rs.getString("email"),
                        rs.getString("phone"), rs.getString("address"),
                        rs.getTimestamp("createdAt"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
