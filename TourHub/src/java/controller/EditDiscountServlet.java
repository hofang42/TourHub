
import DataAccess.DiscountDB;
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
import model.Discount;
import model.User;

@WebServlet("/edit-discount")
public class EditDiscountServlet extends HttpServlet {

    private final DiscountDB discountDB = new DiscountDB();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");

        // Kiểm tra xem người dùng đã đăng nhập và có vai trò là Provider
        if (currentUser != null && "Provider".equals(currentUser.getRole())) {
            // Lấy discountId từ request parameter
            String discountIdParam = request.getParameter("id");
            if (discountIdParam == null || discountIdParam.isEmpty()) {
                session.setAttribute("error", "Discount ID is missing.");
                response.sendRedirect("manage-discounts.jsp");
                return;
            }

            int discountId = Integer.parseInt(discountIdParam);

            // Lấy thông tin mã giảm giá từ cơ sở dữ liệu
            Discount discount = discountDB.getDiscountById(discountId);
            if (discount == null) {
                session.setAttribute("error", "Discount not found.");
                response.sendRedirect("manage-discounts.jsp");
                return;
            }

            // Lấy danh sách tour ID liên quan đến Provider (companyId của user)
            int companyId = 0;
            try {
                companyId = new hoang_UserDB().getProviderIdFromUserId(currentUser.getUser_Id());
            } catch (SQLException ex) {
                Logger.getLogger(ProviderTourServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            List<String> providerTourIds = discountDB.getTourIdsByCompanyId(companyId);

            // Kiểm tra xem danh sách tour ID có rỗng không
            if (providerTourIds.isEmpty()) {
                session.setAttribute("error", "No tours available for this provider.");
                response.sendRedirect("manage-discounts.jsp");
                return;
            }

            // Đặt các dữ liệu vào request attribute để chuyển đến trang JSP
            request.setAttribute("discount", discount);
            request.setAttribute("providerTourIds", providerTourIds);

            // Chuyển đến trang edit-discount.jsp
            request.getRequestDispatcher("edit-discount.jsp").forward(request, response);

        } else {
            // Người dùng không có quyền, chuyển đến trang login
            response.sendRedirect("login.jsp");
        }
    }
}
