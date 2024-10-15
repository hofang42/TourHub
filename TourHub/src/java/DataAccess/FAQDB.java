package DataAccess;

import static DataAccess.DatabaseInfo.DBURL;
import static DataAccess.DatabaseInfo.DRIVERNAME;
import static DataAccess.DatabaseInfo.PASSDB;
import static DataAccess.DatabaseInfo.USERDB;
import model.FAQ;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FAQDB {

    public static Connection getConnect() {
        try {
            Class.forName(DRIVERNAME);
        } catch (ClassNotFoundException e) {
            System.out.println("Error loading driver: " + e.getMessage());
        }
        try {
            Connection con = DriverManager.getConnection(DBURL, USERDB, PASSDB);
            return con;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    public List<FAQ> getAllFAQs() throws SQLException {
        List<FAQ> faqList = new ArrayList<>();
        String sql = "SELECT * FROM FAQs";

        try (Connection conn = getConnect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                FAQ faq = new FAQ();
                faq.setFaqId(rs.getInt("faq_id"));
                faq.setQuestion(rs.getString("question"));
                faq.setAnswer(rs.getString("answer"));
                faq.setCreatedAt(rs.getTimestamp("created_at"));
                faq.setUpdatedAt(rs.getTimestamp("updated_at"));

                faqList.add(faq);
            }
        }

        return faqList;
    }

    public FAQ getFAQById(int faqId) throws SQLException {
        String sql = "SELECT * FROM FAQs WHERE faq_id = ?";
        FAQ faq = null;

        try (Connection conn = getConnect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, faqId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    faq = new FAQ();
                    faq.setFaqId(rs.getInt("faq_id"));
                    faq.setQuestion(rs.getString("question"));
                    faq.setAnswer(rs.getString("answer"));
                    faq.setCreatedAt(rs.getTimestamp("created_at"));
                    faq.setUpdatedAt(rs.getTimestamp("updated_at"));
                }
            }
        }

        return faq;
    }

    public boolean insertFAQ(FAQ faq) throws SQLException {
        String sql = "INSERT INTO FAQs (question, answer) VALUES (?, ?)";

        try (Connection conn = getConnect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, faq.getQuestion());
            pstmt.setString(2, faq.getAnswer());

            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean updateFAQ(FAQ faq) throws SQLException {
        String sql = "UPDATE FAQs SET question = ?, answer = ?, updated_at = GETDATE() WHERE faq_id = ?";

        try (Connection conn = getConnect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, faq.getQuestion());
            pstmt.setString(2, faq.getAnswer());
            pstmt.setInt(3, faq.getFaqId());

            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean deleteFAQ(int faqId) throws SQLException {
        String sql = "DELETE FROM FAQs WHERE faq_id = ?";

        try (Connection conn = getConnect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, faqId);
            return pstmt.executeUpdate() > 0;
        }
    }
}
