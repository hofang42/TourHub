package DataAccess;

import model.FAQ;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FAQDB {

    private Connection getConnection() throws SQLException {
        // Your database connection here
        // Example: DriverManager.getConnection(dbUrl, username, password);
        return DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=yourDB", "user", "password");
    }

    public List<FAQ> getAllFAQs() throws SQLException {
        List<FAQ> faqList = new ArrayList<>();
        String sql = "SELECT * FROM FAQs";

        try (Connection conn = getConnection();
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

        try (Connection conn = getConnection();
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

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, faq.getQuestion());
            pstmt.setString(2, faq.getAnswer());

            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean updateFAQ(FAQ faq) throws SQLException {
        String sql = "UPDATE FAQs SET question = ?, answer = ?, updated_at = GETDATE() WHERE faq_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, faq.getQuestion());
            pstmt.setString(2, faq.getAnswer());
            pstmt.setInt(3, faq.getFaqId());

            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean deleteFAQ(int faqId) throws SQLException {
        String sql = "DELETE FROM FAQs WHERE faq_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, faqId);
            return pstmt.executeUpdate() > 0;
        }
    }
}
