/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author NOMNOM
 */
public class TourVisitCount {
    private int visit_Id;
    private String tour_Id;
    private Date visit_Date;
    private int company_Id;

    public TourVisitCount() {
    }

    public TourVisitCount(int visit_Id, String tour_Id, Date visit_Date, int company_Id) {
        this.visit_Id = visit_Id;
        this.tour_Id = tour_Id;
        this.visit_Date = visit_Date;
        this.company_Id = company_Id;
    }

    public int getVisit_Id() {
        return visit_Id;
    }

    public void setVisit_Id(int visit_Id) {
        this.visit_Id = visit_Id;
    }

    public String getTour_Id() {
        return tour_Id;
    }

    public void setTour_Id(String tour_Id) {
        this.tour_Id = tour_Id;
    }

    public Date getVisit_Date() {
        return visit_Date;
    }

    public void setVisit_Date(Date visit_Date) {
        this.visit_Date = visit_Date;
    }

    public int getCompany_Id() {
        return company_Id;
    }

    public void setCompany_Id(int company_Id) {
        this.company_Id = company_Id;
    }

    
}

