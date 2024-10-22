/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DataAccess.KhanhDB;
import DataAccess.UserDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.math.BigDecimal;
import java.sql.SQLException;
import model.User;
import model.Booking;

/**
 *
 * @author LENOVO
 */
@WebServlet(name = "BookingOverviewServlet", urlPatterns = {"/BookingOverview"})
public class BookingOverviewServlet extends HttpServlet {

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
            out.println("<title>Servlet BookingOverviewServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BookingOverviewServlet at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        UserDB userDB = new UserDB();
        KhanhDB khanhDB = new KhanhDB();

        // Get the user from the session
        User user = userDB.getUserFromSession(session);

        if (user == null) {
            // Redirect to login page if the user does not exist in the session
            response.sendRedirect("login.jsp");
            return; // Stop further execution
        }

        // Get cusId from the User object
        int cusId = 0; // Assuming user_Id corresponds to cusId

        try {
            int userId = userDB.getUserFromSession(session).getUser_Id(); // Example user ID
            cusId = khanhDB.getCusIdFromUserId(userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Get and validate parameters
        String selectedDate = request.getParameter("selectedDate");
        String totalCost = request.getParameter("totalCost");
        String bookingDetail = request.getParameter("bookingDetail");
        String optionId = request.getParameter("optionId");

        System.out.println("Selected Date: " + selectedDate);
        System.out.println("Total Cost: " + totalCost);
        System.out.println("Booking Detail: " + bookingDetail);
        System.out.println("Option ID: " + optionId);

        if (selectedDate == null || totalCost == null || bookingDetail == null || optionId == null) {
            // Redirect to an error page if any required parameter is missing
            response.sendRedirect(request.getContextPath() + "/error.jsp?message=Missing parameters");
            return;
        }

        String tourId = null;

        try {
            // Fetch the tourId by optionId
            tourId = khanhDB.getTourIdByOptionId("1");
            if (tourId == null) {
                // Redirect to an error page if no tour is found for the given optionId
                response.sendRedirect(request.getContextPath() + "/error.jsp?message=No Tour found for Option ID " + optionId);
                return;
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving tour ID: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/error.jsp?message=Error retrieving tour ID");
            return;
        }

        // Default booking status
        String bookStatus = "Pending";

        // Fetch the tourId by optionId (as a string)
        int scheduleId = 1;
        int slotOrder = 1;
        String tourDate = selectedDate;
        String cancelDate = "";
        String bookDate = selectedDate;
        BigDecimal refundAmount = BigDecimal.ZERO;

        // Now, you can use tourId in your booking process or elsewhere.
        try {
            // Import the booking using the updated tourId
            khanhDB.importBooking(tourId, selectedDate, totalCost, bookingDetail, bookStatus, optionId, scheduleId, cusId, slotOrder, selectedDate, cancelDate, selectedDate, refundAmount);
            System.out.println("Booking successfully imported.");
        } catch (SQLException e) {
            System.err.println("Error importing booking: " + e.getMessage());
        }

        //Get booking id, cần chỉnh lại cách lấy, vẫn bị trùng
        int bookingId = 0;
        try {
            bookingId = khanhDB.getLatestBookingId();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(BookingOverviewServlet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        //Check booking id
        if (bookingId != -1) {
            System.out.println("Booking inserted with ID: " + bookingId);
        } else {
            System.out.println("Failed to retrieve booking ID.");
        }

        //Get booking by id
        Booking book = new Booking();
        try {
            book = khanhDB.getBookingById(bookingId);
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(BookingOverviewServlet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        System.out.println(book.toString());

        request.setAttribute("book", book);
        request.getRequestDispatcher("/booking-overview.jsp").forward(request, response);
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
