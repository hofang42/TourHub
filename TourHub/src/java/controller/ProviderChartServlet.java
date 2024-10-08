/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DataAccess.BookingDB;
import DataAccess.CompanyDB;
import DataAccess.UserDB;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

/**
 *
 * @author hoang
 */
@WebServlet(name = "ProviderChartServlet", urlPatterns = {"/charts"})
public class ProviderChartServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProviderChartServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProviderChartServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Check if the user is logged in
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("currentUser") == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User is not logged in");
            return;
        }

// Retrieve the logged-in user
        User user = (User) session.getAttribute("currentUser");
        int companyId;
        CompanyDB companyDB = new CompanyDB();

        try {
            // Get company ID based on the user ID
            companyId = companyDB.getCompanyIdFromUserId(user.getUserId());
        } catch (SQLException ex) {
            Logger.getLogger(ProviderAnalysServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error occurred");
            return;
        }

// Fetch monthly bookings for the company
        BookingDB bookDB = new BookingDB();
        Map<Integer, Integer> monthlyBookings = bookDB.getBookingMonthly(companyId);

// Fetch monthly profits for the current year
        double[] monthlyProfitsThisYear = bookDB.getMonthlyProfitByYear(companyId, LocalDate.now().getYear());
        double[] monthlyProfitsLastYear = bookDB.getMonthlyProfitByYear(companyId, LocalDate.now().getYear() - 1);

// Set the response to JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

// Build the JSON response
        StringBuilder jsonResponse = new StringBuilder();
        jsonResponse.append("{");
        jsonResponse.append("\"monthlyBookings\": [");

        if (monthlyBookings.isEmpty()) {
            jsonResponse.append("],"); // Empty array case
        } else {
            for (Map.Entry<Integer, Integer> entry : monthlyBookings.entrySet()) {
                jsonResponse.append(String.format("{\"month\": %d, \"totalBookings\": %d},", entry.getKey(), entry.getValue()));
            }
            jsonResponse.setLength(jsonResponse.length() - 1); // Safely remove the last comma
        }
        jsonResponse.append("],"); // Close monthlyBookings array

// Append monthly profits for this year
        jsonResponse.append("\"monthlyProfitsThisYear\": [");
        for (int month = 0; month < 12; month++) {
            jsonResponse.append(String.format("{\"month\": %d, \"profit\": %.2f},", month + 1, monthlyProfitsThisYear[month]));
        }
        jsonResponse.setLength(jsonResponse.length() - 1); // Remove the last comma
        jsonResponse.append("],"); // Close monthlyProfitsThisYear array

// Append monthly profits for last year
        jsonResponse.append("\"monthlyProfitsLastYear\": [");
        for (int month = 0; month < 12; month++) {
            jsonResponse.append(String.format("{\"month\": %d, \"profit\": %.2f},", month + 1, monthlyProfitsLastYear[month]));
        }
        jsonResponse.setLength(jsonResponse.length() - 1); // Remove the last comma
        jsonResponse.append("]"); // Close monthlyProfitsLastYear array

        jsonResponse.append("}");

//        BookingDB bookingDB = new BookingDB();
//        // Fetch hot destinations from the database
//        List<Map<String, Object>> hotDestinations = bookingDB.getHotDestination(companyId);
//
//// Initialize categoryLabels and categoryData lists
//        List<String> categoryLabels = new ArrayList<>();
//        List<Integer> categoryData = new ArrayList<>();
//
//// Populate the lists from hotDestinations
//        if (hotDestinations != null && !hotDestinations.isEmpty()) {
//            for (Map<String, Object> destination : hotDestinations) {
//                String location = (String) destination.getOrDefault("location", "Unknown");
//                int count = (Integer) destination.getOrDefault("count", 0);
//                categoryLabels.add(location);
//                categoryData.add(count);
//            }
//        }
//
//// Construct the JSON response
//        jsonResponse.append("{");
//
//// Check if categoryLabels and categoryData are not empty before adding to JSON
//        boolean hasCategories = !categoryLabels.isEmpty() || !categoryData.isEmpty();
//        if (hasCategories) {
//            // Append categoryLabels array
//            jsonResponse.append("\"categoryLabels\": [");
//            for (int i = 0; i < categoryLabels.size(); i++) {
//                jsonResponse.append(String.format("\"%s\"", categoryLabels.get(i)));
//                if (i < categoryLabels.size() - 1) {
//                    jsonResponse.append(","); // Add comma except for the last element
//                }
//            }
//            jsonResponse.append("],"); // Close categoryLabels array
//
//            // Append categoryData array
//            jsonResponse.append("\"categoryData\": [");
//            for (int i = 0; i < categoryData.size(); i++) {
//                jsonResponse.append(categoryData.get(i));
//                if (i < categoryData.size() - 1) {
//                    jsonResponse.append(","); // Add comma except for the last element
//                }
//            }
//            jsonResponse.append("]"); // Close categoryData array
//        } else {
//            // If both lists are empty, add empty arrays without trailing commas
//            jsonResponse.append("\"categoryLabels\": [],");
//            jsonResponse.append("\"categoryData\": []");
//        }
//
//// Close the overall JSON object
//        jsonResponse.append("}");
// Send the JSON response to the client        
        PrintWriter out = response.getWriter();
        out.print(jsonResponse.toString());
        out.flush();

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public static void main(String[] args) {
        // Replace with appropriate company ID and date for testing
        int companyId = 2; // Example company ID

        // Create an instance of BookingDB
        BookingDB bookingDB = new BookingDB();

        // Call the method to get monthly bookings
        Map<Integer, Integer> monthlyBookings = bookingDB.getBookingMonthly(companyId);

        // Print the results
        System.out.println("Monthly Bookings for Company ID: " + companyId);
        for (Map.Entry<Integer, Integer> entry : monthlyBookings.entrySet()) {
            System.out.println("Month: " + entry.getKey() + ", Total Bookings: " + entry.getValue());
        }
    }
}
