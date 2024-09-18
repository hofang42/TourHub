/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DataAccess.UserDB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.User;

/**
 *
 * @author NOMNOM
 */
@WebServlet(name = "UserServlet", urlPatterns = {"/user"})
public class UserServlet extends HttpServlet {

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
        String action = request.getParameter("action");

        if (action == null) {
            action = "login"; // Default action
        }

        switch (action.toLowerCase()) {
            case "login":
                //handleLogin(request, response);
                break;
            case "log out":
                //handleLogout(request, response);
                break;
            case "signup":
                //handleSignUp(request, response);
                break;
            case "db":
                //handleDashBoard(request, response);
                break;
            case "update":
                handleUpdate(request, response);
                break;
            case "updatepass":
                handleUpdatePassword(request, response);
                break;
            case "updateemail":
                handleUpdateEmail(request, response);
                break;
            case "checkotp":
                handleCheckOtp(request, response);
                break;
            default:
                response.sendRedirect("error.jsp");
                break;
        }
    }

    private void handleUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy thông tin từ form
        int userId = Integer.parseInt(request.getParameter("userId"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");

        // Tạo một đối tượng User
        User user = new User(userId, username, password, firstName, lastName, phone, email, address);

        // Cập nhật thông tin người dùng trong cơ sở dữ liệu
        UserDB userDB = new UserDB();
        boolean isUpdated = userDB.updateUser(user);

        if (isUpdated) {
            // Nếu cập nhật thành công, lưu thông tin mới vào session
            HttpSession session = request.getSession();
            session.setAttribute("currentUser", user);

            // Chuyển hướng trở lại trang thông tin người dùng
            response.sendRedirect("user-profile.jsp");
        } else {
            // Xử lý nếu cập nhật thất bại
            response.sendRedirect("user-updateinfo.jsp?error=UpdateFailed");
        }
    }

    private void handleUpdatePassword(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy thông tin từ form
    int userId = Integer.parseInt(request.getParameter("userId"));
    String currentPassword = request.getParameter("password");
    String newPassword = request.getParameter("newPassword");
    String confirmPassword = request.getParameter("confirmPassword");

    // Lấy đối tượng User hiện tại từ cơ sở dữ liệu
    UserDB userDb = new UserDB();
    User currentUser = userDb.getUser(userId);

    RequestDispatcher dispatcher;

    // Kiểm tra mật khẩu hiện tại có khớp không
    if (!currentUser.getPassword().equals(currentPassword)) {
        // Mật khẩu hiện tại không đúng
        request.setAttribute("error", "UpdateFailed");
        dispatcher = request.getRequestDispatcher("user-updateinfo.jsp?buttonChange=pass&error=UpdateFailed");
        dispatcher.forward(request, response);
        return;
    }

    // Kiểm tra mật khẩu mới và mật khẩu xác nhận có khớp không
    if (!newPassword.equals(confirmPassword)) {
        // Mật khẩu mới không khớp với mật khẩu xác nhận
        request.setAttribute("error", "UpdateFailed");
        dispatcher = request.getRequestDispatcher("user-updateinfo.jsp?buttonChange=pass&error=UpdateFailed");
        dispatcher.forward(request, response);
        return;
    }

    // Cập nhật mật khẩu mới trong cơ sở dữ liệu
    boolean isUpdated = userDb.updatePassword(userId, newPassword);

    if (isUpdated) {
        // Nếu cập nhật thành công, lưu thông tin mới vào session
        HttpSession session = request.getSession();
        session.setAttribute("currentUser", userDb.getUser(userId)); // Lấy user mới sau khi update

        // Chuyển hướng trở lại trang thông tin người dùng
        response.sendRedirect("user-profile.jsp");
    } else {
        // Xử lý nếu cập nhật thất bại
        request.setAttribute("error", "UpdateFailed");
        dispatcher = request.getRequestDispatcher("user-updateinfo.jsp?buttonChange=pass&error=UpdateFailed");
        dispatcher.forward(request, response);
    }
    }

    private void handleUpdateEmail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        String email = request.getParameter("email");

        RequestDispatcher dispatcher = null;
        HttpSession mySession = request.getSession();

        if (email != null && !email.trim().isEmpty()) {
            UserDB dao = new UserDB();

            //Kiem tra email da dang ki chua
            boolean emailExists = dao.checkEmailExists(email);

            if (!emailExists) {

                Random rand = new Random();
                int otpvalue = rand.nextInt(1255650);

                String to = email;
                Properties props = new Properties();
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.port", "587");
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.enable", "true");

                Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("tourhubforlife@gmail.com", "zlnk ggii octx hbdf");
                    }
                });

                try {
                    MimeMessage message = new MimeMessage(session);
                    message.setFrom(new InternetAddress("tourhubforlife@gmail.com"));
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                    message.setSubject("OTP for Password Reset");
                    message.setText("Your OTP is: " + otpvalue);

                    Transport.send(message);
                    System.out.println("Message sent successfully");
                } catch (MessagingException e) {
                    throw new RuntimeException("Error sending email", e);
                }

                dispatcher = request.getRequestDispatcher("user-otp.jsp");
                request.setAttribute("message", "OTP is sent to your email id");
                mySession.setAttribute("otp", otpvalue);
                mySession.setAttribute("email", email);
                mySession.setAttribute("userId", userId);  // Set type for password reset
                dispatcher.forward(request, response);
            } else {
                dispatcher = request.getRequestDispatcher("user-updateinfo.jsp?buttonChange=email");
                request.setAttribute("error", "Email is already registered");
                dispatcher.forward(request, response);
            }
        } else {

            dispatcher = request.getRequestDispatcher("user-updateinfo.jsp?buttonChange=email");
            request.setAttribute("error", "Please enter a valid email address");
            dispatcher.forward(request, response);
        }

    }

    private void handleCheckOtp(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        RequestDispatcher dispatcher;
        int otp = Integer.parseInt(request.getParameter("otp"));
        int sessionOtp = (Integer) session.getAttribute("otp");
        String email = (String) session.getAttribute("email");
        int userId = (Integer) session.getAttribute("userId");

        if (otp == sessionOtp) {

            UserDB userDb = new UserDB();

            userDb.updateEmail(userId, email);
            User newUser = userDb.getUser(userId);

            session.setAttribute("currentUser", newUser);
            response.sendRedirect("user-profile.jsp");

        } else {
            request.setAttribute("message", "Invalid OTP. Please try again.");
            dispatcher = request.getRequestDispatcher("user-otp.jsp");
            dispatcher.forward(request, response);
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

}
