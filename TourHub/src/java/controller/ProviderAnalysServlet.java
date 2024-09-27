/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DataAccess.CompanyDB;
import DataAccess.TourDB;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

/**
 *
 * @author hoang
 */
@WebServlet(name = "ProviderAnalysServlet", urlPatterns = {"/provider-analys"})
public class ProviderAnalysServlet extends HttpServlet {

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
        doPost(request, response);
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
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("currentUser");

        // Check if the user is null
        if (user == null) {
            response.sendRedirect("login.jsp"); // Redirect to login if not authenticated
            return; // Stop further processing
        }

        System.out.println("USER PROFILE" + user.getUserId());
        TourDB tourDB = new TourDB();
        CompanyDB companyDB = new CompanyDB();
        int companyId = 0;
        try {
            companyId = companyDB.getCompanyIdFromUserId(user.getUserId());
        } catch (SQLException ex) {
            Logger.getLogger(ProviderAnalysServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        int totalVisitATour = tourDB.getTotalVisitATour(companyId);
        int visitToday = tourDB.getTodayVisit(companyId);
        int bookingThisMonth = tourDB.getTotalBookingThisMonth(companyId);
        request.getSession().setAttribute("totalVisitATour", totalVisitATour);
        request.getSession().setAttribute("visitToday", visitToday);
        request.getSession().setAttribute("bookingThisMonth", bookingThisMonth);
        request.getSession().setAttribute("currentUser", user);

        request.getRequestDispatcher("provider-analysis.jsp").forward(request, response);
//        response.sendRedirect("provider-analysis.jsp");
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
        TourDB tourDB = new TourDB();
        int totalVisitATour = tourDB.getTotalBookingThisMonth(2);
        System.out.println(totalVisitATour);
    }
}
