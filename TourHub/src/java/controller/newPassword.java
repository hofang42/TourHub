/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.UserDB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author NOMNOM
 */
@WebServlet(name = "newPassword", urlPatterns = {"/newPassword"})
public class newPassword extends HttpServlet {

    public static Connection conn = null;
    public static PreparedStatement ps = null;
    public static ResultSet rs = null;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet newPassword</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet newPassword at " + request.getContextPath() + "</h1>");
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
        String newPassword = request.getParameter("password");
        String confPassword = request.getParameter("confPassword");

        // Kiểm tra mật khẩu mới và mật khẩu xác nhận
        if (newPassword != null && confPassword != null && newPassword.equals(confPassword)) {
            try {
                // Kết nối CSDL
                conn = new UserDB().getConnect();
                // Chuẩn bị câu lệnh SQL
                String query = "UPDATE [User] SET password = ? WHERE email = ?;";
                ps = conn.prepareStatement(query);
                ps.setString(1, newPassword);
                ps.setString(2, (String) session.getAttribute("email"));

                // Thực thi câu lệnh SQL
                int rowCount = ps.executeUpdate();

                // Đóng kết nối
                conn.close();

                // Chuyển hướng và thông báo trạng thái
                if (rowCount > 0) {
                    response.sendRedirect("login.jsp?status=resetSuccess");
                } else {
                    response.sendRedirect("newpassword.jsp?status=resetFailed");
                }
            } catch (SQLException e) {
                // Xử lý ngoại lệ SQL
                e.printStackTrace();
                response.sendRedirect("newpassword.jsp?status=resetFailed");
            }
        } else {
            // Nếu mật khẩu không khớp, thông báo lỗi
            response.sendRedirect("newpassword.jsp?status=passwordMismatch");
        }
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
