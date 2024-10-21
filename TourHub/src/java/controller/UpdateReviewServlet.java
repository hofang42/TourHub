
import DataAccess.ReviewDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.Review;

@WebServlet("/UpdateReview")
public class UpdateReviewServlet extends HttpServlet {

    private ReviewDB reviewDB = new ReviewDB();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int reviewId = Integer.parseInt(request.getParameter("reviewId"));
        String comment = request.getParameter("comment");
        int ratingStar = Integer.parseInt(request.getParameter("ratingStar"));

        Review review = new Review();
        review.setReview_Id(reviewId);
        review.setComment(comment);
        review.setRating_Star(ratingStar);

        boolean isUpdated = reviewDB.updateReview(review); // Phương thức này cần được định nghĩa trong ReviewDB

        if (isUpdated) {
            request.getSession().setAttribute("reviewSuccess", "Review updated successfully!");
        } else {
            request.getSession().setAttribute("reviewError", "Failed to update review.");
        }

        response.sendRedirect("myreview.jsp");
    }
}
