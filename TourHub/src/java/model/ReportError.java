
package model;

import java.util.Date;

public class ReportError {
    private int reportId;
    private Date reportDate;
    private String reportDetails;
    private String reportType;
    private int userId;

    public ReportError() {
    }
    
    

    // Constructor
    public ReportError(Date reportDate, String reportDetails, String reportType, int userId) {
        this.reportDate = reportDate;
        this.reportDetails = reportDetails;
        this.reportType = reportType;
        this.userId = userId;
    }

    public ReportError(int reportId, Date reportDate, String reportDetails, String reportType) {
        this.reportId = reportId;
        this.reportDate = reportDate;
        this.reportDetails = reportDetails;
        this.reportType = reportType;
    }
    
    

    // Getters and setters
    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public String getReportDetails() {
        return reportDetails;
    }

    public void setReportDetails(String reportDetails) {
        this.reportDetails = reportDetails;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public int getUserId() {
        return userId;
    }

    public void setUser_Id(int user_Id) {
        this.userId = userId;
    }
}
