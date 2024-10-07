package controller;

import DataAccess.UserDB;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import model.Review;

@WebServlet(name = "SubmitReviewServlet", urlPatterns = {"/SubmitReview"})
public class SubmitReviewServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy thông tin từ form
        String tourId = request.getParameter("tourId");
        int ratingStar = Integer.parseInt(request.getParameter("ratingStar"));
        String comment = request.getParameter("comment");

        // Lấy thông tin người dùng từ session
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");

        // Kiểm tra xem người dùng đã đăng nhập chưa
        if (currentUser == null) {
            // Nếu chưa đăng nhập, chuyển hướng về trang đăng nhập
            response.sendRedirect("login.jsp");
            return;
        }

        // Tạo đối tượng review và lưu vào database
        Review review = new Review();
        review.setUser_Id(currentUser.getUserId());
        review.setTour_Id(tourId);
        review.setRating_Star(ratingStar);
        review.setComment(comment);

        UserDB userDB = new UserDB();
        boolean isReviewSubmitted = userDB.submitReview(review);

        if (isReviewSubmitted) {
            // Nếu đánh giá được lưu thành công, thêm thông báo vào session
            session.setAttribute("reviewSuccess", "Thank you for reviewing our tour!");

            // Chuyển hướng lại trang chi tiết tour
            response.sendRedirect("reviewtour.jsp?tourId=" + tourId);
        } else {
            // Nếu có lỗi khi lưu đánh giá, hiện thông báo lỗi
            request.setAttribute("error", "There's an error, please try again!");
            request.getRequestDispatcher("reviewtour.jsp").forward(request, response);
        }
    }
}
