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
        String query = "SELECT * FROM Tours";

        try (Connection con = getConnect(); PreparedStatement stmt = con.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int tourId = rs.getInt("tourId");
                String tourName = rs.getString("tourName");
                String description = rs.getString("description");
                String totalTime = rs.getString("totalTime");
                float price = rs.getFloat("price");
                int slot = rs.getInt("slot");
                String tourStatus = rs.getString("tourStatus");
                int companyId = rs.getInt("companyId");
                Date createdAt = rs.getDate("createdAt");
                String tourImg = rs.getString("tourImg");

                Tour tour = new Tour(tourId, tourName, description, totalTime, price, slot, tourStatus, companyId, createdAt, tourImg);
                tours.add(tour);
            }

        } catch (SQLException ex) {
            Logger.getLogger(TourDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return tours;
    }
}
