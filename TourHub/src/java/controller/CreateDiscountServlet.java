
import DataAccess.DiscountDB;
import DataAccess.UserDB;
import DataAccess.hoang_UserDB;
import controller.ProviderTourServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

@WebServlet("/create-discount")
public class CreateDiscountServlet extends HttpServlet {

    private final DiscountDB tourDB = new DiscountDB();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");

        // Kiểm tra người dùng đã đăng nhập và có vai trò là Provider
        if (currentUser != null && "Provider".equals(currentUser.getRole())) {
            int companyId = 0;
            try {
                companyId = new hoang_UserDB().getProviderIdFromUserId(currentUser.getUser_Id());
            } catch (SQLException ex) {
                Logger.getLogger(ProviderTourServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            // Lấy danh sách tour ID từ TourDB
            List<String> providerTourIds = tourDB.getTourIdsByCompanyId(companyId);

            // Kiểm tra xem danh sách có trống không
            if (providerTourIds.isEmpty()) {
                System.out.println("Không có tour nào cho companyId: " + companyId);
            } else {
                // Đặt danh sách tour ID vào request attribute
                request.setAttribute("providerTourIds", providerTourIds);
            }

            // Chuyển đến JSP
            request.getRequestDispatcher("create-discount.jsp").forward(request, response);
        } else {
            response.sendRedirect("login.jsp");
        }
    }

}
