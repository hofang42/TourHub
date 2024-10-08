/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DataAccess.UserDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import model.Tour;

/**
 *
 * @author LENOVO
 */
@WebServlet(name = "DisplaySearchServlet", urlPatterns = {"/allTour"})
public class DisplayAllServlet extends HttpServlet {

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
            out.println("<title>Servlet DisplaySearchServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DisplaySearchServlet at " + request.getContextPath() + "</h1>");
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
        UserDB u = new UserDB();

        // Get the sort order from the request
        String sortOrder = request.getParameter("sortOrder");
        if (sortOrder == null || sortOrder.isEmpty()) {
            sortOrder = "popularity";  // Default sort by popularity
        }

        // Get the location from the request
        String location = request.getParameter("location");
        if (location == null || location.isEmpty()) {
            location = "All";  // Default to "All"
        }

        // Get the price range from the request
        String priceRange = request.getParameter("priceRange");
        int minPrice = 0;
        int maxPrice = 0;

        // Parse the price range if provided
        if (priceRange != null && !priceRange.equals("0-0")) {
            String[] prices = priceRange.split("-");
            minPrice = Integer.parseInt(prices[0]);
            maxPrice = Integer.parseInt(prices[1]);
        }

        // Call the getAll method with the sorting, location, and price filters
        List<Tour> list = u.getAll(sortOrder, location, minPrice, maxPrice);
        request.setAttribute("data", list);

        // Pass the sortOrder, location, and priceRange back to the JSP
        request.setAttribute("sortOrder", sortOrder);
        request.setAttribute("location", location);
        request.setAttribute("priceRange", priceRange);

        request.getRequestDispatcher("search-page.jsp").forward(request, response);
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
