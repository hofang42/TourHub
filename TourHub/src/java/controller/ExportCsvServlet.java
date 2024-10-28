/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DataAccess.BookingDB;
import DataAccess.UserDB;
import DataAccess.hoang_UserDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import model.Booking;

/**
 *
 * @author NOMNOM
 */
@WebServlet(name = "ExportCsvServlet", urlPatterns = {"/exportCsv"})
public class ExportCsvServlet extends HttpServlet {

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
        // Set UTF-8 encoding for both request and response
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // Set the content type for CSV export and the file name
        response.setContentType("text/csv; charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"summary-for-provider.csv\"");

        int companyId = 0;
        try {
            companyId = new hoang_UserDB().getProviderIdFromUserId(new UserDB().getUserFromSession(request.getSession()).getUser_Id());
        } catch (SQLException ex) {

        }
        hoang_UserDB book = new hoang_UserDB();
        // Get the current year
        int currentYear = LocalDate.now().getYear();
        double[] monthlyProfit = book.getMonthlyProfitByYear(companyId, currentYear); // assuming this method exists
        BigDecimal totalProfitCurrentYear = book.getTotalProfitCurrentYear(companyId);

        int[] monthlyBooking = book.getTotalBookingMonthly(companyId, currentYear);
        int totalBookingCurrentYear = book.getTotalBookingCurrentYear(companyId);

        List<Map<String, Object>> hotDestinationCurrentYear = book.getHotDestination(companyId, currentYear);
        try (PrintWriter writer = response.getWriter()) {
            // Ensure this is written on one line
            writer.println("\"This summary contains the monthly profit, booking data, and the most popular destinations for the current year.\"");

// Blank line for spacing
            writer.println();

            // Write CSV header for monthly profit and booking data
            writer.println("Month, Monthly Profit, Monthly Booking");

            // Write monthly data
            for (int month = 0; month < 12; month++) {
                writer.println(
                        (month + 1) + ","
                        + monthlyProfit[month] + ","
                        + monthlyBooking[month]
                );
            }

            // Write total data for profit and booking
            writer.println(); // blank line for spacing
            writer.println("Total," + totalProfitCurrentYear + "," + totalBookingCurrentYear);

            // Blank line to separate different sections
            writer.println();

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
        processRequest(request, response);
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

}
