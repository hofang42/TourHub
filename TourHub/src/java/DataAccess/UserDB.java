package DataAccess;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.sql.*;
import java.util.ArrayList;
import model.User;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public boolean registerUser(User user) {
        if (isUsernameExists(user.getUsername()) || isEmailExists(user.getEmail())) {
            return false; // Username or email already exists
        }

        String sql = "INSERT INTO [User] (username, password, first_Name, last_Name, phone, email, address, created_At, user_Status, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFirstName());
            ps.setString(4, user.getLastName());
            ps.setString(5, user.getPhone());
            ps.setString(6, user.getEmail());
            ps.setString(7, user.getAddress());
            ps.setTimestamp(8, new java.sql.Timestamp(user.getCreatedAt().getTime()));
            ps.setString(9, user.getUserStatus());
            ps.setString(10, user.getRole());

            ps.executeUpdate();
            return true;
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
        String sql = "SELECT * FROM [User] WHERE email = ? AND (password = ? OR password = '')"; // Handle no password for Google users
        try (Connection conn = getConnect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            if (password != null) {
                ps.setString(2, password);
            } else {
                ps.setString(2, ""); // Check for Google users (empty password)
            }
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("userId"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getTimestamp("createdAt"),
                        rs.getString("userStatus"), // Fetch userStatus
                        rs.getString("role") // Fetch role
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isUsernameExists(String username) {
        String sql = "SELECT COUNT(*) FROM [User] WHERE username = ?";
        try (Connection conn = getConnect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return true; // Username exists
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isEmailExists(String email) {
        String sql = "SELECT COUNT(*) FROM [User] WHERE email = ?";
        try (Connection conn = getConnect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return true; // Email exists
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //Kiem tra email co trong database hay la khong
    public boolean checkEmailExists(String email) {
        boolean exists = false;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT email FROM [User] WHERE email = ?";
        try {
            conn = getConnect();
            if (conn != null) {
                ps = conn.prepareStatement(query);
                ps.setString(1, email);
                rs = ps.executeQuery();

                if (rs.next()) {
                    exists = true;  // Email co trong database
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return exists;
    }

    public void updateUserStatusToVerified(String email) {
        String sql = "UPDATE users SET userStatus = 'verified' WHERE email = ?";
        try (Connection con = getConnect(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//-------------------------------------------------
    //Lấy all user ra
    public List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM [User]";
        try (Connection con = getConnect(); PreparedStatement stmt = con.prepareStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getInt("user_Id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setFirstName(resultSet.getString("first_Name"));
                user.setLastName(resultSet.getString("last_Name"));
                user.setPhone(resultSet.getString("phone"));
                user.setEmail(resultSet.getString("email"));
                user.setAddress(resultSet.getString("address"));
                user.setCreatedAt(resultSet.getDate("created_At"));
                user.setUserStatus(resultSet.getString("user_Status"));
                user.setRole(resultSet.getString("role"));
                users.add(user);
            }
            System.out.println(users);
            return users;
        } catch (Exception ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public User getUser(int userId) {
        User user = null;
        // Fixed: Added space after "createdAt"
        String query = "SELECT userId, username, password, userStatus, role, "
                + "firstName, lastName, email, phone, address, createdAt "
                + "FROM [User] WHERE userId = ?";

        try (Connection con = getConnect(); PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt(1);
                String username = rs.getString(2);
                String password = rs.getString(3);
                String userStatus = rs.getString(4);
                String role = rs.getString(5);
                String firstName = rs.getString(6);
                String lastName = rs.getString(7);
                String email = rs.getString(8);
                String phone = rs.getString(9);
                String address = rs.getString(10);
                Date createdAt = rs.getDate(11);

                user = new User(id, username, password, firstName, lastName, phone, email, address, createdAt, userStatus, role);
            }

        } catch (Exception ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    //Thêm user mới
    public void insertUser(User user) {
        String sql = "INSERT INTO [User] (username, password, role, email) VALUES (?, ?, ?, ?)";

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
        String query = "UPDATE [User] SET password=? WHERE UserId=?";

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

    public boolean updateEmail(int userId, String newEmail) {
        String query = "UPDATE [User] SET email=? WHERE UserId=?";

        try (Connection con = getConnect(); PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, newEmail);
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
        String sql = "UPDATE [User] SET "
                + "firstName =?, lastName=?, email=?, phone=?, address=?, username=?"
                + "from [User] WHERE userId=?";
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
        User user = (User) session.getAttribute("currentUser");
        return user; // or throw an exception if user not found
    }

}
