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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.ReportError;
import java.sql.*;

/**
 *
 * @author NOMNOM
 */
public class ReportErrorDB implements DatabaseInfo {

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

    //get a ReportError
    public List<ReportError> getAllUserReports(int userId) throws SQLException {
        List<ReportError> reports = new ArrayList<>();
        Connection conn = getConnect(); // Sử dụng phương thức getConnect
        String query = "SELECT report_Id, report_Date, report_Details, report_Type FROM ReportError WHERE user_Id = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setInt(1, userId); // Gán giá trị cho user_Id trong câu lệnh SQL
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            ReportError report = new ReportError();
            report.setReportId(rs.getInt("report_Id"));
            report.setReportDate(rs.getDate("report_Date"));
            report.setReportDetails(rs.getString("report_Details"));
            report.setReportType(rs.getString("report_Type"));
            reports.add(report);
        }

        rs.close();
        pstmt.close(); // Đóng PreparedStatement
        conn.close(); // Đóng kết nối
        return reports;
    }

    //get all ReportError
    public List<ReportError> getAllReports() {
        List<ReportError> reports = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = getConnect(); // Lấy kết nối từ phương thức getConnect()
            if (conn != null) {
                String query = "SELECT report_Id, report_Date, report_Details, report_Type FROM ReportError";
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);

                // Duyệt qua kết quả và tạo các đối tượng ReportError
                while (rs.next()) {
                    ReportError report = new ReportError();
                    report.setReportId(rs.getInt("report_Id"));
                    report.setReportDate(rs.getDate("report_Date"));
                    report.setReportDetails(rs.getString("report_Details"));
                    report.setReportType(rs.getString("report_Type"));
                    reports.add(report); // Thêm đối tượng vào danh sách
                }
            } else {
                System.out.println("Connection is null!");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // In ra lỗi nếu có vấn đề về SQL
        } finally {
            // Đóng các tài nguyên theo thứ tự: ResultSet -> Statement -> Connection
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return reports; // Trả về danh sách ReportError
    }

    //insert a ReportError
    public void addReport(ReportError report, String userId) throws SQLException {
        Connection conn = getConnect();
        String query = "INSERT INTO ReportError (report_Date, report_Details, report_Type) VALUES (?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setDate(1, new java.sql.Date(report.getReportDate().getTime()));
        pstmt.setString(2, report.getReportDetails());
        pstmt.setString(3, report.getReportType());
        pstmt.executeUpdate();
        pstmt.close();
        conn.close();
    }

    //delete a ReportError
    public void deleteReport(int reportId) throws SQLException {
        Connection conn = getConnect();
        String query = "DELETE FROM ReportError WHERE report_Id = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setInt(1, reportId);
        pstmt.executeUpdate();
        pstmt.close();
        conn.close();
    }

    //update a ReportError
    public void updateReport(ReportError report) throws SQLException {
        Connection conn = getConnect();
        String query = "UPDATE ReportError SET report_Date = ?, report_Details = ?, report_Type = ? WHERE report_Id = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setDate(1, new java.sql.Date(report.getReportDate().getTime()));
        pstmt.setString(2, report.getReportDetails());
        pstmt.setString(3, report.getReportType());
        pstmt.setInt(4, report.getReportId());
        pstmt.executeUpdate();
        pstmt.close();
        conn.close();
    }

}
