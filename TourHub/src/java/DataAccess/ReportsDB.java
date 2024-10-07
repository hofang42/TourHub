package DataAccess;

import static DataAccess.DatabaseInfo.DBURL;
import static DataAccess.DatabaseInfo.DRIVERNAME;
import static DataAccess.DatabaseInfo.PASSDB;
import static DataAccess.DatabaseInfo.USERDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import model.ReportError;

public class ReportsDB implements DatabaseInfo {

    // Method to establish a database connection
    public static Connection getConnect() {
        try {
            Class.forName(DRIVERNAME);
        } catch (ClassNotFoundException e) {
            System.out.println("Error loading driver: " + e.getMessage());
        }
        try {
            Connection con = DriverManager.getConnection(DBURL, USERDB, PASSDB);
            return con;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    // Method to insert a new report error
    public static boolean insertReportError(ReportError report) {
        String sql = "INSERT INTO ReportError (report_Date, report_Details, report_Type, user_Id) VALUES (?, ?, ?, ?)";
        try (Connection con = getConnect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // Set the values from the ReportError object
            ps.setDate(1, new java.sql.Date(report.getReportDate().getTime()));
            ps.setString(2, report.getReportDetails());
            ps.setString(3, report.getReportType());
            ps.setInt(4, report.getUser_Id());

            // Execute the update and return true if a row was inserted
            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.out.println("Error inserting report error: " + e.getMessage());
            e.printStackTrace();
        }

        return false; // Return false if insertion failed
    }
}
