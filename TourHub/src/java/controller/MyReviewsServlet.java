package Controller;

import DataAccess.ReviewDB;
import model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import model.Review;

@WebServlet("/myreviews")
public class MyReviewsServlet extends HttpServlet {

    private ReviewDB reviewDB = new ReviewDB();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the current user from the session
        User currentUser = (User) request.getSession().getAttribute("currentUser");

        // Check if the user is logged in
        if (currentUser == null) {
            // If not logged in, redirect to the login page
            response.sendRedirect("login.jsp");
            return; // Stop further processing
        }

        // Proceed to get user reviews if the user is logged in
        List<Review> userReviews = reviewDB.getUserReviews(currentUser.getUser_Id());
        request.setAttribute("userReviews", userReviews);

        // Forward to the myreview.jsp page
        request.getRequestDispatcher("myreview.jsp").forward(request, response);
    }
}
