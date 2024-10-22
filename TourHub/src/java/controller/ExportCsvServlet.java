/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DataAccess.BookingDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
        response.setHeader("Content-Disposition", "attachment; filename=\"bookings.csv\"");
        
        // Get data to be exported (replace this with actual data fetching logic)
        List<Booking> bookings = getBookingsFromDatabase();

        try (PrintWriter writer = response.getWriter()) {
            // Write CSV header
            writer.println("Booking ID, User Name, Tour Name, Booking Date, Price");

            // Write booking data (adjust to match your Booking object properties)
            for (Booking booking : bookings) {
                writer.println(
                    booking.getBook_Id() + "," +
                    booking.getCus_Id() + "," +
                    booking.getTour_Name() + "," +
                    booking.getCreated_At() + "," +
                    booking.getBooking_Detail()
                );
            }
        }
    }
    
    private List<Booking> getBookingsFromDatabase() {
        // Replace this with your actual logic to fetch bookings from the database
        // Example: Call a DAO method to fetch the bookings
        BookingDB book = new BookingDB();
        return book.getAllBookings(); // assuming this method exists
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
