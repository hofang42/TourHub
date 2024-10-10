/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccess;

import static DataAccess.UserDB.getConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Tour;
import model.TourOption;

/**
 *
 * @author LENOVO
 */
public class KhanhDB {
    public List<Tour> getAllTour(String sortOrder, String location, int minPrice, int maxPrice) {
        List<Tour> list = new ArrayList<>();
        String sql = "SELECT * FROM Tour WHERE 1=1 AND tour_Status='Active'";

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

    public Tour getTourById(String tourId) {
        Tour tour = null;
        String sql = "SELECT * FROM Tour WHERE tour_Id = ?"; // Corrected column name

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tourId); // Set the tourId parameter

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Split the image URL string by ";" to create a list of URLs
                String imageUrlStr = rs.getString("tour_Img");
                List<String> imageUrlList = Arrays.asList(imageUrlStr.split(";"));

                // Create a new Tour object with the extracted data
                tour = new Tour(
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tour; // Return the Tour object or null if not found
    }
    
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
                        rs.getBigDecimal("option_Price"), // price from TourOption
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
        KhanhDB userDB = new KhanhDB();

        // Specify the tourId you want to fetch
        String tourId = "T0000001";  // Example of a tourId (use a real one from your database)

        // Fetch the tour using the getTourById method
        Tour tour = userDB.getTourById(tourId);

        if (tour != null) {
            // Print the tour details
            System.out.println(tour.getTour_Name());

            // Fetch and print tour options for the tour
            List<TourOption> options = userDB.getTourOptionsByTourId(tourId);
            for (TourOption option : options) {
                System.out.println(option.getOption_Name());
            }
        } else {
            System.out.println("Tour not found for ID: " + tourId);
        }
    }
}
