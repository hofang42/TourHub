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
import utils.Encrypt;

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
        if (isEmailExists(user.getEmail())) {
            return false; // Email already exists
        }
        String sql = "INSERT INTO [User] (password, first_Name, last_Name, phone, email, address, created_At, user_Status, role, avatar) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getPassword());
            ps.setString(2, user.getFirst_Name());
            ps.setString(3, user.getLast_Name());
            ps.setString(4, user.getPhone());
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getAddress());
            ps.setTimestamp(7, new java.sql.Timestamp(user.getCreated_At().getTime()));
            ps.setString(8, user.getUser_Status());
            ps.setString(9, user.getRole());
            ps.setString(10, user.getAvatar()); // Set avatar field

            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public User authenticate(String email, String password) {
        String sql = "SELECT * FROM [User] WHERE email = ?";
        try (Connection conn = getConnect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String storedPassword = rs.getString("password");

                // If password is null (Google login), bypass password check
                if (password == null || storedPassword.equals(Encrypt.toSHA256(password))) {
                    return new User(
                            rs.getInt("user_Id"),
                            rs.getString("password"),
                            rs.getString("first_Name"),
                            rs.getString("last_Name"),
                            rs.getString("phone"),
                            rs.getString("email"),
                            rs.getString("address"),
                            rs.getTimestamp("created_At"),
                            rs.getString("user_Status"),
                            rs.getString("role"),
                            rs.getString("avatar")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
                    exists = true;  // Email exists
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


    public void updateUser_StatusToVerified(String email) {
        String sql = "UPDATE [User] SET user_Status = 'verified' WHERE email = ?";
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

    public User getUserOld(int userId) {
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
    public User getUser(int user_Id) {
        User user = null;
        String query = "SELECT user_Id, password, user_Status, role, first_Name, last_Name, email, phone, address, created_At, avatar "
                + "FROM [User] WHERE user_Id = ?";

        try (Connection con = getConnect(); PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, user_Id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt(1);
                String password = rs.getString(2);
                String user_Status = rs.getString(3);
                String role = rs.getString(4);
                String first_Name = rs.getString(5);
                String last_Name = rs.getString(6);
                String email = rs.getString(7);
                String phone = rs.getString(8);
                String address = rs.getString(9);
                Date created_At = rs.getDate(10);
                String avatar = rs.getString(11); // Get avatar

                user = new User(id, password, first_Name, last_Name, phone, email, address, created_At, user_Status, role, avatar);
            }
        } catch (Exception ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    //Thêm user mới
    public void insertUser(User user) {
        String sql = "INSERT INTO [User] (password, role, email) VALUES (?, ?, ?)";

        try (Connection con = getConnect(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, user.getPassword());
            stmt.setString(2, user.getRole());
            stmt.setString(3, user.getEmail());
            stmt.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Change password
    public boolean updatePassword(int user_Id, String newPassword) {
        String query = "UPDATE [User] SET password=? WHERE user_Id=?";

        try (Connection con = getConnect(); PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, newPassword);
            stmt.setInt(2, user_Id);
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
            stmt.setInt(2, user_Id);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated == 0) {
                throw new SQLException("Update failed, no rows affected.");
            }
            return true;
        } catch (Exception ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    //Edit profile function
    public boolean updateUser(User user) {
        boolean result = false;
        String sql = "UPDATE [User] SET "
                + "first_Name =?, last_Name=?, email=?, phone=?, address=?, avatar=? "
                + "WHERE user_Id=?";
        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getFirst_Name());
            stmt.setString(2, user.getLast_Name());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPhone());
            stmt.setString(5, user.getAddress());
            stmt.setString(6, user.getAvatar()); // Set avatar field
            stmt.setInt(7, user.getUser_Id());

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
