/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DataAccess.ProvinceDB;
import DataAccess.TourDB;
import com.google.gson.Gson;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Province;
import model.Tour;

/**
 *
 * @author hoang
 */
@WebServlet(name = "ListAllTour", urlPatterns = {"/home"})
public class ListAllTour extends HttpServlet {

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
        try {
            TourDB tourDB = new TourDB();
            List<Tour> tours = tourDB.getTours();  // Ensure getTours() handles exceptions
            String toursJson = new Gson().toJson(tours);
            request.setAttribute("toursJson", toursJson);
            request.setAttribute("tours", tours);

            ProvinceDB provinceDB = new ProvinceDB();
            List<Province> provinces = provinceDB.getProvinceByVisitCount();
            request.getSession().setAttribute("provinces", provinces);

            request.getRequestDispatcher("index.jsp").forward(request, response);  // Make sure index.jsp exists
        } catch (Exception e) {
            // Handle potential exceptions (e.g., DB connection issues)
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
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

    public static void main(String[] args) {
        TourDB tourDB = new TourDB();
        List<Tour> tours = tourDB.getTours();
        String toursJson = new Gson().toJson(tours);
        System.out.println(toursJson.toString());
    }
}
