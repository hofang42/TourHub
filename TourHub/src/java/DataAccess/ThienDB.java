/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Message;
import model.ReportError;
import model.User;

/**
 *
 * @author NOMNOM
 */
public class ThienDB implements DatabaseInfo {

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

    public void banAccount(int userId) throws Exception {
        String sql = "UPDATE [User] SET user_Status = 'Banned' WHERE user_Id = ?";
        try (Connection conn = getConnect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.executeUpdate();
        }
    }

    public void unbanAccount(int userId) throws Exception {
        String sql = "UPDATE [User] SET user_Status = 'verified' WHERE user_Id = ?";
        try (Connection conn = getConnect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.executeUpdate();
        }
    }

    public void cancelTour(String tourId) throws SQLException {
        String sql = "UPDATE Tour SET tour_Status = 'Cancelled' WHERE tour_Id = ?";
        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tourId);
            stmt.executeUpdate();
        }
    }

    public void approveTour(String tourId) throws Exception {
        String sql = "UPDATE Tour SET tour_Status = 'Approved' WHERE tour_Id = ?";
        try (Connection conn = getConnect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, tourId);
            pstmt.executeUpdate();
        }
    }

    public void deleteReport(int reportId) throws SQLException {
        String sql = "DELETE FROM ReportError WHERE report_Id = ?";
        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, reportId);
            stmt.executeUpdate();
        }
    }

    public User getUserById(int userId) throws Exception {
        User user = null;
        String sql = "SELECT * FROM [User] WHERE user_Id = ?";
        try (Connection conn = getConnect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
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
                java.util.Date created_At = rs.getDate(10);
                String avatar = rs.getString(11); // Get avatar

                user = new User(id, password, first_Name, last_Name, phone, email, address, created_At, user_Status, role, avatar);
            }
        }
        return user;
    }

    public ArrayList<ReportError> getAllReports() throws Exception {
        ArrayList<ReportError> reports = new ArrayList<>();
        String sql = "SELECT * FROM ReportError";
        try (Connection conn = getConnect(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                reports.add(new ReportError(rs.getInt("report_Id"), rs.getDate("report_Date"), rs.getString("report_Details"), rs.getString("report_Type"), rs.getInt("user_Id")));
            }
        }
        return reports;
    }

    public void cancelBooking(int bookingId) throws Exception {
        String sql = "UPDATE Booking SET book_Status = 'Cancelled', cancel_Date = GETDATE() WHERE book_Id = ? AND book_Status = 'Pending' ";
        try (Connection conn = getConnect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, bookingId);
            pstmt.executeUpdate();
        }
    }

    // Phương thức để lấy danh sách người dùng đang nhắn tin với người dùng hiện tại
    public static List<User> getActiveChatUsers(int userId) {
        List<User> activeUsers = new ArrayList<>();
        String query = "SELECT DISTINCT u.user_Id, u.first_Name, u.last_Name "
                + "FROM [User] u "
                + "JOIN Messages m ON (m.sender_id = u.user_Id OR m.receiver_id = u.user_Id) "
                + "WHERE (m.sender_id = ? OR m.receiver_id = ?) AND u.user_Id != ?";

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);
            stmt.setInt(2, userId);
            stmt.setInt(3, userId);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("user_id");
                String first_Name = rs.getString("first_Name");
                String last_Name = rs.getString("last_Name");

                // Tạo đối tượng User và thêm vào danh sách
                User activeUser = new User(id, first_Name, last_Name);
                activeUsers.add(activeUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return activeUsers;
    }
    
    // Phương thức để lấy kênh chat với admin ( 12 là id của admin trong database )
    public static List<User> getAdminChat() {
        List<User> activeUsers = new ArrayList<>();
        String query = "SELECT user_Id, first_Name, last_Name FROM [User] WHERE user_role = 'Admin'";

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("user_id");
                String first_Name = rs.getString("first_Name");
                String last_Name = rs.getString("last_Name");

                // Tạo đối tượng User và thêm vào danh sách
                User activeUser = new User(id, first_Name, last_Name);
                activeUsers.add(activeUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return activeUsers;
    }

    // Method to insert a message into the database
    public boolean insertMessage(Message message) {
        boolean isInserted = false;
        String sql = "INSERT INTO Messages (sender_id, receiver_id, message_text, message_time) VALUES (?, ?, ?, GETDATE())";

        try (Connection connection = getConnect(); PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, message.getSenderId());
            statement.setInt(2, message.getReceiverId()); // Include receiverId
            statement.setString(3, message.getMessageText());
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                isInserted = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isInserted;
    }

    // Phương thức để lấy các tin nhắn giữa hai người dùng
    public static List<Message> getChatMessages(int userId1, int userId2) {
        List<Message> messages = new ArrayList<>();
        String query = "SELECT m.message_id, m.sender_id, m.receiver_id, m.message_text, m.message_time "
                + "FROM Messages m "
                + "WHERE (m.sender_id = ? AND m.receiver_id = ?) OR (m.sender_id = ? AND m.receiver_id = ?) "
                + "ORDER BY m.message_time ASC";

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId1);
            stmt.setInt(2, userId2);
            stmt.setInt(3, userId2);
            stmt.setInt(4, userId1);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int messageId = rs.getInt("message_id");
                int senderId = rs.getInt("sender_id");
                int receiverId = rs.getInt("receiver_id");
                String messageText = rs.getString("message_text");
                Timestamp messageTime = rs.getTimestamp("message_time");

                // Tạo đối tượng Message và thêm vào danh sách
                Message message = new Message(messageId, senderId, receiverId, messageText, messageTime);
                messages.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return messages;
    }

}
