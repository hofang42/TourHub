package controller;

import DataAccess.ReviewDB;
import DataAccess.UserDB;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import model.Booking;
import model.Review;
import model.Tour;

@WebServlet(name = "SubmitReviewServlet", urlPatterns = {"/SubmitReview"})
public class SubmitReviewServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handle review submission
        String tourId = request.getParameter("tourId");
        int ratingStar = Integer.parseInt(request.getParameter("ratingStar"));
        String comment = request.getParameter("comment");

        // Retrieve user session
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");

        // Check if the user is logged in
        if (currentUser == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Create a review object and store it in the database
        Review review = new Review();
        review.setUser_Id(currentUser.getUser_Id());
        review.setTour_Id(tourId);
        review.setRating_Star(ratingStar);
        review.setComment(comment);

        ReviewDB reviewDB = new ReviewDB();
        boolean isReviewSubmitted = reviewDB.submitReview(review);

        // Handle success or failure of review submission
        if (isReviewSubmitted) {
            session.setAttribute("reviewSuccess", "Thank you for reviewing our tour!");
        } else {
            request.setAttribute("error", "There was an error submitting your review. Please try again.");
        }

        // Redirect to the same page after submission
        response.sendRedirect("SubmitReview");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handle tour review display
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");

        // Check if the user is logged in
        if (currentUser == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Fetch tours that have not been reviewed yet
        ReviewDB reviewDB = new ReviewDB();
        List<Booking> bookedTours = reviewDB.getBookedToursWithoutReview(currentUser.getUser_Id());

        // Fetch related tour data for each booking
        for (Booking booking : bookedTours) {
            Tour tour = reviewDB.getTourById(booking.getTour_Id());

            if (tour != null) {
                booking.setTour_Name(tour.getTour_Name());
                booking.setTour_Img(tour.getTour_Img());
            }
        }

        // Pass the booked tours and any success message to the JSP
        request.setAttribute("bookedTours", bookedTours);

        String reviewSuccess = (String) session.getAttribute("reviewSuccess");
        if (reviewSuccess != null) {
            request.setAttribute("reviewSuccess", reviewSuccess);
            session.removeAttribute("reviewSuccess");
        }

        // Forward the request to the JSP page
        request.getRequestDispatcher("reviewtour.jsp").forward(request, response);
    }
}