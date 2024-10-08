/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccess;

import static DataAccess.DatabaseInfo.DBURL;
import static DataAccess.DatabaseInfo.DRIVERNAME;
import static DataAccess.DatabaseInfo.PASSDB;
import static DataAccess.DatabaseInfo.USERDB;
import static DataAccess.TourDB.getConnect;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.BookingDetails;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.math.BigDecimal;
import model.Booking;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

/**
 *
 * @author NOMNOM
 */
public class BookingDB implements DatabaseInfo {

    public static Connection getConnect() {
        try {
            Class.forName(DRIVERNAME);
        } catch (ClassNotFoundException e) {
            System.out.println("Error loading driver" + e);
        }
        try {
            Connection con = DriverManager.getConnection(DBURL, USERDB, PASSDB);
            return con;
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return null;
    }

    public List<Booking> getUserBooking(int customer_Id) {
        List<Booking> list = new ArrayList<>();

        try (Connection con = getConnect()) {
            PreparedStatement stmt = con.prepareStatement("SELECT book_Id, book_Date,"
                    + "slot_Order, total_Cost, book_Status, cus_Id, tour_Id FROM Booking where cus_Id = ?");
            stmt.setInt(1, customer_Id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Booking(rs.getInt(1), rs.getDate(2), rs.getInt(3), rs.getBigDecimal(4), rs.getString(5), rs.getInt(6), rs.getString(7)));
            }
            con.close();
            return list;
        } catch (Exception ex) {
            Logger.getLogger(BookingDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public List<Booking> getUser2Booking(int customer_Id) {
        List<Booking> list = new ArrayList<>();

        // Sử dụng try-with-resources để tự động đóng kết nối và tài nguyên
        String query = "SELECT book_Id, book_Date, slot_Order, total_Cost, book_Status, Tour.tour_Id, Tour.tour_Name "
                + "FROM Booking "
                + "INNER JOIN Tour ON Booking.tour_Id = Tour.tour_Id "
                + "INNER JOIN [User] ON Booking.cus_Id = [User].user_Id "
                + "WHERE Booking.cus_Id = ?";

        try (Connection con = getConnect(); PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setInt(1, customer_Id); // Đặt tham số vào PreparedStatement
            ResultSet rs = stmt.executeQuery(); // Thực thi truy vấn

            while (rs.next()) {
                // Tạo một đối tượng Booking từ dữ liệu trong ResultSet
                Booking booking = new Booking(
                        rs.getInt("book_Id"),
                        rs.getDate("book_Date"),
                        rs.getInt("slot_Order"),
                        rs.getBigDecimal("total_Cost"),
                        rs.getString("book_Status"),
                        customer_Id, // Sử dụng customer_Id từ tham số đầu vào
                        rs.getString("tour_Id"),
                        rs.getString("tour_Name") // Chắc chắn rằng lớp Booking có trường tour_Name
                );
                list.add(booking);
            }
        } catch (Exception ex) {
            // Ghi lại lỗi nếu có
            Logger.getLogger(BookingDB.class.getName()).log(Level.SEVERE, "Error retrieving user bookings", ex);
        }

        return list; // Trả về danh sách booking, nếu không có booking thì danh sách sẽ rỗng
    }

    public List<Booking> getAllBookings() {
        List<Booking> list = new ArrayList<>();

        try (Connection con = getConnect()) {
            PreparedStatement stmt = con.prepareStatement("SELECT book_Id, book_Date,"
                    + "slot_Order, total_Cost, book_Status, cus_Id, tour_Id FROM Booking");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Booking(rs.getInt(1), rs.getDate(2), rs.getInt(3), rs.getBigDecimal(4), rs.getString(5), rs.getInt(6), rs.getString(7)));
            }
            con.close();
            return list;
        } catch (Exception ex) {
            Logger.getLogger(BookingDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    //----------------------------------------------------------
    //Số lượng đặt tour: Tổng số tour đã được đặt -- có thể thêm trong một khoảng thời gian cụ thể (ngày, tháng, năm).
    public int getTotalBookings() throws SQLException {
        try (Connection con = getConnect()) {
            String sql = "SELECT COUNT(*) FROM Booking";
            try (PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
            return 0;
        } catch (Exception ex) {
            Logger.getLogger(BookingDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;

    }

    //Doanh thu: Tổng doanh thu từ các tour đã đặt.
    public BigDecimal getTotalRevenue() throws SQLException {
        try (Connection con = getConnect()) {
            String sql = "SELECT SUM(total_Cost) FROM Booking WHERE book_Status = 'Confirmed'";
            try (PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getBigDecimal(1) != null ? rs.getBigDecimal(1) : BigDecimal.ZERO;
                }
            }
            return BigDecimal.ZERO;
        } catch (Exception ex) {
            Logger.getLogger(BookingDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    //Số lượng khách hàng mới: Số lượng khách hàng đã đăng ký tài khoản trong khoảng thời gian.
    public int getNewCustomersCount(Connection conn, Date startDate, Date endDate, String role) throws SQLException {
        role = "customer";
        String sql = "SELECT COUNT(*) FROM [User] WHERE created_At BETWEEN ? AND ? AND Role = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, new java.sql.Date(startDate.getTime()));
            stmt.setDate(2, new java.sql.Date(endDate.getTime()));
            stmt.setString(3, role);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return 0;
    }

    //Tỷ lệ hủy tour: Tỷ lệ phần trăm các tour bị hủy so với tổng số tour đã đặt.
    public double getCancellationRate() throws SQLException {
        try (Connection con = getConnect()) {
            String sql = "SELECT COUNT(*) FROM Booking WHERE book_Status = 'Cancelled'";
            String sqlTotal = "SELECT COUNT(*) FROM Booking";
            int cancelledCount = 0;
            int totalCount = 0;

            try (PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cancelledCount = rs.getInt(1);
                }
            }

            try (PreparedStatement stmtTotal = con.prepareStatement(sqlTotal); ResultSet rsTotal = stmtTotal.executeQuery()) {
                if (rsTotal.next()) {
                    totalCount = rsTotal.getInt(1);
                }
            }

            return totalCount > 0 ? (double) cancelledCount / totalCount * 100 : 0;
        } catch (Exception ex) {
            Logger.getLogger(BookingDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    //Đánh giá và phản hồi: Điểm trung bình đánh giá từ khách hàng về các tour. Chưa có review = null
    public double getAverageReviewRating(Connection conn) throws SQLException {
        String sql = "SELECT AVG(rating_Star) FROM Review";
        try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getDouble(1);
            }
        }
        return 0;
    }

    //Số lượng tour được xem: Số lần khách hàng xem các tour trên website. Chưa có biến view trong database, sẽ thêm
    public int getTourViewsCount(Connection conn) throws SQLException {
        // Giả sử bạn có một bảng lưu trữ số lần xem tour
        String sql = "SELECT SUM(views) FROM Tour"; // Bạn cần tạo cột views trong bảng Tour
        try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

    //Số lượng đặt tour theo loại: Phân loại theo các loại tour (du lịch biển, du lịch núi, văn hóa, ...).// chưa thêm data type 
    public Map<String, Integer> getBookingsCountByType(Connection conn) throws SQLException {
        String sql = "SELECT tour_Id, COUNT(*) FROM Booking GROUP BY tour_Id";
        Map<String, Integer> bookingsByType = new HashMap<>();

        try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                bookingsByType.put(rs.getString("tour_Id"), rs.getInt(2));
            }
        }
        return bookingsByType;
    }

    //Thống kê theo vùng miền: Số lượng đặt tour theo từng thành phố hoặc vùng miền.
    public Map<String, Integer> getBookingsByLocation() throws SQLException {
        try (Connection con = getConnect()) {
            String sql = "SELECT location, COUNT(*) FROM Tour t INNER JOIN Booking b ON t.tour_Id = b.tour_Id GROUP BY location";
            Map<String, Integer> bookingsByLocation = new HashMap<>();

            try (PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    bookingsByLocation.put(rs.getString("location"), rs.getInt(2));
                }
            }
            return bookingsByLocation;
        } catch (Exception ex) {
            Logger.getLogger(BookingDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    //Liệt kê số lượng đặt tour theo từng tháng
    public Map<String, Integer> getMonthlyBookingsCount() throws SQLException {
        try (Connection con = getConnect()) {
            String sql = "SELECT MONTH(book_Date) AS month, COUNT(*) AS booking_count "
                    + "FROM Booking "
                    + "GROUP BY MONTH(book_Date)";
            Map<String, Integer> bookingsByMonth = new HashMap<>();

            try (PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String month = String.valueOf(rs.getInt("month"));
                    int bookingCount = rs.getInt("booking_count");
                    bookingsByMonth.put(month, bookingCount);
                }
            }
            return bookingsByMonth;
        } catch (Exception ex) {
            Logger.getLogger(BookingDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    //Liệt kê các user vừa đăng ký gần đây nhất
    public List<User> getRecentUsers() throws SQLException {
        try (Connection con = getConnect()) {
            String sql = "SELECT TOP 10 user_Id, first_Name, last_Name, email, created_At, role "
                    + "FROM [User] WHERE role = 'customer' OR role = 'company' "
                    + "ORDER BY created_At DESC "; // Giới hạn lấy 10 user gần đây nhất
            List<User> recentUsers = new ArrayList<>();

            try (PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    User user = new User();
                    user.setUser_Id(rs.getInt("user_Id"));
                    user.setFirst_Name(rs.getString("first_Name"));
                    user.setLast_Name(rs.getString("last_Name"));
                    user.setEmail(rs.getString("email"));
                    user.setCreated_At(rs.getDate("created_At"));
                    user.setRole(rs.getString("role"));
                    recentUsers.add(user);
                }
            }
            return recentUsers;
        } catch (Exception ex) {
            Logger.getLogger(BookingDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
