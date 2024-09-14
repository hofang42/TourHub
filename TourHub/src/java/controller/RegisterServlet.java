package controller;

import DAO.UserDB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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

        // Check for username existence
        if (userDB.isUsernameExists(username)) {
            error += "Username already exists. ";
        }

        // Check for email existence
        if (userDB.isEmailExists(email)) {
            error += "Email already exists. ";
        }

        // Validate password
        if (!isValidPassword(password)) {
            error += "Password must be 8-16 characters long, include uppercase, lowercase letters, and a number. ";
        }

        // Confirm password match
        if (!password.equals(confirmPassword)) {
            error += "Password confirmation does not match. ";
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

        // If no errors, proceed to register the user
        boolean isRegistered = userDB.registerUser(new User(0, username, password, firstName, lastName, phone, email, address, new Date(), "unverified", "customer"));

        if (isRegistered) {
            // Generate OTP
            Random rand = new Random();
            int otp = rand.nextInt(999999);

            // Send the OTP to the user's email
            sendOtpEmail(email, otp);

            // Store OTP in session for validation later
            HttpSession session = request.getSession();
            session.setAttribute("otp", otp);
            session.setAttribute("email", email);
            session.setAttribute("type", "register");  // Set type for registration
            // Redirect to the OTP verification page
            request.setAttribute("message", "OTP has been sent to your email. Please verify.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("enterotp.jsp");
            dispatcher.forward(request, response);
        } else {
            request.setAttribute("error", "Registration failed. Please try again.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }

    private boolean isValidPassword(String password) {
        return password.length() >= 8 && password.length() <= 16
                && password.chars().anyMatch(Character::isUpperCase)
                && password.chars().anyMatch(Character::isLowerCase)
                && password.chars().anyMatch(Character::isDigit);
    }

    private void sendOtpEmail(String to, int otp) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("trongducdoan25@gmail.com", "fymw ilhs vtuj sczy");
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("trongducdoan25@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("OTP for Email Verification");
            message.setText("Your OTP is: " + otp);
            Transport.send(message);
            System.out.println("OTP email sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
