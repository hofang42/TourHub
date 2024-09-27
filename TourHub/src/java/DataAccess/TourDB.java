/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccess;

import static DataAccess.DatabaseInfo.DBURL;
import static DataAccess.DatabaseInfo.DRIVERNAME;
import static DataAccess.DatabaseInfo.PASSDB;
import static DataAccess.DatabaseInfo.USERDB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Tour;

/**
 *
 * @author hoang
 */
public class TourDB {

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

    //Get all tour
    public List<Tour> getTours() {
        List<Tour> tours = new ArrayList<>();
        String query = "SELECT * FROM Tour";

        try (Connection con = getConnect(); PreparedStatement stmt = con.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String tourId = rs.getString("tour_Id");
                String tourName = rs.getString("tour_Name");
                String description = rs.getString("tour_Description");
                Date startDate = rs.getDate("start_Date");
                Date endDate = rs.getDate("end_Date");
                float avgRating = rs.getFloat("average_Review_Rating");
                int numOfReview = rs.getInt("number_Of_Review");
                String totalTime = rs.getString("total_Time");
                float price = rs.getFloat("price");
                int slot = rs.getInt("slot");
                String tourStatus = rs.getString("tour_Status");
                int companyId = rs.getInt("company_Id");
                Date createdAt = rs.getDate("created_At");
                String tourImg = rs.getString("tour_Img");

                Tour tour = new Tour(tourId, tourName, description, totalTime, price, slot, tourStatus, companyId, createdAt, tourImg, startDate, endDate, avgRating, numOfReview);
                tours.add(tour);
            }

        } catch (SQLException ex) {
            Logger.getLogger(TourDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return tours;
    }

    public int getTotalVisitATour(int companyId) {
        String query = "SELECT COUNT(*) FROM TourVisitCount WHERE company_Id = ?";
        int totalVisits = 0;  // Variable to store the total count of visits

        try (Connection con = getConnect(); PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, companyId);  // Set the tour_Id parameter
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    totalVisits = rs.getInt(1);  // Retrieve the count from the result set
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(TourDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return totalVisits;  // Return the total visit count
    }

    public int getTodayVisit(int companyId) {
        int visitCount = 0;
        String query = "SELECT COUNT(*) AS visit_count FROM TourVisitCount WHERE company_id = ? AND visitDate = ?";

        try (Connection connection = getConnect(); PreparedStatement pstmt = connection.prepareStatement(query)) {
            // Set the companyId parameter
            pstmt.setInt(1, companyId);
            pstmt.setDate(2, new Date(System.currentTimeMillis()));
            // Execute the query and retrieve the result
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    visitCount = rs.getInt("visit_count");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Consider using a logging framework
        }

        return visitCount;
    }

    public int addNewCountTourVisit(String tourId) {
        String query = "INSERT INTO TourVisitCount (tour_Id, company_Id) VALUES (?, ?)";
        int companyId = 0;
        try {
            companyId = getCompanyIdFromTourId(tourId);
        } catch (SQLException ex) {
            Logger.getLogger(TourDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        try (Connection con = getConnect(); PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, tourId);  // Set the tour_Id parameter
            stmt.setInt(2, companyId);
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated;
        } catch (SQLException ex) {
            Logger.getLogger(TourDB.class.getName()).log(Level.SEVERE, null, ex);
            return 0;  // Return false if there was an error
        }
    }

    public int getCompanyIdFromTourId(String tourId) throws SQLException {
        String query = "SELECT c.company_Id FROM Company c "
                + "JOIN Tour t ON c.company_Id = t.company_Id "
                + "WHERE t.tour_Id = ?";
        try (Connection con = getConnect(); PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, tourId);
            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("company_Id");
                } else {
                    throw new SQLException("No company found for tourId: " + tourId);
                }
            }
        }
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
}
