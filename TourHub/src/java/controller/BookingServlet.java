/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DataAccess.BookingDB;
import DataAccess.ThienDB;
import com.google.gson.Gson;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import model.Booking;
import model.User;

/**
 *
 * @author NOMNOM
 */
@WebServlet(name = "booking", urlPatterns = {"/booking"})
public class BookingServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

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
            out.println("<title>Servlet BookingServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BookingServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Assuming you have logic to get the current user bookings from DB
        User currentUser = (User) request.getSession().getAttribute("currentUser");

        System.out.println("Current User ID: " + currentUser.getUser_Id());
        ThienDB book = new ThienDB();
        int cus_Id = book.getCusIdFromUserId(currentUser.getUser_Id());
        BookingDB booking = new BookingDB();
        List<Booking> bookings = booking.getUser2Booking(cus_Id);
        request.setAttribute("bookings", bookings);

        // Forward the request to user-booking.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-booking.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "login"; // Default action
        }

        switch (action.toLowerCase()) {
            case "cancelbook":
                handleCancelBook(request, response);
                break;
            default:
                response.sendRedirect("error.jsp");
                break;
        }
    }

    public void handleCancelBook(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String bookingId = request.getParameter("id");
        User currentUser = (User) request.getSession().getAttribute("currentUser");

        ThienDB book = new ThienDB();
        int cus_Id = book.getCusIdFromUserId(currentUser.getUser_Id());

        boolean updateSuccess = book.updateBookingStatus(cus_Id, bookingId);

        book.updateBookingStatus(cus_Id, bookingId);
        response.sendRedirect("booking");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
