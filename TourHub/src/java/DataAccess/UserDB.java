package DataAccess;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.sql.*;
import java.util.ArrayList;

import java.util.Arrays;

import model.User;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Booking;
import model.Discount;
import model.Review;
import model.Tour;

import model.Tour;
import model.TourOption;

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
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM [User]";
        try (Connection con = getConnect(); PreparedStatement stmt = con.prepareStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setUser_Id(resultSet.getInt("user_Id"));
                user.setPassword(resultSet.getString("password"));
                user.setFirst_Name(resultSet.getString("first_Name"));
                user.setLast_Name(resultSet.getString("last_Name"));
                user.setPhone(resultSet.getString("phone"));
                user.setEmail(resultSet.getString("email"));
                user.setAddress(resultSet.getString("address"));
                user.setCreated_At(resultSet.getDate("created_At"));
                user.setUser_Status(resultSet.getString("user_Status"));
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
        }
        return false;
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
                return result;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
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
                    booking.setBook_Id(rs.getInt("book_Id"));
                    booking.setBook_Date(rs.getDate("book_Date"));
                    booking.setSlot_Order(rs.getInt("slot_Order"));
                    booking.setTotal_Cost(rs.getBigDecimal("total_Cost"));
                    booking.setTour_Id(rs.getString("tour_Id"));
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
                    tour.setTour_Id(rs.getString("tour_Id"));
                    tour.setTour_Name(rs.getString("tour_Name"));
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

        try (Connection conn = getConnect(); PreparedStatement ps = conn.prepareStatement(sql)) {

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

    public User getUserFromSession(HttpSession session, HttpServletRequest request) {
        User user = (User) session.getAttribute("currentUser");
        return user; // or throw an exception if user not found
    }

    public List<Tour> getAll(String sortOrder, String location, int minPrice, int maxPrice) {
        List<Tour> list = new ArrayList<>();
        String sql = "SELECT * FROM Tour WHERE 1=1";

        // Add location filter
        if (location != null && !location.equals("All")) {
            sql += " AND location = ?";
        }

        // Add price range filter
        if (minPrice > 0 || maxPrice > 0) {
            sql += " AND price BETWEEN ? AND ?";
        }

        // Sort the results
        switch (sortOrder) {
            case "price-asc":
                sql += " ORDER BY price ASC";
                break;
            case "price-desc":
                sql += " ORDER BY price DESC";
                break;
            case "rating":
                sql += " ORDER BY average_Review_Rating DESC";
                break;
            case "popularity":
            default:
                sql += " ORDER BY purchases_Time DESC";
                break;
        }

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            int index = 1;

            // Set location filter
            if (location != null && !location.equals("All")) {
                stmt.setString(index++, location);
            }

            // Set price range filter
            if (minPrice > 0 || maxPrice > 0) {
                stmt.setInt(index++, minPrice);
                stmt.setInt(index++, maxPrice);
            }

            // Execute the query and process results
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // Split the image URLs by ";"
                String imageUrlStr = rs.getString("tour_Img");
                List<String> imageUrlList = Arrays.asList(imageUrlStr.split(";"));

                // Create a new Tour object using the data from the result set
                Tour t = new Tour(
                        rs.getString("tour_Id"), // tourId
                        rs.getString("tour_Name"), // tourName
                        rs.getString("tour_Description"), // tourDescription
                        rs.getDate("start_Date"), // startDate
                        rs.getDate("end_Date"), // endDate
                        rs.getString("location"), // location
                        rs.getInt("purchases_Time"), // purchasesTime
                        rs.getDouble("average_Review_Rating"), // averageReviewRating
                        rs.getInt("number_Of_Review"), // numberOfReview
                        rs.getString("total_Time"), // totalTime
                        rs.getBigDecimal("price"), // price
                        rs.getInt("slot"), // slot
                        rs.getString("tour_Status"), // tourStatus
                        rs.getDate("created_At"), // createdAt
                        imageUrlList, // tourImg
                        rs.getInt("company_Id") // companyId
                );
                list.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

//    public Tour getTourById(String tourId) {
//        Tour tour = null;
//        String sql = "SELECT * FROM Tour WHERE tour_Id = ?"; // Corrected column name
//
//        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setString(1, tourId); // Set the tourId parameter
//
//            ResultSet rs = stmt.executeQuery();
//            if (rs.next()) {
//                // Split the image URL string by ";" to create a list of URLs
//                String imageUrlStr = rs.getString("tour_Img");
//                List<String> imageUrlList = Arrays.asList(imageUrlStr.split(";"));
//
//                // Create a new Tour object with the extracted data
//                tour = new Tour(
//                        rs.getString("tour_Id"), // tourId
//                        rs.getString("tour_Name"), // tourName
//                        rs.getString("tour_Description"), // tourDescription
//                        rs.getDate("start_Date"), // startDate
//                        rs.getDate("end_Date"), // endDate
//                        rs.getString("location"), // location
//                        rs.getInt("purchases_Time"), // purchasesTime
//                        rs.getBigDecimal("average_Review_Rating"), // averageReviewRating
//                        rs.getInt("number_Of_Review"), // numberOfReview
//                        rs.getString("total_Time"), // totalTime
//                        rs.getBigDecimal("price"), // price
//                        rs.getInt("slot"), // slot
//                        rs.getString("tour_Status"), // tourStatus
//                        rs.getDate("created_At"), // createdAt
//                        imageUrlList, // tourImg
//                        rs.getInt("company_Id") // companyId
//                );
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return tour; // Return the Tour object or null if not found
//    }
    public List<TourOption> getTourOptionsByTourId(String tourId) {
        List<TourOption> options = new ArrayList<>();
        String sql = "SELECT tourOpt.option_Id, tourOpt.tour_Id, tourOpt.option_Name, tourOpt.option_Price, tourOpt.option_Description, "
                + "ts.day_Of_Week, ts.available_Slots "
                + "FROM TourOption tourOpt "
                + "LEFT JOIN TourSchedule ts ON tourOpt.option_Id = ts.option_Id "
                + "WHERE tourOpt.tour_Id = ?"; // Use the correct field names

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tourId); // Set the tourId as a parameter (since it's CHAR(8), treat as String)

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // Create a new TourOption object using the result set
                TourOption option = new TourOption(
                        rs.getInt("option_Id"), // optionId from TourOption
                        rs.getString("tour_Id"), // tourId from TourOption
                        rs.getString("option_Name"), // optionName from TourOption
                        rs.getDouble("option_Price"), // price from TourOption
                        rs.getString("option_Description"), // description from TourOption
                        rs.getString("day_Of_Week"), // dayOfWeek from TourSchedule
                        rs.getInt("available_Slots") // availableSlots from TourSchedule
                );

                options.add(option); // Add the option to the list
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return options; // Return the list of TourOptions
    }

    public static void main(String[] args) {
        UserDB userDB = new UserDB();

        // Specify the tourId you want to fetch
        String tourId = "T0000001";  // Example of a tourId (use a real one from your database)

        // Fetch the tour using the getTourById method
        Tour tour = userDB.getTourById(tourId);

        if (tour != null) {
            // Print the tour details
            System.out.println(tour.toString());

            // Fetch and print tour options for the tour
            List<TourOption> options = userDB.getTourOptionsByTourId(tourId);
            for (TourOption option : options) {
                System.out.println(option.toString());
            }
        } else {
            System.out.println("Tour not found for ID: " + tourId);
        }
    }

}
