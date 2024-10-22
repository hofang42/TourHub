/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DataAccess.ThienDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import model.User;
import model.Wishlist;

/**
 *
 * @author NOMNOM
 */
@WebServlet(name = "WishlistServlet", urlPatterns = {"/wishlist"})
public class WishlistServlet extends HttpServlet {

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
            out.println("<title>Servlet WishlistServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet WishlistServlet at " + request.getContextPath() + "</h1>");
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
        // Fetch wishlist data from the database
        User currentUser = (User) request.getSession().getAttribute("currentUser");

        ThienDB wishlistdb = new ThienDB();
        int cus_Id = wishlistdb.getCusIdFromUserId(currentUser.getUser_Id());
        List<Wishlist> wishlistItems = wishlistdb.getWishlistFromDB(cus_Id);
        System.out.println(cus_Id);
        System.out.println(wishlistItems);

        // Set the wishlist data as a request attribute
        request.setAttribute("wishlistItems", wishlistItems);

        // Forward the request to the JSP
        // nên để response về web nớ
        request.getRequestDispatcher("user-wishlist.jsp").forward(request, response);
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
        String action = request.getParameter("action");
        if ("delete".equals(action)) {
            deleteWishlistItem(request, response);
        } else if ("add".equals(action)) {
            addWishlistItem(request, response);
        } else {
            processRequest(request, response);
        }
    }

    private void deleteWishlistItem(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve the wishlist item ID from the request
        String wishIdStr = request.getParameter("wishId");
        int wishId = Integer.parseInt(wishIdStr);

        // Call the method to delete the wishlist item
        ThienDB wishlistdb = new ThienDB();
        boolean isDeleted = wishlistdb.deleteWishlistItem(wishId);

        // Optionally, you can set a message to indicate success or failure
        if (isDeleted) {
            request.setAttribute("message", "Wishlist item deleted successfully.");
        } else {
            request.setAttribute("message", "Failed to delete wishlist item.");
        }

        // Redirect back to the wishlist page to show the updated list
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        int cus_Id = wishlistdb.getCusIdFromUserId(currentUser.getUser_Id());
        List<Wishlist> wishlistItems = wishlistdb.getWishlistFromDB(cus_Id);
        request.setAttribute("wishlistItems", wishlistItems);

        // Forward the request to the JSP
        request.getRequestDispatcher("user-wishlist.jsp").forward(request, response);
    }

    private void addWishlistItem(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String tourId = request.getParameter("tourId");
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        String returnUrl = request.getParameter("returnUrl");
        ThienDB wishlistdb = new ThienDB();
        int cus_Id = wishlistdb.getCusIdFromUserId(currentUser.getUser_Id());

        // Call a method to add the tour to the user's wishlist
        boolean success = wishlistdb.addToWishlist(cus_Id, tourId);

        if (success) {
            // Redirect or set a success message
            response.sendRedirect(returnUrl); // Redirect to the wishlist page
        } else {
            // Handle the case where adding failed
            request.setAttribute("errorMessage", "Could not add to wishlist. Please try again.");
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
