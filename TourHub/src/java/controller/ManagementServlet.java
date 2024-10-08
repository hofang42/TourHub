/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DataAccess.BookingDB;
import DataAccess.ReportDB;
import DataAccess.ReportErrorDB;
import DataAccess.TourDB;
import DataAccess.UserDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import model.Booking;
import model.ReportError;
import model.Tour;
import model.User;

/**
 *
 * @author NOMNOM
 */
@WebServlet(name = "ManagementServlet", urlPatterns = {"/manage"})
public class ManagementServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ReportErrorDB reportErrorDB = new ReportErrorDB();

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
        String action = request.getParameter("action");

        // Sử dụng switch-case để xử lý các loại yêu cầu khác nhau
        switch (action) {
            case "user-manage":
                viewUserList(request, response);
                break;

            case "tour-manage":
                viewTourList(request, response);
                break;

            case "report-manage":
                viewReportList(request, response);
                break;

            case "booking-manage":
                viewBookingList(request, response);
                break;

            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
        }
    }

    protected void viewUserList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDB user = new UserDB();
        List<User> userList = user.getAllUsers();
        request.setAttribute("data", userList);
        request.setAttribute("type", "user");
        System.out.println("Day là" + userList);
        request.getRequestDispatcher("display.jsp").forward(request, response);
    }

    protected void viewTourList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        TourDB tour = new TourDB();
        List<Tour> tourList = tour.getAllTours();
        request.setAttribute("data", tourList);
        request.setAttribute("type", "tour");
        System.out.println("Day là" + tourList);
        request.getRequestDispatcher("display.jsp").forward(request, response);
    }

    protected void viewReportList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ReportErrorDB report = new ReportErrorDB();
        List<ReportError> reportList = report.getAllReports();
        request.setAttribute("data", reportList);
        request.setAttribute("type", "report");
        System.out.println("Day là" + reportList);
        request.getRequestDispatcher("display.jsp").forward(request, response);
    }

    protected void viewBookingList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BookingDB booking = new BookingDB();
        List<Booking> bookingList = booking.getAllBookings();
        request.setAttribute("data", bookingList);
        request.setAttribute("type", "booking");
        System.out.println("Day là" + bookingList);
        request.getRequestDispatcher("display.jsp").forward(request, response);
    }

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

//        private String viewTours
//            //View, Delete, Approve before publish
//        Users
//            //Ban -> View ban -> unban
    //Bill data nên để thống kê 
    // Discount của provider 
    // Wishlist của user 
    //Review ( Comment ) AI suggestion ?
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
