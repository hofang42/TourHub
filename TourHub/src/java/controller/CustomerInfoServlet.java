package controller;

import DataAccess.UserDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Customer;
import model.User;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "CustomerInfoServlet", urlPatterns = {"/updateCustomerInfo"})
public class CustomerInfoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("currentUser");

        if (user == null || !user.getRole().equals("Customer")) {
            // If the user is not logged in or not a customer, redirect to login
            response.sendRedirect("login.jsp");
            return;
        }

        // Get customer details from form
        String birthDateStr = request.getParameter("birthDate");

        // Validate that the customer is at least 18 years old
        java.sql.Date birthDate = java.sql.Date.valueOf(birthDateStr);
        java.sql.Date today = new java.sql.Date(System.currentTimeMillis());
        long ageInMillis = today.getTime() - birthDate.getTime();
        long ageInYears = ageInMillis / (1000L * 60 * 60 * 24 * 365);

        if (ageInYears < 18) {
            // If user is less than 18, redirect to an error page or return a message
            request.setAttribute("errorMessage", "You must be at least 18 years old.");
            request.getRequestDispatcher("customerInfo.jsp").forward(request, response);
            return;
        }

        // Create a new Customer object
        Customer customer = new Customer();
        customer.setCus_Birth(birthDate);
        customer.setUser_Id(user.getUser_Id());

        // Save customer details to the database
        UserDB customerDB = new UserDB();
        try {
            customerDB.saveCustomer(customer);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("customerInfo.jsp"); // Redirect to error page on failure
            return;
        }

        user.setCus_Birth(birthDate);
        session.setAttribute("currentUser", user);

        // Redirect to homepage or customer dashboard after successful update
        response.sendRedirect("home");
    }
}

