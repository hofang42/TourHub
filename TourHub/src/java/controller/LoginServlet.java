package controller;

import DAO.UserDB;
import model.User;
import entity.GoogleAccount;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String code = request.getParameter("code"); // Google login code

        UserDB userDB = new UserDB();
        User user = null;
        
        if (code != null) {
            // Google Login Flow
            GoogleLogin googleLogin = new GoogleLogin();
            String accessToken = googleLogin.getToken(code);
            GoogleAccount googleAccount = googleLogin.getUserInfo(accessToken);

            // Check if Google account exists in the User table
            user = userDB.authenticate(googleAccount.getEmail(), null);
            
            if (user == null) {
                // Register the Google user if not found in the database
                user = new User();
                user.setUsername(googleAccount.getName());
                user.setEmail(googleAccount.getEmail());
                user.setFirstName(googleAccount.getGiven_name());
                user.setLastName(googleAccount.getFamily_name());
                user.setPassword(""); // No password for Google users
                user.setCreatedAt(new java.util.Date());
                user.setUserStatus("verified");
                user.setRole("customer");
                userDB.registerUser(user); // Save user in the DB
            }
        } else if (email != null && password != null) {
            // Manual login
            user = userDB.authenticate(email, password);
            
            if (user == null) {
                request.setAttribute("error", "Invalid email or password");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }
        } else {
            // Neither manual login nor Google login attempt
            response.sendRedirect("login.jsp");
            return;
        }
        
        if (user != null) {
            // Save user information in session
            HttpSession session = request.getSession();
            session.setAttribute("currentUser", user);

            // Redirect to the homepage or user dashboard
            response.sendRedirect("/Project_SWP/home");            
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
        processRequest(request, response);
    }
    
    @Override
    public String getServletInfo() {
        return "Handles Google and manual login";
    }
}
