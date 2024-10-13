/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.math.BigDecimal;

/**
 *
 * @author LENOVO
 */
public class TourOption {
    private int option_Id;          // From TourOption table
    private String tour_Id;         // From TourOption table (CHAR(8) so String is used)
    private String option_Name;     // From TourOption table
    private BigDecimal option_Price;          // From TourOption table (option_Price)
    private String option_Description;    // From TourOption table (option_Description)
    private String day_Of_Week;      // From TourSchedule table (day_Of_Week)
    private int available_Slots;    // From TourSchedule table (available_Slots)

    public TourOption() {
    }

    public TourOption(int option_Id, String tour_Id, String option_Name, BigDecimal option_Price, String option_Description, String day_Of_Week, int available_Slots) {
        this.option_Id = option_Id;
        this.tour_Id = tour_Id;
        this.option_Name = option_Name;
        this.option_Price = option_Price;
        this.option_Description = option_Description;
        this.day_Of_Week = day_Of_Week;
        this.available_Slots = available_Slots;
    }

    public int getOption_Id() {
        return option_Id;
    }

    public void setOption_Id(int option_Id) {
        this.option_Id = option_Id;
    }

    public String getTour_Id() {
        return tour_Id;
    }

    public void setTour_Id(String tour_Id) {
        this.tour_Id = tour_Id;
    }

    public String getOption_Name() {
        return option_Name;
    }

    public void setOption_Name(String option_Name) {
        this.option_Name = option_Name;
    }

    public BigDecimal getOption_Price() {
        return option_Price;
    }

    public void setOption_Price(BigDecimal option_Price) {
        this.option_Price = option_Price;
    }

    public String getOption_Description() {
        return option_Description;
    }

    public void setOption_Description(String option_Description) {
        this.option_Description = option_Description;
    }

    public String getDay_Of_Week() {
        return day_Of_Week;
    }

    public void setDay_Of_Week(String day_Of_Week) {
        this.day_Of_Week = day_Of_Week;
    }

    public int getAvailable_Slots() {
        return available_Slots;
    }

    public void setAvailable_Slots(int available_Slots) {
        this.available_Slots = available_Slots;
    }

    
    
    
}


