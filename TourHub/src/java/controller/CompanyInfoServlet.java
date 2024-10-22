package controller;

import DataAccess.UserDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Company;
import model.User;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

@WebServlet(name = "CompanyInfoServlet", urlPatterns = {"/updateCompanyInfo"})
public class CompanyInfoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("currentUser");

        if (user == null || !user.getRole().equals("Provider")) {
            // If the user is not logged in or not a provider, redirect to login
            response.sendRedirect("login.jsp");
            return;
        }

        // Get company details from form
        String taxCode = request.getParameter("taxCode");
        String bankInformation = request.getParameter("bankInformation");

        // Create a new Company object
        Company company = new Company();
        company.setTaxCode(taxCode);
        company.setBankInformation(bankInformation);
        company.setUser_Id(user.getUser_Id());
        company.setBalance(BigDecimal.ZERO); // Initial balance

        // Save company details to the database
        UserDB companyDB = new UserDB();
        try {
            companyDB.saveCompany(company);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp"); // Redirect to error page on failure
            return;
        }
        
        user.setTax_Code(taxCode);
        user.setBank_Information(bankInformation);
        user.setBalance(BigDecimal.valueOf(0));
        session.setAttribute("currentUser", user);

        // Redirect to homepage or provider dashboard after successful update
        response.sendRedirect("home");
    }
}
