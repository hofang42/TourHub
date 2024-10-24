/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccess;

import static DataAccess.DatabaseInfo.DBURL;
import static DataAccess.DatabaseInfo.DRIVERNAME;
import static DataAccess.DatabaseInfo.PASSDB;
import static DataAccess.DatabaseInfo.USERDB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Booking;
import model.Comment;
import model.Review;
import model.ReviewReply;
import model.Tour;

/**
 *
 * @author TRONG DUC
 */
public class ReviewDB implements DatabaseInfo {

    public static java.sql.Connection getConnect() {
        try {
            Class.forName(DRIVERNAME);
        } catch (ClassNotFoundException e) {
            System.out.println("Error loading driver" + e);
        }
        try {
            java.sql.Connection con = DriverManager.getConnection(DBURL, USERDB, PASSDB);
            return con;
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return null;
    }

    public static boolean addReview(String comment, int ratingStar, int cusID, String tourId) {
        String sql = "INSERT INTO Review (comment, rating_Star, cus_Id, tour_Id) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnect(); PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, comment);
            statement.setInt(2, ratingStar);
            statement.setInt(3, cusID);
            statement.setString(4, tourId);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean hasCustomerBookedTour(int customerId, String tourId) {
        String sql = "SELECT COUNT(*) FROM Booking WHERE tour_Id = ? AND cus_Id = ?";
        try (Connection conn = getConnect(); PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, tourId);
            statement.setInt(2, customerId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;  // Return true if the count is greater than 0
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Booking> getBookedToursWithoutReview(int userId) {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT book_Id, created_At, slot_Order, total_Cost, tour_Id "
                + "FROM Booking "
                + "WHERE cus_Id = ? AND book_Status = 'Booked' "
                + "AND NOT EXISTS (SELECT 1 FROM Review WHERE tour_Id = Booking.tour_Id AND user_Id = ?)";

        try (Connection conn = getConnect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, userId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Booking booking = new Booking();
                    booking.setBook_Id(rs.getInt("book_Id"));
                    booking.setCreated_At(rs.getDate("created_At"));
                    booking.setSlot_Order(rs.getInt("slot_Order"));
                    booking.setTotal_Cost(rs.getBigDecimal("total_Cost"));
                    booking.setTour_Id(rs.getString("tour_Id"));
                    bookings.add(booking);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    public boolean submitReview(Review review) {
        String sql = "INSERT INTO Review (comment, rating_Star, user_Id, tour_Id) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, review.getComment());
            stmt.setInt(2, review.getRating_Star());
            stmt.setInt(3, review.getUser_Id());
            stmt.setString(4, review.getTour_Id());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0; // Trả về true nếu lưu thành công
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Trả về false nếu có lỗi
        }
    }

    public Tour getTourById(String tourId) {
        Tour tour = null;
        String sql = "SELECT tour_Id, tour_Name FROM Tour WHERE tour_Id = ?";

        try (Connection conn = getConnect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tourId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    tour = new Tour();
                    tour.setTour_Id(rs.getString("tour_Id"));
                    tour.setTour_Name(rs.getString("tour_Name"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tour;
    }

    public String getTourImageUrl(String tourId) {
        String imageUrl = null;
        String sql = "SELECT tour_Img FROM Tour WHERE tour_Id = ?";

        try (Connection conn = getConnect(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, tourId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                imageUrl = rs.getString("tour_Img");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

            return imageUrl != null ? imageUrl : "assests/images/1.jpg";
    }

    public List<Review> getReviewsByTourId(String tourId) {
        List<Review> reviews = new ArrayList<>();

        String sql = "SELECT R.review_Id, R.comment, R.rating_Star, R.user_Id, U.first_Name, U.last_Name, R.tour_Id "
                + "FROM Review R JOIN [User] U ON R.user_Id = U.user_Id WHERE R.tour_Id = ?";

        try (Connection conn = getConnect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tourId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Review review = new Review();
                    review.setReview_Id(rs.getInt("review_Id"));
                    review.setComment(rs.getString("comment"));
                    review.setRating_Star(rs.getInt("rating_Star"));
                    review.setUser_Id(rs.getInt("user_Id"));
                    review.setTour_Id(rs.getString("tour_Id"));
                    review.setFirst_Name(rs.getString("first_Name"));
                    review.setLast_Name(rs.getString("last_Name"));

                    // Lấy danh sách reply cho mỗi review
                    List<ReviewReply> replies = getRepliesForReview(review.getReview_Id());
                    review.setReplies(replies);  // Gán danh sách reply vào review

                    reviews.add(review);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reviews;
    }

    public List<Review> getTop3ReviewsByTourId(String tourId) {
        List<Review> reviews = new ArrayList<>();
        String sql = "SELECT TOP 3 R.review_Id, R.comment, R.rating_Star, R.user_Id, U.first_Name, U.last_Name, R.tour_Id "
                + "FROM Review R JOIN [User] U ON R.user_Id = U.user_Id "
                + "WHERE R.tour_Id = ? "
                + "ORDER BY R.rating_Star DESC";

        try (Connection conn = getConnect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tourId); // Đảm bảo tourId được đặt đúng vào câu lệnh SQL
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Review review = new Review();
                    review.setReview_Id(rs.getInt("review_Id"));
                    review.setComment(rs.getString("comment"));
                    review.setRating_Star(rs.getInt("rating_Star"));
                    review.setUser_Id(rs.getInt("user_Id"));
                    review.setTour_Id(rs.getString("tour_Id"));
                    review.setFirst_Name(rs.getString("first_Name"));
                    review.setLast_Name(rs.getString("last_Name"));
                    reviews.add(review);  // Thêm review vào danh sách
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reviews;
    }

    public boolean addReplyToReview(int reviewId, String replyContent, int userId) {
        String sql = "INSERT INTO ReviewReply (reply_Content, review_Id, user_Id) VALUES (?, ?, ?)";
        try (Connection conn = getConnect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, replyContent);
            ps.setInt(2, reviewId);
            ps.setInt(3, userId);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<ReviewReply> getRepliesForReview(int reviewId) {
        List<ReviewReply> replies = new ArrayList<>();
        String sql = "SELECT reply_Id, reply_Content, reply_Date, review_Id, user_Id FROM ReviewReply WHERE review_Id = ?";

        try (Connection conn = getConnect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, reviewId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ReviewReply reply = new ReviewReply();
                    reply.setReply_Id(rs.getInt("reply_Id"));
                    reply.setReply_Content(rs.getString("reply_Content"));
                    reply.setReply_Date(rs.getDate("reply_Date"));
                    reply.setReview_Id(rs.getInt("review_Id"));
                    reply.setUser_Id(rs.getInt("user_Id"));
                    replies.add(reply);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return replies;
    }

    public boolean checkIfUserIsProviderOfTour(int userId, String tourId) {
        String sql = "SELECT COUNT(*) FROM Tour WHERE company_Id = (SELECT company_Id FROM Company WHERE user_Id = ?) AND tour_Id = ?";
        try (Connection conn = getConnect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setString(2, tourId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Review> getUserReviews(int userId) {
        List<Review> reviews = new ArrayList<>();
        String sql = "SELECT * FROM Review WHERE user_Id = ?";

        try (Connection conn = getConnect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Review review = new Review();
                    review.setReview_Id(rs.getInt("review_Id"));
                    review.setComment(rs.getString("comment"));
                    review.setRating_Star(rs.getInt("rating_Star"));
                    review.setUser_Id(rs.getInt("user_Id"));
                    review.setTour_Id(rs.getString("tour_Id"));
                    reviews.add(review);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reviews;
    }

    public boolean updateReview(Review review) {
        String sql = "UPDATE Review SET comment = ?, rating_Star = ? WHERE review_Id = ?";
        try (Connection conn = getConnect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, review.getComment());
            ps.setInt(2, review.getRating_Star());
            ps.setInt(3, review.getReview_Id());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // Trả về true nếu cập nhật thành công
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Trả về false nếu có lỗi
        }
    }

    public boolean deleteReview(int reviewId) {
        String sql = "DELETE FROM Review WHERE review_Id = ?";
        try (Connection conn = getConnect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, reviewId);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // Return true if a row was deleted
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false if there was an error
        }
    }

    public boolean incrementLikes(int reviewId) {
        String sql = "UPDATE Review SET likes = likes + 1 WHERE review_Id = ?";
        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, reviewId);
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0; // Returns true if a row was updated
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Returns false if there was an error
        }
    }

    public int getLikeCount(int reviewId) {
        String sql = "SELECT likes FROM Review WHERE review_Id = ?";
        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, reviewId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("likes");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // Return 0 if not found or error occurred
    }

    public boolean addLikeToReview(int reviewId) {
        String sql = "UPDATE Review SET likes = likes + 1 WHERE review_Id = ?";
        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, reviewId);
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0; // Return true if the like was successfully added
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false if there was an error
        }
    }

    public boolean addComment(int userId, String tourId, String commentText, Integer parentCommentId) {
        String sql = "INSERT INTO Comment (user_id, tour_id, comment_text, parent_comment_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setString(2, tourId);
            ps.setString(3, commentText);
            if (parentCommentId != null) {
                ps.setInt(4, parentCommentId);  // Nếu là reply thì truyền vào parent_comment_id
            } else {
                ps.setNull(4, java.sql.Types.INTEGER);  // Nếu là comment gốc, set NULL
            }
            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Comment> getCommentsByTourId(String tourId) {
        List<Comment> comments = new ArrayList<>();
        String sql = "SELECT c.comment_id, c.parent_comment_id, u.first_Name, u.last_Name, c.comment_text, c.created_at "
                + "FROM Comment c JOIN [User] u ON c.user_id = u.user_Id "
                + "WHERE c.tour_id = ? "
                + "ORDER BY COALESCE(c.parent_comment_id, c.comment_id), c.created_at";

        try (Connection conn = getConnect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tourId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Comment comment = new Comment();
                    comment.setCommentId(rs.getInt("comment_id"));

                    // Lấy parent_comment_id và kiểm tra NULL
                    int parentCommentId = rs.getInt("parent_comment_id");
                    if (rs.wasNull()) {
                        comment.setParentCommentId(null); // Nếu là NULL thì gán null
                    } else {
                        comment.setParentCommentId(parentCommentId); // Ngược lại thì gán giá trị bình thường
                    }

                    comment.setFirstName(rs.getString("first_Name"));
                    comment.setLastName(rs.getString("last_Name"));
                    comment.setCommentText(rs.getString("comment_text"));
                    comment.setCreatedAt(rs.getTimestamp("created_at"));
                    comments.add(comment);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return comments;
    }

}
