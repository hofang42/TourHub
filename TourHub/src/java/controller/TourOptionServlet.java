/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import DataAccess.UserDB;
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
import model.Tour;
import model.TourOption;
/**
 *
 * @author LENOVO
 */
@WebServlet(name="TourOptionServlet", urlPatterns={"/tourOptions"})
public class TourOptionServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet TourOptionServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TourOptionServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        // Lấy dayOfWeek từ yêu cầu
//        String dayOfWeekStr = request.getParameter("dayOfWeek");
//        int dayOfWeek = Integer.parseInt(dayOfWeekStr);
//
//        // Lấy tourId từ session hoặc từ request
//        int tourId = Integer.parseInt(request.getParameter("tourId"));
//
//        // Gọi hàm để lấy danh sách các tourOption theo dayOfWeek
//        UserDB u = new UserDB();
//        List<TourOption> tourOptions = u.getTourOptionsByTourIdAndDayOfWeek(tourId, dayOfWeek);
//
//        // Trả về dữ liệu JSON
//        response.setContentType("application/json");
//        PrintWriter out = response.getWriter();
//        Gson gson = new Gson();
//        out.print(gson.toJson(tourOptions));
//        out.flush();
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
