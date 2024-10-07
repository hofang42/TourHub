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
import java.sql.SQLException;

import java.util.List;

import java.sql.*;
import java.util.ArrayList;
import model.Tour;

/**
 *
 * @author NOMNOM
 */
public class TourDB implements DatabaseInfo {
    
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
    
    //get a tour
    //get all tour
    public static List<Tour> getAllTours() {
        List<Tour> tourList = new ArrayList<>();
        Connection con = getConnect();  // Kết nối tới database
        if (con == null) {
            System.out.println("Failed to make connection!");
            return tourList;
        }

        String sql = "SELECT * FROM Tour";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                // Tạo đối tượng Tour từ kết quả truy vấn
                Tour tour = new Tour();
                tour.setTour_Id(rs.getString("tour_Id"));
                tour.setTour_Name(rs.getString("tour_Name"));
                tour.setTour_Description(rs.getString("tour_Description"));
                tour.setStart_Date(rs.getDate("start_Date"));
                tour.setEnd_Date(rs.getDate("end_Date"));
                tour.setLocation(rs.getString("location"));
                tour.setPurchases_Time(rs.getInt("purchases_Time"));
                tour.setAverage_Review_Rating(rs.getDouble("average_Review_Rating"));
                tour.setNumber_Of_Review(rs.getInt("number_Of_Review"));
                tour.setTotal_Time(rs.getString("total_Time"));
                tour.setPrice(rs.getBigDecimal("price"));
                tour.setSlot(rs.getInt("slot"));
                tour.setTour_Status(rs.getString("tour_Status"));
                tour.setCreated_At(rs.getDate("created_At"));
                tour.setTour_Img(rs.getString("tour_Img"));
                tour.setCompany_Id(rs.getInt("company_Id"));
                
                // Thêm đối tượng Tour vào danh sách
                tourList.add(tour);
            }
        } catch (SQLException e) {
            System.out.println("Error while fetching tours: " + e.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close(); // Đóng kết nối sau khi sử dụng
                }
            } catch (SQLException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }

        return tourList;
    }
    //insert a tour
    //delete a tour
    //update a tour
    
    
}
