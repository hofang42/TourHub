/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author LENOVO
 */
public class DatabaseConnectionCheck implements DatabaseInfo {

    public static void main(String[] args) {
        checkDatabaseConnection();
        System.out.println("Hello");
    }

    public static void checkDatabaseConnection() {
        Connection conn = null;
        try {
            // Load the driver
            Class.forName(DRIVERNAME);

            // Attempt to connect to the database
            conn = DriverManager.getConnection(DBURL, USERDB, PASSDB);

            // If connection is successful
            if (conn != null) {
                System.out.println("Connection to the database was successful!");
            }
        } catch (ClassNotFoundException e) {
            // Handle case where the driver class was not found
            System.out.println("Error: Unable to load database driver class");
            e.printStackTrace();
        } catch (SQLException e) {
            // Handle SQL exceptions
            System.out.println("Error: Failed to connect to the database");
            e.printStackTrace();
        } finally {
            // Close the connection if it was opened
            if (conn != null) {
                try {
                    conn.close();
                    System.out.println("Connection closed.");
                } catch (SQLException e) {
                    System.out.println("Error: Failed to close the connection");
                    e.printStackTrace();
                }
            }
        }
    }
}
