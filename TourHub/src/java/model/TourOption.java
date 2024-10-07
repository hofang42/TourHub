/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author LENOVO
 */
public class TourOption {
    private int optionId;          // From TourOption table
    private String tourId;         // From TourOption table (CHAR(8) so String is used)
    private String optionName;     // From TourOption table
    private double price;          // From TourOption table (option_Price)
    private String description;    // From TourOption table (option_Description)
    private String dayOfWeek;      // From TourSchedule table (day_Of_Week)
    private int availableSlots;    // From TourSchedule table (available_Slots)

    // Constructor for TourOption with schedule information
    public TourOption(int optionId, String tourId, String optionName, double price, String description, 
                      String dayOfWeek, int availableSlots) {
        this.optionId = optionId;
        this.tourId = tourId;
        this.optionName = optionName;
        this.price = price;
        this.description = description;
        this.dayOfWeek = dayOfWeek;
        this.availableSlots = availableSlots;
    }

    // Constructor without schedule information
    public TourOption(int optionId, String tourId, String optionName, double price, String description) {
        this.optionId = optionId;
        this.tourId = tourId;
        this.optionName = optionName;
        this.price = price;
        this.description = description;
    }

    // Getters and Setters
    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    public String getTourId() {
        return tourId;
    }

    public void setTourId(String tourId) {
        this.tourId = tourId;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getAvailableSlots() {
        return availableSlots;
    }

    public void setAvailableSlots(int availableSlots) {
        this.availableSlots = availableSlots;
    }

    @Override
    public String toString() {
        return "TourOption{" + "optionId=" + optionId + ", tourId=" + tourId + ", optionName=" + optionName + ", price=" + price + ", description=" + description + ", dayOfWeek=" + dayOfWeek + ", availableSlots=" + availableSlots + '}';
    }
    
    
}


