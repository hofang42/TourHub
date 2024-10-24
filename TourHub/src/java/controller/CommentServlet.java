
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
import model.Comment;
import model.User;

@WebServlet("/CommentServlet")
public class CommentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Lấy tourId từ URL
        String tourId = request.getParameter("id");

        // Kiểm tra tourId hợp lệ
        if (tourId == null || tourId.isEmpty()) {
            response.sendRedirect("error.jsp");
            return;
        }

        // Lấy danh sách comment từ database
        ReviewDB reviewDB = new ReviewDB();
        List<Comment> comments = reviewDB.getCommentsByTourId(tourId);

        // Đẩy dữ liệu comment và tourId vào request
        request.setAttribute("comments", comments);
        request.setAttribute("tourId", tourId);

        // Chuyển tiếp request đến comment.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("/includes/comment.jsp");
        dispatcher.include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");
        // Kiểm tra xem userId có tồn tại trong session không (nghĩa là người dùng đã đăng nhập)
        if (currentUser == null || currentUser.getUser_Id() == 0) {
            response.getWriter().write("You need to login to comment or reply.");
            return;
        }

        // Lấy thông tin từ request
        String commentText = request.getParameter("commentText");
        String tourId = request.getParameter("tourId");
        String parentCommentIdParam = request.getParameter("parentCommentId");
        Integer parentCommentId = null;
        if (parentCommentIdParam != null && !parentCommentIdParam.isEmpty()) {
            parentCommentId = Integer.parseInt(parentCommentIdParam);
        }

        ReviewDB reviewDB = new ReviewDB();
        boolean success = reviewDB.addComment(currentUser.getUser_Id(), tourId, commentText, parentCommentId);

        if (success) {
            response.getWriter().write("Success");
        } else {
            response.getWriter().write("Failure");
        }
    }
}
