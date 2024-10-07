package DataAccess;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import model.User;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Tour;
import model.TourOption;

public class UserDB implements DatabaseInfo {

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

    public boolean registerUser(User user) {
        if (isUsernameExists(user.getUsername()) || isEmailExists(user.getEmail())) {
            return false; // Username or email already exists
        }

        String sql = "INSERT INTO [User] (username, password, firstName, lastName, phone, email, address, createdAt, userStatus, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFirstName());
            ps.setString(4, user.getLastName());
            ps.setString(5, user.getPhone());
            ps.setString(6, user.getEmail());
            ps.setString(7, user.getAddress());
            ps.setTimestamp(8, new java.sql.Timestamp(user.getCreatedAt().getTime()));
            ps.setString(9, user.getUserStatus());
            ps.setString(10, user.getRole());

            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void verifyUser(String email) {
        String sql = "UPDATE [User] SET verified = ? WHERE email = ?";
        try (Connection conn = getConnect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setBoolean(1, true);
            ps.setString(2, email);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User authenticate(String email, String password) {
        String sql = "SELECT * FROM [User] WHERE email = ? AND (password = ? OR password = '')"; // Handle no password for Google users
        try (Connection conn = getConnect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            if (password != null) {
                ps.setString(2, password);
            } else {
                ps.setString(2, ""); // Check for Google users (empty password)
            }
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("userId"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getTimestamp("createdAt"),
                        rs.getString("userStatus"), // Fetch userStatus
                        rs.getString("role") // Fetch role
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isUsernameExists(String username) {
        String sql = "SELECT COUNT(*) FROM [User] WHERE username = ?";
        try (Connection conn = getConnect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return true; // Username exists
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isEmailExists(String email) {
        String sql = "SELECT COUNT(*) FROM [User] WHERE email = ?";
        try (Connection conn = getConnect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return true; // Email exists
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //Kiem tra email co trong database hay la khong
    public boolean checkEmailExists(String email) {
        boolean exists = false;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT email FROM [User] WHERE email = ?";
        try {
            conn = getConnect();
            if (conn != null) {
                ps = conn.prepareStatement(query);
                ps.setString(1, email);
                rs = ps.executeQuery();

                if (rs.next()) {
                    exists = true;  // Email co trong database
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return exists;
    }

    public void updateUserStatusToVerified(String email) {
        String sql = "UPDATE users SET userStatus = 'verified' WHERE email = ?";
        try (Connection con = getConnect(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//-------------------------------------------------
    //Lấy all user ra
    public User getUser(int userId) {
        User user = null;
        // Fixed: Added space after "createdAt"
        String query = "SELECT userId, username, password, userStatus, role, "
                + "firstName, lastName, email, phone, address, createdAt "
                + "FROM [User] WHERE userId = ?";

        try (Connection con = getConnect(); PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt(1);
                String username = rs.getString(2);
                String password = rs.getString(3);
                String userStatus = rs.getString(4);
                String role = rs.getString(5);
                String firstName = rs.getString(6);
                String lastName = rs.getString(7);
                String email = rs.getString(8);
                String phone = rs.getString(9);
                String address = rs.getString(10);
                Date createdAt = rs.getDate(11);

                user = new User(id, username, password, firstName, lastName, phone, email, address, createdAt, userStatus, role);
            }

        } catch (Exception ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    //Thêm user mới
    public void insertUser(User user) {
        String sql = "INSERT INTO [User] (username, password, role, email) VALUES (?, ?, ?, ?)";

        try (Connection con = getConnect(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getRole());
            stmt.setString(5, user.getEmail());
            stmt.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Change password
    public boolean updatePassword(int userId, String newPassword) {
        String query = "UPDATE [User] SET password=? WHERE UserId=?";

        try (Connection con = getConnect(); PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, newPassword);
            stmt.setInt(2, userId);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated == 0) {
                throw new SQLException("Update failed, no rows affected.");
            }
            return true;
        } catch (Exception ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
            return false; // Password update failed
        }
    }

    public boolean updateEmail(int userId, String newEmail) {
        String query = "UPDATE [User] SET email=? WHERE UserId=?";

        try (Connection con = getConnect(); PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, newEmail);
            stmt.setInt(2, userId);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated == 0) {
                throw new SQLException("Update failed, no rows affected.");
            }
            return true;
        } catch (Exception ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
            return false; // Password update failed
        }
    }

    //Edit profile function
    public boolean updateUser(User user) {
        boolean result = false;
        String sql = "UPDATE [User] SET "
                + "firstName =?, lastName=?, email=?, phone=?, address=?, username=?"
                + "from [User] WHERE userId=?";
        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPhone());
            stmt.setString(5, user.getAddress());
            stmt.setString(6, user.getUsername());
            stmt.setInt(7, user.getUserId());

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                result = true;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public User getUserFromSession(HttpSession session, HttpServletRequest request) {
        User user = (User) session.getAttribute("currentUser");
        return user; // or throw an exception if user not found
    }

    public List<Tour> getAll(String sortOrder, String location, int minPrice, int maxPrice) {
        List<Tour> list = new ArrayList<>();
        String sql = "SELECT * FROM Tour WHERE 1=1";

        // Add location filter
        if (location != null && !location.equals("All")) {
            sql += " AND location = ?";
        }

        // Add price range filter
        if (minPrice > 0 || maxPrice > 0) {
            sql += " AND price BETWEEN ? AND ?";
        }

        // Sort the results
        switch (sortOrder) {
            case "price-asc":
                sql += " ORDER BY price ASC";
                break;
            case "price-desc":
                sql += " ORDER BY price DESC";
                break;
            case "rating":
                sql += " ORDER BY average_Review_Rating DESC";
                break;
            case "popularity":
            default:
                sql += " ORDER BY purchases_Time DESC";
                break;
        }

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            int index = 1;

            // Set location filter
            if (location != null && !location.equals("All")) {
                stmt.setString(index++, location);
            }

            // Set price range filter
            if (minPrice > 0 || maxPrice > 0) {
                stmt.setInt(index++, minPrice);
                stmt.setInt(index++, maxPrice);
            }

            // Execute the query and process results
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // Split the image URLs by ";"
                String imageUrlStr = rs.getString("tour_Img");
                List<String> imageUrlList = Arrays.asList(imageUrlStr.split(";"));

                // Create a new Tour object using the data from the result set
                Tour t = new Tour(
                        rs.getString("tour_Id"), // tourId
                        rs.getString("tour_Name"), // tourName
                        rs.getString("tour_Description"), // tourDescription
                        rs.getDate("start_Date"), // startDate
                        rs.getDate("end_Date"), // endDate
                        rs.getString("location"), // location
                        rs.getInt("purchases_Time"), // purchasesTime
                        rs.getBigDecimal("average_Review_Rating"), // averageReviewRating
                        rs.getInt("number_Of_Review"), // numberOfReview
                        rs.getString("total_Time"), // totalTime
                        rs.getBigDecimal("price"), // price
                        rs.getInt("slot"), // slot
                        rs.getString("tour_Status"), // tourStatus
                        rs.getDate("created_At"), // createdAt
                        imageUrlList, // tourImg
                        rs.getInt("company_Id") // companyId
                );
                list.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Tour getTourById(String tourId) {
        Tour tour = null;
        String sql = "SELECT * FROM Tour WHERE tour_Id = ?"; // Corrected column name

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tourId); // Set the tourId parameter

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Split the image URL string by ";" to create a list of URLs
                String imageUrlStr = rs.getString("tour_Img");
                List<String> imageUrlList = Arrays.asList(imageUrlStr.split(";"));

                // Create a new Tour object with the extracted data
                tour = new Tour(
                        rs.getString("tour_Id"), // tourId
                        rs.getString("tour_Name"), // tourName
                        rs.getString("tour_Description"), // tourDescription
                        rs.getDate("start_Date"), // startDate
                        rs.getDate("end_Date"), // endDate
                        rs.getString("location"), // location
                        rs.getInt("purchases_Time"), // purchasesTime
                        rs.getBigDecimal("average_Review_Rating"), // averageReviewRating
                        rs.getInt("number_Of_Review"), // numberOfReview
                        rs.getString("total_Time"), // totalTime
                        rs.getBigDecimal("price"), // price
                        rs.getInt("slot"), // slot
                        rs.getString("tour_Status"), // tourStatus
                        rs.getDate("created_At"), // createdAt
                        imageUrlList, // tourImg
                        rs.getInt("company_Id") // companyId
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tour; // Return the Tour object or null if not found
    }

    public List<TourOption> getTourOptionsByTourId(String tourId) {
        List<TourOption> options = new ArrayList<>();
        String sql = "SELECT tourOpt.option_Id, tourOpt.tour_Id, tourOpt.option_Name, tourOpt.option_Price, tourOpt.option_Description, "
                + "ts.day_Of_Week, ts.available_Slots "
                + "FROM TourOption tourOpt "
                + "LEFT JOIN TourSchedule ts ON tourOpt.option_Id = ts.option_Id "
                + "WHERE tourOpt.tour_Id = ?"; // Use the correct field names

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tourId); // Set the tourId as a parameter (since it's CHAR(8), treat as String)

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // Create a new TourOption object using the result set
                TourOption option = new TourOption(
                        rs.getInt("option_Id"), // optionId from TourOption
                        rs.getString("tour_Id"), // tourId from TourOption
                        rs.getString("option_Name"), // optionName from TourOption
                        rs.getDouble("option_Price"), // price from TourOption
                        rs.getString("option_Description"), // description from TourOption
                        rs.getString("day_Of_Week"), // dayOfWeek from TourSchedule
                        rs.getInt("available_Slots") // availableSlots from TourSchedule
                );

                options.add(option); // Add the option to the list
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return options; // Return the list of TourOptions
    }

    public static void main(String[] args) {
        UserDB userDB = new UserDB();

        // Specify the tourId you want to fetch
        String tourId = "T0000001";  // Example of a tourId (use a real one from your database)

        // Fetch the tour using the getTourById method
        Tour tour = userDB.getTourById(tourId);

        if (tour != null) {
            // Print the tour details
            System.out.println(tour.toString());

            // Fetch and print tour options for the tour
            List<TourOption> options = userDB.getTourOptionsByTourId(tourId);
            for (TourOption option : options) {
                System.out.println(option.toString());
            }
        } else {
            System.out.println("Tour not found for ID: " + tourId);
        }
    }
}
