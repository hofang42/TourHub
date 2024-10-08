/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccess;

import static DataAccess.DatabaseInfo.DBURL;
import static DataAccess.DatabaseInfo.DRIVERNAME;
import static DataAccess.DatabaseInfo.PASSDB;
import static DataAccess.DatabaseInfo.USERDB;
<<<<<<< HEAD
import static DataAccess.TourDB.getConnect;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.BookingDetails;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
=======
>>>>>>> e839fb6ab9a068e816cbec84d5f2d127cf3c3bd7
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Booking;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

/**
 *
 * @author NOMNOM
 */
public class BookingDB implements DatabaseInfo {

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

<<<<<<< HEAD
    public int getTotalBookingThisMonth(int companyId) {
        int bookingCount = 0;
        String query = "SELECT COUNT(*) AS booking_count "
                + "FROM Booking b "
                + "JOIN Customer c ON b.cus_Id = c.cus_Id "
                + "JOIN [User] u ON c.user_Id = u.user_Id "
                + "JOIN Tour t ON b.tour_Id = t.tour_Id "
                + "WHERE t.company_Id = ? "
                + "AND MONTH(b.book_Date) = MONTH(GETDATE()) "
                + "AND YEAR(b.book_Date) = YEAR(GETDATE())";

        try (Connection connection = getConnect(); PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, companyId); // Set the company ID parameter

            // Execute the query and retrieve the result
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    bookingCount = rs.getInt("booking_count");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Consider using a logging framework for production
        }

        return bookingCount;
    }

    public List<BookingDetails> getPendingBookingDetails() {
        String query
                = "SELECT b.book_Id, t.tour_Name, "
                + "CONCAT(u.first_Name, ' ', u.last_Name) AS cus_Name, "
                + "b.slot_Order, b.book_Status, b.total_Cost "
                + "FROM Booking b "
                + "JOIN Customer c ON b.cus_Id = c.cus_Id "
                + "JOIN [User] u ON c.user_Id = u.user_Id "
                + "JOIN Tour t ON b.tour_Id = t.tour_Id WHERE b.book_Status = 'Pending'";

        // Initialize an empty list to hold the booking details
        List<BookingDetails> bookingDetailsList = new ArrayList<>();

        try {
            // Try-with-resources to manage the connection and statement
            try (Connection con = getConnect(); PreparedStatement statement = con.prepareStatement(query)) {

                // Execute the query and get the result set
                try (ResultSet resultSet = statement.executeQuery()) {
                    // Iterate through the result set
                    while (resultSet.next()) {
                        // Extract data from the current row
                        int bookId = resultSet.getInt("book_Id");
                        String tourName = resultSet.getString("tour_Name");
                        String customerName = resultSet.getString("cus_Name");
                        int slotOrder = resultSet.getInt("slot_Order");
                        String bookStatus = resultSet.getString("book_Status");
                        double totalCost = resultSet.getDouble("total_Cost");
                        // Create a new BookingDetail object and add it to the list
                        BookingDetails bookingDetail = new BookingDetails(bookId, tourName, customerName, slotOrder, bookStatus, totalCost);
                        bookingDetailsList.add(bookingDetail);
                    }
                }
            }
        } catch (SQLException e) {
            // Handle the exception: log it and optionally rethrow it or return an empty list
            System.err.println("An error occurred while fetching booking details: " + e.getMessage());
            e.printStackTrace();
            // Optionally, rethrow the exception if you need the calling method to handle it
            // throw new RuntimeException("Database error occurred", e);
        }
        return bookingDetailsList;
    }

    //With day 
    public int getTotalBookingAMonthByDate(int companyId, java.util.Date date) {
        int bookingCount = 0;
        String query = "SELECT COUNT(*) AS booking_count "
                + "FROM Booking b "
                + "JOIN Customer c ON b.cus_Id = c.cus_Id "
                + "JOIN [User] u ON c.user_Id = u.user_Id "
                + "JOIN Tour t ON b.tour_Id = t.tour_Id "
                + "WHERE t.company_Id = ? "
                + "AND book_Status = 'Booked'"
                + "AND MONTH(b.book_Date) = MONTH(?) "
                + "AND YEAR(b.book_Date) = YEAR(?)";

        try (Connection connection = getConnect(); PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, companyId); // Set the company ID parameter
            pstmt.setDate(2, new java.sql.Date(date.getTime())); // Set the date parameter for month and year comparison
            pstmt.setDate(3, new java.sql.Date(date.getTime())); // Set the date parameter for year comparison

            // Execute the query and retrieve the result
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    bookingCount = rs.getInt("booking_count");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Consider using a logging framework for production
        }

        return bookingCount;
    }

    public void acceptABooking(int bookId) {
        String sql = "UPDATE Booking SET book_Status = 'Booked' WHERE book_Status = 'Pending' AND book_Id = ?";

        try (Connection con = getConnect(); PreparedStatement statement = con.prepareStatement(sql)) {
            // Bind the bookId to the prepared statement
            statement.setInt(1, bookId); // Set the bookId in the prepared statement
            // Execute the update
            int rowEffect = statement.executeUpdate();
            System.out.println("Booking ID: " + bookId + " | Row Effect: " + rowEffect);
        } catch (SQLException e) {
            e.printStackTrace(); // Print exception details
        }
    }

    public Map<Integer, Integer> getBookingMonthly(int companyId) {
        Map<Integer, Integer> monthlyBookings = new HashMap<>();
        String query = "SELECT MONTH(b.book_Date) AS month, COUNT(*) AS totalBookings "
                + "FROM Booking b "
                + "JOIN Tour t ON b.tour_Id = t.tour_Id "
                + "WHERE t.company_Id = ? "
                + "AND b.book_Status = 'Booked' "
                + "AND YEAR(book_Date) = YEAR(GETDATE())" // Filter for only booked status
                + "GROUP BY MONTH(b.book_Date)"
                + "ORDER BY MONTH(b.book_Date)";

        try (Connection connection = getConnect(); PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, companyId); // Set the company ID parameter

            // Execute the query and retrieve the result
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int month = rs.getInt("month");
                    int totalBookings = rs.getInt("totalBookings");
                    monthlyBookings.put(month, totalBookings); // Store the month and count in the map
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Consider using a logging framework for production
        }

        return monthlyBookings; // Return the map containing month and total bookings
    }

    public double[] getMonthlyProfitByYear(int companyId, int year) {
        // Array to hold profit for each month (12 months)
        double[] monthlyProfits = new double[12];
        double totalRevenue;
        double totalRefunds;

        for (int month = 1; month <= 12; month++) {
            totalRevenue = 0.0;
            totalRefunds = 0.0;

            String revenueQuery = "SELECT SUM(b.total_Cost) AS TotalRevenue "
                    + "FROM Company c "
                    + "INNER JOIN Tour t ON c.company_Id = t.company_Id "
                    + "INNER JOIN Booking b ON b.tour_Id = t.tour_Id "
                    + "WHERE c.company_Id = ? "
                    + "AND MONTH(b.book_Date) = ? "
                    + "AND YEAR(b.book_Date) = ? "
                    + "AND b.book_Status = 'Booked'";

            try (Connection connection = getConnect()) {
                // Calculate total revenue for the month
                try (PreparedStatement revenueStmt = connection.prepareStatement(revenueQuery)) {
                    revenueStmt.setInt(1, companyId);
                    revenueStmt.setInt(2, month);
                    revenueStmt.setInt(3, year);
                    ResultSet revenueResult = revenueStmt.executeQuery();
                    if (revenueResult.next()) {
                        totalRevenue = revenueResult.getDouble("TotalRevenue");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Handle exceptions appropriately in production code
            }

            // Calculate profit for the month and store it in the array
            monthlyProfits[month - 1] = totalRevenue;
        }

        return monthlyProfits; // Returns an array containing monthly profits
    }

    public List<Map<String, Object>> getHotDestination(int companyId) {
        List<Map<String, Object>> hotDestinations = new ArrayList<>();
        String query = "SELECT t.location, COUNT(b.tour_Id) AS bookingCount "
                + "FROM Company c "
                + "INNER JOIN Tour t ON c.company_Id = t.company_Id "
                + "INNER JOIN Booking b ON b.tour_Id = t.tour_Id "
                + "WHERE c.company_Id = ? "
                + "GROUP BY t.location"; // Group by location to get booking counts

        try (Connection connection = getConnect(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Set the company ID in the prepared statement
            preparedStatement.setInt(1, companyId);

            // Execute the query
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // Process the results
                while (resultSet.next()) {
                    String location = resultSet.getString("location");
                    int bookingCount = resultSet.getInt("bookingCount");

                    // Create a map for the current destination
                    Map<String, Object> destination = new HashMap<>();
                    destination.put("location", location);
                    destination.put("count", bookingCount);

                    // Add the map to the list
                    hotDestinations.add(destination);
                }
            }
        } catch (SQLException e) {
            // Log the exception and ensure it's handled properly in your system         
        }

        // Return the list of hot destinations
        return hotDestinations;
    }

    public void updateBookingStatus(int bookingId, String newStatus) throws SQLException {
        // Ensure the new status is valid
        if (!newStatus.equals("Booked") && !newStatus.equals("Cancelled")) {
            throw new IllegalArgumentException("Invalid status. Status must be either 'Booked' or 'Cancelled'.");
        }

        String sql = "UPDATE booking SET book_Status = ? WHERE book_Id = ?"; // Adjust the table name as needed

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newStatus);
            stmt.setInt(2, bookingId);

            // Execute the update statement
            int rowsAffected = stmt.executeUpdate();

            // Optionally, check if the update was successful
            if (rowsAffected == 0) {
                throw new SQLException("No booking found with the specified ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;  // Rethrow or handle the exception as needed
        }
    }

    public static void main(String[] args) {
        BookingDB book = new BookingDB();

        double profit = book.getTotalBookingThisMonth(2);
        System.out.println(profit);
    }

=======
>>>>>>> e839fb6ab9a068e816cbec84d5f2d127cf3c3bd7
    public List<Booking> getUserBooking(int customer_Id) {
        List<Booking> list = new ArrayList<>();

        try (Connection con = getConnect()) {
            PreparedStatement stmt = con.prepareStatement("SELECT book_Id, book_Date,"
                    + "slot_Order, total_Cost, book_Status, cus_Id, tour_Id FROM Booking where cus_Id = ?");
            stmt.setInt(1, customer_Id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Booking(rs.getInt(1), rs.getDate(2), rs.getInt(3), rs.getBigDecimal(4), rs.getString(5), rs.getInt(6), rs.getString(7)));
            }
            con.close();
            return list;
        } catch (Exception ex) {
            Logger.getLogger(BookingDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public List<Booking> getUser2Booking(int customer_Id) {
        List<Booking> list = new ArrayList<>();

        // Sử dụng try-with-resources để tự động đóng kết nối và tài nguyên
        String query = "SELECT book_Id, book_Date, slot_Order, total_Cost, book_Status, Tour.tour_Id, Tour.tour_Name "
                + "FROM Booking "
                + "INNER JOIN Tour ON Booking.tour_Id = Tour.tour_Id "
                + "INNER JOIN [User] ON Booking.cus_Id = [User].user_Id "
                + "WHERE Booking.cus_Id = ?";

        try (Connection con = getConnect(); PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setInt(1, customer_Id); // Đặt tham số vào PreparedStatement
            ResultSet rs = stmt.executeQuery(); // Thực thi truy vấn

            while (rs.next()) {
                // Tạo một đối tượng Booking từ dữ liệu trong ResultSet
                Booking booking = new Booking(
                        rs.getInt("book_Id"),
                        rs.getDate("book_Date"),
                        rs.getInt("slot_Order"),
                        rs.getBigDecimal("total_Cost"),
                        rs.getString("book_Status"),
                        customer_Id, // Sử dụng customer_Id từ tham số đầu vào
                        rs.getString("tour_Id"),
                        rs.getString("tour_Name") // Chắc chắn rằng lớp Booking có trường tour_Name
                );
                list.add(booking);
            }
        } catch (Exception ex) {
            // Ghi lại lỗi nếu có
            Logger.getLogger(BookingDB.class.getName()).log(Level.SEVERE, "Error retrieving user bookings", ex);
        }

        return list; // Trả về danh sách booking, nếu không có booking thì danh sách sẽ rỗng
    }

    public List<Booking> getAllBookings() {
        List<Booking> list = new ArrayList<>();

        try (Connection con = getConnect()) {
            PreparedStatement stmt = con.prepareStatement("SELECT book_Id, book_Date,"
                    + "slot_Order, total_Cost, book_Status, cus_Id, tour_Id FROM Booking");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Booking(rs.getInt(1), rs.getDate(2), rs.getInt(3), rs.getBigDecimal(4), rs.getString(5), rs.getInt(6), rs.getString(7)));
            }
            con.close();
            return list;
        } catch (Exception ex) {
            Logger.getLogger(BookingDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    //----------------------------------------------------------
    //Số lượng đặt tour: Tổng số tour đã được đặt -- có thể thêm trong một khoảng thời gian cụ thể (ngày, tháng, năm).
    public int getTotalBookings() throws SQLException {
        try (Connection con = getConnect()) {
            String sql = "SELECT COUNT(*) FROM Booking";
            try (PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
            return 0;
        } catch (Exception ex) {
            Logger.getLogger(BookingDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;

    }

    //Doanh thu: Tổng doanh thu từ các tour đã đặt.
    public BigDecimal getTotalRevenue() throws SQLException {
        try (Connection con = getConnect()) {
            String sql = "SELECT SUM(total_Cost) FROM Booking WHERE book_Status = 'Confirmed'";
            try (PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getBigDecimal(1) != null ? rs.getBigDecimal(1) : BigDecimal.ZERO;
                }
            }
            return BigDecimal.ZERO;
        } catch (Exception ex) {
            Logger.getLogger(BookingDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    //Số lượng khách hàng mới: Số lượng khách hàng đã đăng ký tài khoản trong khoảng thời gian.
    public int getNewCustomersCount(Connection conn, Date startDate, Date endDate, String role) throws SQLException {
        role = "customer";
        String sql = "SELECT COUNT(*) FROM [User] WHERE created_At BETWEEN ? AND ? AND Role = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, new java.sql.Date(startDate.getTime()));
            stmt.setDate(2, new java.sql.Date(endDate.getTime()));
            stmt.setString(3, role);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return 0;
    }

    //Tỷ lệ hủy tour: Tỷ lệ phần trăm các tour bị hủy so với tổng số tour đã đặt.
    public double getCancellationRate() throws SQLException {
        try (Connection con = getConnect()) {
            String sql = "SELECT COUNT(*) FROM Booking WHERE book_Status = 'Cancelled'";
            String sqlTotal = "SELECT COUNT(*) FROM Booking";
            int cancelledCount = 0;
            int totalCount = 0;

            try (PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cancelledCount = rs.getInt(1);
                }
            }

            try (PreparedStatement stmtTotal = con.prepareStatement(sqlTotal); ResultSet rsTotal = stmtTotal.executeQuery()) {
                if (rsTotal.next()) {
                    totalCount = rsTotal.getInt(1);
                }
            }

            return totalCount > 0 ? (double) cancelledCount / totalCount * 100 : 0;
        } catch (Exception ex) {
            Logger.getLogger(BookingDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    //Đánh giá và phản hồi: Điểm trung bình đánh giá từ khách hàng về các tour. Chưa có review = null
    public double getAverageReviewRating(Connection conn) throws SQLException {
        String sql = "SELECT AVG(rating_Star) FROM Review";
        try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getDouble(1);
            }
        }
        return 0;
    }

    //Số lượng tour được xem: Số lần khách hàng xem các tour trên website. Chưa có biến view trong database, sẽ thêm
    public int getTourViewsCount(Connection conn) throws SQLException {
        // Giả sử bạn có một bảng lưu trữ số lần xem tour
        String sql = "SELECT SUM(views) FROM Tour"; // Bạn cần tạo cột views trong bảng Tour
        try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

    //Số lượng đặt tour theo loại: Phân loại theo các loại tour (du lịch biển, du lịch núi, văn hóa, ...).// chưa thêm data type 
    public Map<String, Integer> getBookingsCountByType(Connection conn) throws SQLException {
        String sql = "SELECT tour_Id, COUNT(*) FROM Booking GROUP BY tour_Id";
        Map<String, Integer> bookingsByType = new HashMap<>();

        try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                bookingsByType.put(rs.getString("tour_Id"), rs.getInt(2));
            }
        }
        return bookingsByType;
    }

    //Thống kê theo vùng miền: Số lượng đặt tour theo từng thành phố hoặc vùng miền.
    public Map<String, Integer> getBookingsByLocation() throws SQLException {
        try (Connection con = getConnect()) {
            String sql = "SELECT location, COUNT(*) FROM Tour t INNER JOIN Booking b ON t.tour_Id = b.tour_Id GROUP BY location";
            Map<String, Integer> bookingsByLocation = new HashMap<>();

            try (PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    bookingsByLocation.put(rs.getString("location"), rs.getInt(2));
                }
            }
            return bookingsByLocation;
        } catch (Exception ex) {
            Logger.getLogger(BookingDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    //Liệt kê số lượng đặt tour theo từng tháng
    public Map<String, Integer> getMonthlyBookingsCount() throws SQLException {
        try (Connection con = getConnect()) {
            String sql = "SELECT MONTH(book_Date) AS month, COUNT(*) AS booking_count "
                    + "FROM Booking "
                    + "GROUP BY MONTH(book_Date)";
            Map<String, Integer> bookingsByMonth = new HashMap<>();

            try (PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String month = String.valueOf(rs.getInt("month"));
                    int bookingCount = rs.getInt("booking_count");
                    bookingsByMonth.put(month, bookingCount);
                }
            }
            return bookingsByMonth;
        } catch (Exception ex) {
            Logger.getLogger(BookingDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    //Liệt kê các user vừa đăng ký gần đây nhất
    public List<User> getRecentUsers() throws SQLException {
        try (Connection con = getConnect()) {
            String sql = "SELECT TOP 10 user_Id, first_Name, last_Name, email, created_At, role "
                    + "FROM [User] WHERE role = 'customer' OR role = 'company' "
                    + "ORDER BY created_At DESC "; // Giới hạn lấy 10 user gần đây nhất
            List<User> recentUsers = new ArrayList<>();

            try (PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    User user = new User();
                    user.setUser_Id(rs.getInt("user_Id"));
                    user.setFirst_Name(rs.getString("first_Name"));
                    user.setLast_Name(rs.getString("last_Name"));
                    user.setEmail(rs.getString("email"));
                    user.setCreated_At(rs.getDate("created_At"));
                    user.setRole(rs.getString("role"));
                    recentUsers.add(user);
                }
            }
            return recentUsers;
        } catch (Exception ex) {
            Logger.getLogger(BookingDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
