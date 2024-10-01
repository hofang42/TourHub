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

    public List<BookingDetails> getBookingDetails() {
        String query
                = "SELECT t.tour_Name, "
                + "CONCAT(u.first_Name, ' ', u.last_Name) AS cus_Name, "
                + "b.slot_Order, b.book_Status, b.total_Cost "
                + "FROM Booking b "
                + "JOIN Customer c ON b.cus_Id = c.cus_Id "
                + "JOIN [User] u ON c.user_Id = u.user_Id "
                + "JOIN Tour t ON b.tour_Id = t.tour_Id";

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
                        String tourName = resultSet.getString("tour_Name");
                        String customerName = resultSet.getString("cus_Name");
                        int slotOrder = resultSet.getInt("slot_Order");
                        String bookStatus = resultSet.getString("book_Status");
                        double totalCost = resultSet.getDouble("total_Cost");

                        // Create a new BookingDetail object and add it to the list
                        BookingDetails bookingDetail = new BookingDetails(tourName, customerName, slotOrder, bookStatus, totalCost);
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

    public static void main(String[] args) {
        BookingDB book = new BookingDB();
        List<BookingDetails> detail = book.getBookingDetails();
        for (BookingDetails b : detail) {
            System.out.println(b.toString());
        }
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

    public List<BookingDetails> getBookingDetailsWithDate(Date bookingDate) {
        // SQL query with a WHERE clause to filter by booking date
        String query = "SELECT t.tour_Name, "
                + "CONCAT(u.first_Name, ' ', u.last_Name) AS cus_Name, "
                + "b.slot_Order, b.book_Status, b.total_Cost "
                + "FROM Booking b "
                + "JOIN Customer c ON b.cus_Id = c.cus_Id "
                + "JOIN [User] u ON c.user_Id = u.user_Id "
                + "JOIN Tour t ON b.tour_Id = t.tour_Id "
                + "WHERE CAST(b.book_Date AS DATE) = ?"; // Filter by booking date

        // Initialize an empty list to hold the booking details
        List<BookingDetails> bookingDetailsList = new ArrayList<>();

        try {
            // Try-with-resources to manage the connection and statement
            try (Connection con = getConnect(); PreparedStatement statement = con.prepareStatement(query)) {
                statement.setDate(1, new java.sql.Date(bookingDate.getTime())); // Convert to java.sql.Date
                // Execute the query and get the result set
                try (ResultSet resultSet = statement.executeQuery()) {
                    // Iterate through the result set
                    while (resultSet.next()) {
                        // Extract data from the current row
                        String tourName = resultSet.getString("tour_Name");
                        String customerName = resultSet.getString("cus_Name");
                        int slotOrder = resultSet.getInt("slot_Order");
                        String bookStatus = resultSet.getString("book_Status");
                        double totalCost = resultSet.getDouble("total_Cost");

                        // Create a new BookingDetail object and add it to the list
                        BookingDetails bookingDetail = new BookingDetails(tourName, customerName, slotOrder, bookStatus, totalCost);
                        bookingDetailsList.add(bookingDetail);
                    }
                }
            }
        } catch (SQLException e) {
            // Handle the exception: log it and optionally rethrow it or return an empty list
            System.err.println("An error occurred while fetching booking details: " + e.getMessage());
            e.printStackTrace();
        }
        return bookingDetailsList;
    }

}
