package servlet;

import DataAccess.ReviewDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/DeleteReview")
public class DeleteReviewServlet extends HttpServlet {
    private final ReviewDB reviewDB = new ReviewDB();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String reviewIdParam = request.getParameter("reviewId");
        if (reviewIdParam != null) {
            int reviewId = Integer.parseInt(reviewIdParam);
            boolean isDeleted = reviewDB.deleteReview(reviewId); // Ensure your ReviewDB has this method

            if (isDeleted) {
                request.getSession().setAttribute("reviewSuccess", "Review deleted successfully.");
            } else {
                request.getSession().setAttribute("reviewError", "Failed to delete the review.");
            }
        } else {
            request.getSession().setAttribute("reviewError", "Review ID is required.");
        }

        response.sendRedirect("myreview.jsp"); // Redirect to the reviews page after deletion
    }
}
