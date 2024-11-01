/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccess;

import static DataAccess.BookingDB.getConnect;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.BookingDetails;
import model.Tour;

/**
 *
 * @author hoang
 */
public class hoang_UserDB implements DatabaseInfo {

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
//USERDB

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
//BOOKINGDB

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

    public void updateTour(Tour updatedTour) throws SQLException {
        String sql = "UPDATE Tour SET tour_Name = ?, tour_Description = ?, start_Date = ?, end_Date = ?, location = ?, "
                + "total_Time = ?, price = ?, slot = ?, tour_Img = ? WHERE tour_Id = ?";

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            // Set the parameters for the prepared statement
            stmt.setString(1, updatedTour.getTour_Name());
            stmt.setString(2, updatedTour.getTour_Description());

            // Assuming start_Date and end_Date are java.util.Date, convert them to java.sql.Date
            stmt.setDate(3, new java.sql.Date(updatedTour.getStart_Date().getTime()));
            stmt.setDate(4, new java.sql.Date(updatedTour.getEnd_Date().getTime()));

            stmt.setString(5, updatedTour.getLocation());
            stmt.setString(6, updatedTour.getTotal_Time());

            // Assuming price is BigDecimal
            stmt.setBigDecimal(7, updatedTour.getPrice());

            stmt.setInt(8, updatedTour.getSlot());

            // Assuming tour_Img is a List<String>, join them into a single string separated by semicolons
            List<String> images = updatedTour.getTour_Img();
            String imageFilenames = String.join(";", images);
            stmt.setString(9, imageFilenames);

            stmt.setString(10, updatedTour.getTour_Id()); // Assuming tour_Id is String

            // Execute the update statement
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error updating tour: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        List<BookingDetails> tours = new hoang_UserDB().getPendingBookingDetails();
        for (BookingDetails book : tours) {
            System.out.println(book.toString());
        }
    }
}
