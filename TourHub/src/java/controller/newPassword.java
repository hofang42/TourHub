/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.UserDB;
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
 * @author duc
 */
@WebServlet(name = "newPassword", urlPatterns = {"/newPassword"})
public class newPassword extends HttpServlet {

    public static Connection conn = null;
    public static PreparedStatement ps = null;
    public static ResultSet rs = null;

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
            out.println("<title>Servlet newPassword</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet newPassword at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

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
                    response.sendRedirect("newPassword.jsp?status=resetFailed");
                }
            } catch (SQLException e) {
                // Xử lý ngoại lệ SQL
                e.printStackTrace();
                response.sendRedirect("newPassword.jsp?status=resetFailed");
            }
        } else {
            // Nếu mật khẩu không khớp, thông báo lỗi
            response.sendRedirect("newPassword.jsp?status=passwordMismatch");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}