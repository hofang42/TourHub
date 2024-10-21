
import DataAccess.ReviewDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import model.Review;
import model.User;

@WebServlet("/LikeReviewServlet")
public class LikeReviewServlet extends HttpServlet {

    private ReviewDB reviewDB = new ReviewDB();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String reviewIdParam = request.getParameter("reviewId");
        int reviewId = Integer.parseInt(reviewIdParam);

        // Increment likes in the database
        boolean success = reviewDB.incrementLikes(reviewId);

        // Send response back
        response.setContentType("text/plain");
        response.getWriter().write(success ? "Success" : "Failure");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User currentUser = (User) request.getSession().getAttribute("currentUser");

        if (currentUser != null) {
            List<Review> userReviews = reviewDB.getUserReviews(currentUser.getUser_Id());

            // Get like count for each review
            for (Review review : userReviews) {
                int likeCount = reviewDB.getLikeCount(review.getReview_Id());
                review.setLikeCount(likeCount);
            }

            request.setAttribute("userReviews", userReviews);
            request.getRequestDispatcher("myreview.jsp").forward(request, response);
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
