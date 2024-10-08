
package model;

import java.util.Date;

public class ReportError {
    private int report_Id;
    private Date report_Date;
    private String report_Details;
    private String report_Type;
    private int user_Id;

    public ReportError() {
    }
    
    // Constructor

    public ReportError(int report_Id, Date report_Date, String report_Details, String report_Type, int user_Id) {
        this.report_Id = report_Id;
        this.report_Date = report_Date;
        this.report_Details = report_Details;
        this.report_Type = report_Type;
        this.user_Id = user_Id;
    }

    public int getReport_Id() {
        return report_Id;
    }

    public void setReport_Id(int report_Id) {
        this.report_Id = report_Id;
    }

    public Date getReport_Date() {
        return report_Date;
    }

    public void setReport_Date(Date report_Date) {
        this.report_Date = report_Date;
    }

    public String getReport_Details() {
        return report_Details;
    }

    public void setReport_Details(String report_Details) {
        this.report_Details = report_Details;
    }

    public String getReport_Type() {
        return report_Type;
    }

    public void setReport_Type(String report_Type) {
        this.report_Type = report_Type;
    }

    public int getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(int user_Id) {
        this.user_Id = user_Id;
    }
   
}
