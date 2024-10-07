/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccess;

import static DataAccess.DatabaseInfo.DBURL;
import static DataAccess.DatabaseInfo.DRIVERNAME;
import static DataAccess.DatabaseInfo.PASSDB;
import static DataAccess.DatabaseInfo.USERDB;
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

/**
 *
 * @author hoang
 */
public class BookingDB {

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
                + "AND b.book_Status = 'Booked' " // Filter for only booked status
                + "GROUP BY MONTH(b.book_Date) "
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

    public static void main(String[] args) {
        BookingDB book = new BookingDB();

        double profit = book.getTotalBookingThisMonth(2);
        System.out.println(profit);
    }

}
