
import DataAccess.UserDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import model.Discount;
import model.User;

@WebServlet("/discount")
public class DiscountServlet extends HttpServlet {

    private final UserDB userDB = new UserDB();

    @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");

        // Check if the user is Admin or Provider
        if (currentUser != null && ("Admin".equals(currentUser.getRole()) || "Provider".equals(currentUser.getRole()))) {
            String action = request.getParameter("action");

            if (action == null) {
                listDiscounts(request, response);
            } else {
                switch (action) {
                    case "new":
                        showNewForm(request, response);
                        break;
                    case "edit":
                        showEditForm(request, response);
                        break;
                    default:
                        listDiscounts(request, response);
                        break;
                }
            }
        } else {
            // Redirect unauthorized users to an error page
            response.sendRedirect("unauthorized.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");

        // Check if the user is Admin or Provider
        if (currentUser != null && ("Admin".equals(currentUser.getRole()) || "Provider".equals(currentUser.getRole()))) {
            String action = request.getParameter("action");

            switch (action) {
                case "insert":
                    insertDiscount(request, response);
                    break;
                case "update":
                    updateDiscount(request, response);
                    break;
                case "delete":
                    deleteDiscount(request, response);
                    break;
                default:
                    listDiscounts(request, response);
                    break;
            }
        } else {
            // Redirect unauthorized users to an error page
            response.sendRedirect("unauthorized.jsp");
        }
    }

    // Common method to list all discounts
    private void listDiscounts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Discount> listDiscount = userDB.getAllDiscounts();
        request.setAttribute("listDiscount", listDiscount);
        request.getRequestDispatcher("manage-discounts.jsp").forward(request, response);
    }

    // Shows the form to create a new discount
    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("create-discount.jsp").forward(request, response);
    }

    // Shows the form to edit an existing discount
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int discountId = getRequestParameterAsInt(request, "id", response);
        if (discountId == -1) {
            return;
        }
        Discount existingDiscount = userDB.getDiscountById(discountId);
        if (existingDiscount == null) {
            request.setAttribute("error", "Discount not found.");
            listDiscounts(request, response);
        } else {
            request.setAttribute("discount", existingDiscount);
            request.getRequestDispatcher("edit-discount.jsp").forward(request, response);
        }
    }

    // Inserts a new discount into the database
    private void insertDiscount(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            Discount discount = getDiscountFromRequest(request);
            if (userDB.isDiscountCodeExists(discount.getCode())) {
                throw new IllegalArgumentException("Discount code already exists!");
            }
            if (!userDB.isTourIdExists(discount.getTourId())) {
                throw new IllegalArgumentException("Tour ID does not exist!");
            }
            userDB.insertDiscount(discount);
            request.setAttribute("message", "Discount created successfully!");
            listDiscounts(request, response);
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("create-discount.jsp").forward(request, response);
        }
    }

    // Updates an existing discount in the database
    private void updateDiscount(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            int discountId = getRequestParameterAsInt(request, "id", response);
            if (discountId == -1) {
                return;
            }
            Discount discount = getDiscountFromRequest(request);
            discount.setDiscountId(discountId);

            userDB.updateDiscount(discount);
            request.setAttribute("message", "Discount updated successfully!");
            listDiscounts(request, response);
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("create-discount.jsp").forward(request, response);
        }
    }

    // Deletes a discount from the database
    private void deleteDiscount(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int discountId = getRequestParameterAsInt(request, "id", response);
        if (discountId == -1) {
            return;
        }
        userDB.deleteDiscount(discountId);
        request.setAttribute("message", "Discount deleted successfully!");
        listDiscounts(request, response);
    }

    // Helper method to parse and validate date
    private Date parseDate(String dateStr) {
        try {
            return Date.valueOf(dateStr);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid date format. Please use YYYY-MM-DD.");
        }
    }

    // Helper method to parse and validate integers
    private int getRequestParameterAsInt(HttpServletRequest request, String param, HttpServletResponse response) throws IOException {
        try {
            return Integer.parseInt(request.getParameter(param));
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid number format for parameter: " + param);
            return -1;
        }
    }

    // Helper method to parse and validate double
    private double getRequestParameterAsDouble(HttpServletRequest request, String param) {
        try {
            return Double.parseDouble(request.getParameter(param));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format for parameter: " + param);
        }
    }

    // Extract Discount object from request parameters
    private Discount getDiscountFromRequest(HttpServletRequest request) throws IOException {
        String code = request.getParameter("code");
        if (code == null || code.isEmpty()) {
            throw new IllegalArgumentException("Discount code cannot be empty.");
        }

        int quantity = getRequestParameterAsInt(request, "quantity", null);
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }

        double percentDiscount = getRequestParameterAsDouble(request, "percentDiscount");
        if (percentDiscount < 0) {
            throw new IllegalArgumentException("Discount percentage cannot be negative.");
        }
        String require = request.getParameter("require");
        String tourId = request.getParameter("tourId");

        Date startDate = parseDate(request.getParameter("startDate"));
        Date endDate = parseDate(request.getParameter("endDate"));

        return new Discount(0, code, quantity, percentDiscount, startDate, endDate, require, tourId);
    }

}
