
import DataAccess.ReviewDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/GetLikeCountServlet")
public class GetLikeCountServlet extends HttpServlet {

    private ReviewDB reviewDB = new ReviewDB();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String reviewIdParam = request.getParameter("reviewId");
        int reviewId = Integer.parseInt(reviewIdParam);

        // Get the current like count from the database
        int likeCount = reviewDB.getLikeCount(reviewId);

        // Send the like count back as a response
        response.setContentType("text/plain");
        response.getWriter().write(String.valueOf(likeCount));
    }
}
