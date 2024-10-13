
import DataAccess.ReviewDB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import model.Review;
import model.User;

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

        // Lấy thông tin currentUser từ session
        HttpSession session = request.getSession(false);
        User currentUser = (User) (session != null ? session.getAttribute("currentUser") : null);

        // Kiểm tra nếu người dùng là Guest
        boolean isGuest = currentUser == null;

        // Mặc định isProviderOfTour là false
        boolean isProviderOfTour = false;
        String userRole = null;

        if (!isGuest) {
            userRole = currentUser.getRole();
            int userId = currentUser.getUser_Id();

            // Nếu người dùng là Provider, kiểm tra xem có phải Provider của tour không
            if ("Provider".equalsIgnoreCase(userRole)) {
                ReviewDB reviewDB = new ReviewDB();
                isProviderOfTour = reviewDB.checkIfUserIsProviderOfTour(userId, tourId);
            }
        }

        // Lấy review từ database
        ReviewDB reviewDB = new ReviewDB();
        List<Review> reviews = reviewDB.getReviewsByTourId(tourId);

        // Đẩy các thuộc tính vào request
        request.setAttribute("reviews", reviews);
        request.setAttribute("isGuest", isGuest);
        request.setAttribute("isProviderOfTour", isProviderOfTour);
        request.setAttribute("userRole", userRole); // Truyền userRole để JSP xác định xem có hiển thị nút reply hay không

        // Chuyển tiếp dữ liệu sang trang JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("/includes/viewreview.jsp");
        dispatcher.include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("doPost() method called.");

        // Lấy thông tin currentUser từ session
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");

        // Kiểm tra nếu currentUser là null, chuyển hướng đến trang đăng nhập
        if (currentUser == null) {
            response.sendRedirect("login.jsp"); // Redirect đến trang login nếu chưa đăng nhập
            return;
        }

        // Lấy userId từ currentUser
        int userId = currentUser.getUser_Id();

        // Lấy giá trị reviewId và tourId từ request
        String reviewIdParam = request.getParameter("reviewId");
        String tourIdParam = request.getParameter("tourId");
        System.out.println("Received reviewId: " + reviewIdParam);
        System.out.println("Received tourId: " + tourIdParam);

        // Kiểm tra nếu reviewId hoặc tourId là null
        if (reviewIdParam == null || tourIdParam == null || reviewIdParam.isEmpty() || tourIdParam.isEmpty()) {
            System.out.println("reviewId hoặc tourId bị null hoặc rỗng.");
            response.sendRedirect("error.jsp"); // Chuyển hướng đến trang lỗi nếu thiếu dữ liệu
            return;
        }

        // Chuyển đổi reviewId từ chuỗi sang số nguyên
        int reviewId;
        try {
            reviewId = Integer.parseInt(reviewIdParam);
        } catch (NumberFormatException e) {
            System.out.println("Lỗi khi chuyển đổi reviewId: " + e.getMessage());
            response.sendRedirect("error.jsp");
            return;
        }

        // Lấy nội dung reply từ request
        String replyContent = request.getParameter("reply_Content");

        // Kiểm tra nếu replyContent không rỗng
        if (replyContent == null || replyContent.trim().isEmpty()) {
            System.out.println("Nội dung reply bị rỗng.");
            response.sendRedirect("error.jsp");
            return;
        }

        // Thêm reply vào database
        ReviewDB reviewDB = new ReviewDB();
        boolean success = reviewDB.addReplyToReview(reviewId, replyContent, userId);

        // Quay lại trang xem review sau khi gửi reply thành công
        if (success) {
            response.sendRedirect("displayTourDetail?id=" + tourIdParam);
        } else {
            response.sendRedirect("error.jsp");
        }
    }

}
