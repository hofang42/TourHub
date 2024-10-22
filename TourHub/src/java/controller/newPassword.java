package controller;

import DataAccess.UserDB;
import utils.Encrypt; // Import class Encrypt
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet(name = "newPassword", urlPatterns = {"/newPassword"})
public class newPassword extends HttpServlet {

    public static Connection conn = null;
    public static PreparedStatement ps = null;
    public static ResultSet rs = null;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String newPassword = request.getParameter("password");
        String confPassword = request.getParameter("confPassword");

        // Kiểm tra mật khẩu mới và mật khẩu xác nhận
        if (newPassword != null && confPassword != null && newPassword.equals(confPassword)) {
            try {
                // Mã hóa mật khẩu mới
                String encryptedPassword = Encrypt.toSHA256(newPassword);

                // Kết nối CSDL
                conn = new UserDB().getConnect();

                // Chuẩn bị câu lệnh SQL
                String query = "UPDATE [User] SET password = ? WHERE email = ?;";
                ps = conn.prepareStatement(query);
                ps.setString(1, encryptedPassword); // Đặt mật khẩu đã mã hóa vào SQL
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

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
