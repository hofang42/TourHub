package controller;

import DataAccess.ReportsDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.ReportError;
import model.User;
import java.io.IOException;
import java.util.Date;

@WebServlet(name = "ReportErrorServlet", urlPatterns = {"/reporterror"})
public class ReportErrorServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String reportDetails = request.getParameter("reportDetails");
        String reportType = request.getParameter("reportType");

        // Retrieve current user from session
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");

        if (currentUser != null && reportDetails != null && !reportDetails.isEmpty()) {
            // Create a new ReportError object
            ReportError report = new ReportError(new Date(), reportDetails, reportType, currentUser.getUser_Id());

            // Insert report into the database
            ReportsDB reportErrorDB = new ReportsDB();
            boolean isSuccess = reportErrorDB.insertReportError(report);

            if (isSuccess) {
                request.setAttribute("message", "Your report has been successfully submitted.");
            } else {
                request.setAttribute("error", "There was an issue submitting your report. Please try again.");
            }
        } else {
            request.setAttribute("error", "You must provide details for the report.");
        }

        // Forward back to the report page
        request.getRequestDispatcher("reporterror.jsp").forward(request, response);
    }
}
