/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DataAccess.DiscountDB;
import DataAccess.ProvinceDB;
import DataAccess.TourDB;
import DataAccess.hoang_UserDB;
import com.google.gson.Gson;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Discount;
import model.Province;
import model.Review;
import model.Tour;

/**
 *
 * @author hoang
 */
@WebServlet(name = "ListAllTour", urlPatterns = {"/home"})
public class HomeServlet extends HttpServlet {

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
            hoang_UserDB hoangDB = new hoang_UserDB();
            List<Tour> tours = tourDB.getTours();  // Ensure getTours() handles exceptions
            String toursJson = new Gson().toJson(tours);
            request.setAttribute("toursJson", toursJson);
            request.setAttribute("tours", tours);

            ProvinceDB provinceDB = new ProvinceDB();
            List<Province> provinces = provinceDB.getProvinceByVisitCount();
            request.getSession().setAttribute("provinces", provinces);
            List<Discount> discounts = new hoang_UserDB().getAllDiscounts();
            List<Map<String, Object>> discountWithTour = new ArrayList<>();
// Loop through each discount and fetch the corresponding tour
            for (Discount discount : discounts) {
                Map<String, Object> discountTourMap = new HashMap<>();

                // Get the corresponding tour object from tour_Id
                Tour tour = tourDB.getTourFromTourID(discount.getTour_Id());

                // Add discount and tour details to the map
                discountTourMap.put("discount", discount);
                discountTourMap.put("tour", tour);

                discountWithTour.add(discountTourMap);
            }

            List<Review> reviews = hoangDB.getTop5ReviewsByLikes();
            // Create a list of maps to hold both review and associated tour image
            List<Map<String, Object>> reviewWithTourImages = new ArrayList<>();
// Loop through each review and fetch the associated tour image
            for (Review review : reviews) {
                Map<String, Object> reviewData = new HashMap<>();

                // Fetch the tour details associated with the review
                Tour tour = tourDB.getTourFromTourID(review.getTour_Id());

                // Add the review and tour image to the map
                reviewData.put("review", review);           // Add review details
                reviewData.put("tour", tour);  // Add tour image from the Tour table

                reviewWithTourImages.add(reviewData);  // Add to the final list
            }
// Set the list with reviews and tour images in the request scope
            request.setAttribute("reviewWithTourImages", reviewWithTourImages);
// Pass the list of discount and tour details to the JSP
            request.getSession().setAttribute("discountWithTour", discountWithTour);
//            request.getSession().setAttribute("discounts", discounts);
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
