/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author hoang
 */
public class BookingDetails {

    private String tourName;
    private String customerName;
    private int slotOrder;
    private String bookStatus;
    private double totalCost;

    public BookingDetails() {
    }

    public BookingDetails(String tourName, String customerName, int slotOrder, String bookStatus, double totalCost) {
        this.tourName = tourName;
        this.customerName = customerName;
        this.slotOrder = slotOrder;
        this.bookStatus = bookStatus;
        this.totalCost = totalCost;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getSlotOrder() {
        return slotOrder;
    }

    public void setSlotOrder(int slotOrder) {
        this.slotOrder = slotOrder;
    }

    public String getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(String bookStatus) {
        this.bookStatus = bookStatus;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public String toString() {
        return "BookingDetails{" + "tourName=" + tourName + ", customerName=" + customerName + ", slotOrder=" + slotOrder + ", bookStatus=" + bookStatus + ", totalCost=" + totalCost + '}';
    }

    
    
}
