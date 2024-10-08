/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DataAccess.TourDB;
import DataAccess.UserDB;
import DataAccess.hoang_UserDB;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Tour;

/**
 *
 * @author hoang
 */
@WebServlet(name = "SearchTourByIdServlet", urlPatterns = {"/SearchTourByIdServlet"})
public class SearchTourByIdServlet extends HttpServlet {

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
            out.println("<title>Servlet SearchTourByIdServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchTourByIdServlet at " + request.getContextPath() + "</h1>");
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
        String tourId = request.getParameter("tour-edit");

        TourDB tourDB = new TourDB();
        int companyId;

        try {
            // Fetch the provider Id from user session
            companyId = new hoang_UserDB().getProviderIdFromUserId(new UserDB().getUserFromSession(request.getSession()).getUser_Id());
            System.out.println("GET SUCCESS: Company ID = " + companyId);
        } catch (SQLException ex) {
            Logger.getLogger(SearchTourByIdServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("errorMessage", "Database error occurred while fetching the company ID.");
            request.getRequestDispatcher("edit-tour.jsp").forward(request, response);
            return;
        }

        // If no tourId is provided, fetch all tours
        if (tourId == null || tourId.trim().isEmpty()) {
            List<Tour> allTours = tourDB.getToursByProviderID(companyId);
            if (allTours.isEmpty()) {
                request.setAttribute("errorMessage", "No tours available.");
            } else {
                request.setAttribute("providerTours", allTours);
            }
            request.getRequestDispatcher("edit-tour.jsp").forward(request, response);
            return;
        }

        // Retrieve the tour details by tourId
        Tour tourEdit = tourDB.getTourFromTourID(tourId, companyId);

        // Check if the tour was found
        if (tourEdit == null) {
            request.setAttribute("errorMessage", "No tour found with the given ID.");
            request.getRequestDispatcher("edit-tour.jsp").forward(request, response);
            return;
        }

        // Set the tourEdit object in request scope and forward to the edit page
        request.setAttribute("tourEdit", tourEdit);
        Tour tourEditSession = tourEdit;
        request.getSession().setAttribute("tourEditSession", tourEditSession);
        request.getRequestDispatcher("mytour.jsp").forward(request, response);
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
