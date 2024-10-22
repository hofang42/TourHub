package controller;

import DataAccess.UserDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import java.io.IOException;
import utils.Encrypt;

@WebServlet(name = "GoogleRegisterServlet", urlPatterns = {"/completeRegistration"})
public class GoogleRegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("currentUser");

        if (user == null) {
            // Người dùng chưa đăng nhập, redirect về trang login
            response.sendRedirect("login.jsp");
            return;
        }

        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        // Cập nhật thông tin vào User object
        user.setPhone(phone);
        user.setAddress(address);
        user.setPassword(Encrypt.toSHA256(password)); // Mã hóa password
        user.setRole(role);

        // Lưu thông tin cập nhật vào database
        UserDB userDB = new UserDB();
        userDB.updateGoogleAccount(user);

        // Cập nhật session
        session.setAttribute("currentUser", user);

        // Kiểm tra role và điều hướng
        if ("Customer".equalsIgnoreCase(role)) {
            UserDB customerDB = new UserDB();
            if (!customerDB.hasCustomerInfo(user.getUser_Id())) {
                // Nếu chưa có thông tin customer thì điều hướng tới customerinfo.jsp
                response.sendRedirect("customerInfo.jsp");
            } else {
                response.sendRedirect("home");
            }
        } else if ("provider".equalsIgnoreCase(role)) {
            if (!userDB.hasCompanyInfo(user.getUser_Id())) {
                // Nếu chưa có thông tin company thì điều hướng tới companyinfo.jsp
                response.sendRedirect("companyInfo.jsp");
            } else {
                response.sendRedirect("home");
            }
        } else {
            // Nếu role không hợp lệ, điều hướng về trang chủ
            response.sendRedirect("home");
        }
    }
}
