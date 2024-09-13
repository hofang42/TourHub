package controller;

import DAO.UserDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import model.User;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

        // Set default values for userStatus and role
        String userStatus = "unverified"; // Default user status
        String role = "customer"; // Default user role

        UserDB userDB = new UserDB();

        // Check if the username or email already exists
        if (userDB.isUsernameExists(username)) {
            request.setAttribute("error", "Username already exists.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        if (userDB.isEmailExists(email)) {
            request.setAttribute("error", "Email already exists.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // If no duplicates, register the user
        boolean isRegistered = userDB.registerUser(new User(0, username, password, firstName, lastName, phone, email, address, new Date(), userStatus, role));

        if (isRegistered) {
            response.sendRedirect("login.jsp");
        } else {
            request.setAttribute("error", "Registration failed. Try again.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }

    private boolean isValidPassword(String password) {
        return password.length() >= 8 && password.length() <= 16
                && password.chars().anyMatch(Character::isUpperCase)
                && password.chars().anyMatch(Character::isLowerCase)
                && password.chars().anyMatch(Character::isDigit);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

        UserDB userDB = new UserDB();
        String error = "";

        // Kiểm tra username trùng
        if (userDB.isUsernameExists(username)) {
            error += "Username đã được sử dụng. ";
        }

        // Kiểm tra email trùng
        if (userDB.isEmailExists(email)) {
            error += "Email đã được sử dụng. ";
        }

        // Kiểm tra mật khẩu
        if (!isValidPassword(password)) {
            error += "Mật khẩu phải từ 8-16 ký tự và bao gồm chữ cái in hoa, in thường và số. ";
        }

        // Kiểm tra mật khẩu khớp
        if (!password.equals(confirmPassword)) {
            error += "Mật khẩu xác nhận không khớp. ";
        }

        if (!error.isEmpty()) {
            request.setAttribute("error", error);
            request.setAttribute("username", username);
            request.setAttribute("email", email);
            request.setAttribute("firstName", firstName);
            request.setAttribute("lastName", lastName);
            request.setAttribute("phone", phone);
            request.setAttribute("address", address);
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // Nếu không có lỗi, tiến hành đăng ký người dùng
        boolean isRegistered = userDB.registerUser(new User(0, username, password, firstName, lastName, phone, email, address, new Date(), "unverified", "customer"));

        if (isRegistered) {
            response.sendRedirect("login.jsp");
        } else {
            request.setAttribute("error", "Đăng ký không thành công. Vui lòng thử lại.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }

}
