/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DataAccess.BookingDB;
import DataAccess.ReportErrorDB;
import DataAccess.ThienDB;
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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "user-manage":
                viewUserList(request, response);
                break;

            case "user-ban":
                userManage(request, response);
                break;

            case "user-unban":
                userManage(request, response);
                break;

            case "tour-manage":
                viewTourList(request, response);
                break;
                
            case "approve-tour":
                tourManage(request, response);
                break;

            case "cancel-tour":
                tourManage(request, response);
                break;

            case "report-manage":
                viewReportList(request, response);
                break;

            case "delete-report":
                reportManage(request, response);
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
        List<User> userList = user.getAllUsersNotAdmin();
        request.setAttribute("data", userList);
        request.setAttribute("type", "user");
        System.out.println("Day là" + userList);
        request.getRequestDispatcher("display.jsp").forward(request, response);
    }

    protected void userManage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        int userId = Integer.parseInt(request.getParameter("id"));

        ThienDB userDAO = new ThienDB();
        try {
            if ("user-ban".equals(action)) {
                userDAO.banAccount(userId);
                request.getSession().setAttribute("message", "Account banned successfully!");
            } else if ("user-unban".equals(action)) {
                userDAO.unbanAccount(userId);
                request.getSession().setAttribute("message", "Account unbanned successfully!");
            }
            response.sendRedirect("manage?action=user-manage");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
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
    
    protected void tourManage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String tourId = request.getParameter("id");
        System.out.println(action);

        ThienDB tourDAO = new ThienDB();
        try {
            if ("approve-tour".equals(action)) {
                tourDAO.approveTour(tourId);
                request.getSession().setAttribute("message", "Tour approved successfully!");
            } else if ("cancel-tour".equals(action)) {
                tourDAO.cancelTour(tourId);
                request.getSession().setAttribute("message", "Tour cancelled successfully!");
            }
            response.sendRedirect("manage?action=tour-manage");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
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

    protected void reportManage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("delete-report".equals(action)) {
            int reportId = Integer.parseInt(request.getParameter("id"));

            ThienDB reportDAO = new ThienDB();
            try {
                reportDAO.deleteReport(reportId);
                request.getSession().setAttribute("message", "Report deleted successfully!");
                response.sendRedirect("manage?action=report-manage");
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("error.jsp");
            }
        }
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
