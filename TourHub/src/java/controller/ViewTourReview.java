
import DataAccess.ReviewDB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import model.Review;


@WebServlet("/ViewTourReview")
public class ViewTourReview extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String tourId = request.getParameter("id");

        // Kiểm tra tourId hợp lệ
        if (tourId == null || tourId.isEmpty()) {
            response.sendRedirect("error.jsp");
            return;
        }

        // Lấy review từ database
        ReviewDB reviewDB = new ReviewDB();
        List<Review> reviews = reviewDB.getReviewsByTourId(tourId);

        // Đẩy danh sách review vào request attribute
        request.setAttribute("reviews", reviews);

        // Chuyển tiếp dữ liệu sang trang JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("/includes/viewreview.jsp");
        dispatcher.include(request, response);
    }
}
