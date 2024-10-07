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
import model.Booking;
import model.Discount;
import model.Review;
import model.Tour;

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

        String sql = "INSERT INTO [User] (password, first_Name, last_Name, phone, email, address, created_At, user_Status, role, avatar) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getPassword());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getPhone());
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getAddress());
            ps.setTimestamp(7, new java.sql.Timestamp(user.getCreatedAt().getTime()));
            ps.setString(8, user.getUser_Status());
            ps.setString(9, user.getRole());
            ps.setString(10, user.getAvatar());

            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void verifyUser(String email) {
        String sql = "UPDATE [User] SET user_Status = 'verified' WHERE email = ?";
        try (Connection conn = getConnect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User authenticate(String email, String password) {
        String sql = "SELECT * FROM [User] WHERE email = ? AND (password = ? OR password = '')"; // Handle no password for Google users
        try (Connection conn = getConnect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, password != null ? password : "");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
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

    public boolean checkEmailExists(String email) {
        String query = "SELECT email FROM [User] WHERE email = ?";
        try (Connection conn = getConnect(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean updatePassword(int userId, String newPassword) {
        String query = "UPDATE [User] SET password=? WHERE user_Id=?";
        try (Connection con = getConnect(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, newPassword);
            ps.setInt(2, userId);
            return ps.executeUpdate() > 0;
        } catch (Exception ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean updateUser(User user) {
        String sql = "UPDATE [User] SET first_Name = ?, last_Name = ?, email = ?, phone = ?, address = ?, avatar = ? WHERE user_Id = ?";
        try (Connection conn = getConnect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPhone());
            ps.setString(5, user.getAddress());
            ps.setString(6, user.getAvatar());
            ps.setInt(7, user.getUserId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public User getUser(int userId) {
        String query = "SELECT user_Id, password, user_Status, role, first_Name, last_Name, email, phone, address, created_At, avatar FROM [User] WHERE user_Id = ?";
        try (Connection con = getConnect(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
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
        } catch (SQLException ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean updateUserStatusToVerified(String email) {
        String query = "UPDATE [User] SET user_Status = 'verified' WHERE email = ?";
        try (Connection con = getConnect(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, email);
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated == 0) {
                throw new SQLException("Update failed, no rows affected. Email might not exist.");
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean updateEmail(int userId, String newEmail) {
        String query = "UPDATE [User] SET email = ? WHERE user_Id = ?";

        try (Connection con = getConnect(); PreparedStatement stmt = con.prepareStatement(query)) {

            // Set new email and user_Id
            stmt.setString(1, newEmail);
            stmt.setInt(2, userId);

            // Execute the update query
            int rowsUpdated = stmt.executeUpdate();

            // Check if the update was successful
            if (rowsUpdated == 0) {
                throw new SQLException("Update failed, no rows affected.");
            }
            return true;  // Update successful
        } catch (Exception ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;  // Update failed
        }
    }

    public User getUserFromSession(HttpSession session) {
        return (User) session.getAttribute("currentUser");
    }

    public Integer getProviderIdFromUserId(int userId) throws SQLException {
        String query = "SELECT c.company_Id FROM [User] u JOIN Company c ON u.user_Id = c.user_Id WHERE u.user_Id = ?";
        Integer providerId = null;

        try (Connection connection = getConnect(); PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, userId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    providerId = rs.getInt("company_Id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // or handle exception as needed
        }

        return providerId; // This will return null if no providerId is found
    }

    // Get all discounts
    public static List<Discount> getAllDiscounts() {
        List<Discount> discounts = new ArrayList<>();
        String sql = "SELECT * FROM [Discount]";

        try (Connection conn = getConnect(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int discountId = rs.getInt("discount_Id");
                String code = rs.getString("code");
                int quantity = rs.getInt("quantity");
                double percentDiscount = rs.getDouble("percent_Discount");
                Date startDay = rs.getDate("start_Day");
                Date endDay = rs.getDate("end_Day");
                String require = rs.getString("require");
                String tourId = rs.getString("tour_Id");

                Discount discount = new Discount(discountId, code, quantity, percentDiscount, startDay, endDay, require, tourId);
                discounts.add(discount);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return discounts;
    }

    // Get discount by ID
    public Discount getDiscountById(int discountId) {
        String sql = "SELECT * FROM [Discount] WHERE discount_Id = ?";
        try (Connection conn = getConnect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, discountId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Discount(
                        rs.getInt("discount_Id"),
                        rs.getString("code"),
                        rs.getInt("quantity"),
                        rs.getDouble("percent_Discount"),
                        rs.getDate("start_Day"),
                        rs.getDate("end_Day"),
                        rs.getString("require"),
                        rs.getString("tour_Id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Insert a new discount
    public boolean insertDiscount(Discount discount) {
        if (isDiscountCodeExists(discount.getCode())) {
            System.out.println("Lỗi: Mã giảm giá đã tồn tại.");
            return false;
        }
        if (!isTourIdExists(discount.getTourId())) {
            System.out.println("Lỗi: tourId không tồn tại.");
            return false;
        }

        String sql = "INSERT INTO [Discount] (code, quantity, percent_Discount, start_Day, end_Day, require, tour_Id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, discount.getCode());
            ps.setInt(2, discount.getQuantity());
            ps.setDouble(3, discount.getPercentDiscount());
            ps.setDate(4, new java.sql.Date(discount.getStartDay().getTime()));
            ps.setDate(5, new java.sql.Date(discount.getEndDay().getTime()));
            ps.setString(6, discount.getRequire());
            ps.setString(7, discount.getTourId());

            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Update a discount
    public boolean updateDiscount(Discount discount) {
        String sql = "UPDATE [Discount] SET code=?, quantity=?, percent_Discount=?, start_Day=?, end_Day=?, require=?, tour_Id=? WHERE discount_Id=?";
        try (Connection conn = getConnect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, discount.getCode());
            ps.setInt(2, discount.getQuantity());
            ps.setDouble(3, discount.getPercentDiscount());
            ps.setDate(4, new java.sql.Date(discount.getStartDay().getTime()));
            ps.setDate(5, new java.sql.Date(discount.getEndDay().getTime()));
            ps.setString(6, discount.getRequire());
            ps.setString(7, discount.getTourId());
            ps.setInt(8, discount.getDiscountId());

            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Delete a discount
    public boolean deleteDiscount(int discountId) {
        String sql = "DELETE FROM [Discount] WHERE discount_Id=?";
        try (Connection conn = getConnect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, discountId);
            int rowsDeleted = ps.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isDiscountCodeExists(String code) {
        String sql = "SELECT COUNT(*) FROM [Discount] WHERE code = ?";
        try (Connection conn = getConnect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isTourIdExists(String tourId) {
        String sql = "SELECT COUNT(*) FROM [Tour] WHERE tour_Id = ?";
        try (Connection conn = getConnect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tourId);
            ResultSet rs = ps.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean addReview(String comment, int ratingStar, int cusID, String tourId) {
        String sql = "INSERT INTO Review (comment, rating_Star, cus_Id, tour_Id) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnect(); PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, comment);
            statement.setInt(2, ratingStar);
            statement.setInt(3, cusID);
            statement.setString(4, tourId);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

public static boolean hasCustomerBookedTour(int customerId, String tourId) {
    String sql = "SELECT COUNT(*) FROM Booking WHERE tour_Id = ? AND cus_Id = ?";
    try (Connection conn = getConnect(); PreparedStatement statement = conn.prepareStatement(sql)) {
        statement.setString(1, tourId);
        statement.setInt(2, customerId);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt(1) > 0;  // Return true if the count is greater than 0
        }
        return false;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}


    public List<Booking> getBookedToursWithoutReview(int userId) {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT book_Id, book_Date, slot_Order, total_Cost, tour_Id "
                + "FROM Booking "
                + "WHERE cus_Id = ? AND book_Status = 'Booked' "
                + "AND NOT EXISTS (SELECT 1 FROM Review WHERE tour_Id = Booking.tour_Id AND user_Id = ?)";

        try (Connection conn = getConnect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, userId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Booking booking = new Booking();
                    booking.setBookId(rs.getInt("book_Id"));
                    booking.setBookDate(rs.getDate("book_Date"));
                    booking.setSlotOrder(rs.getInt("slot_Order"));
                    booking.setTotalCost(rs.getFloat("total_Cost"));
                    booking.setTourId(rs.getString("tour_Id"));
                    bookings.add(booking);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    public boolean submitReview(Review review) {
        String sql = "INSERT INTO Review (comment, rating_Star, user_Id, tour_Id) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, review.getComment());
            stmt.setInt(2, review.getRating_Star());
            stmt.setInt(3, review.getUser_Id());
            stmt.setString(4, review.getTour_Id());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0; // Trả về true nếu lưu thành công
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Trả về false nếu có lỗi
        }
    }

    public Tour getTourById(String tourId) {
        Tour tour = null;
        String sql = "SELECT tour_Id, tour_Name FROM Tour WHERE tour_Id = ?";

        try (Connection conn = getConnect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tourId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    tour = new Tour();
                    tour.setTourId(rs.getString("tour_Id"));
                    tour.setTourName(rs.getString("tour_Name"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tour;
    }
    public String getTourImageUrl(String tourId) {
        String imageUrl = null;
        String sql = "SELECT tour_Img FROM Tour WHERE tour_Id = ?";

            try (Connection conn = getConnect(); PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setString(1, tourId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                imageUrl = rs.getString("tour_Img");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return imageUrl != null ? imageUrl : "assests/images/default-tour.jpg";
    }

    public static void main(String[] args) {
        UserDB userDB = new UserDB();
        User user = userDB.getUser(1);
        if (user != null) {
            System.out.println(user.toString());
        } else {
            System.out.println("User not found.");
        }
    }
}
