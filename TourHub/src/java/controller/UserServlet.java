package controller;

import DAO.UserDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import model.User;

public class UserServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "login"; // Default action
        }

        switch (action.toLowerCase()) {
            case "login":
                //handleLogin(request, response);
                break;
            case "log out":
                //handleLogout(request, response);
                break;
            case "signup":
                //handleSignUp(request, response);
                break;
            case "db":
                //handleDashBoard(request, response);
                break;
            case "update":
                handleUpdate(request, response);
                break;
            case "booking":
                //handleBooking(request, response);
                break;
            default:
                response.sendRedirect("error.jsp");
                break;
        }
    }

    private void handleUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy thông tin từ form
        int userID = Integer.parseInt(request.getParameter("userID")); // Parse to int
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");

        User user = new User(userID, username, firstName, lastName, email, phone, address);

        UserDB userDB = new UserDB();
        boolean isUpdated = userDB.updateUser(user);

        if (isUpdated) {
            HttpSession session = request.getSession();
            session.setAttribute("user", username);
            session.setAttribute("userDetails", user);
            response.sendRedirect("customer.jsp");
        } else {
            // Xử lý nếu cập nhật thất bại
            response.sendRedirect("updateinfo.jsp?error=UpdateFailed");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
