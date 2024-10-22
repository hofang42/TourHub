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

    private int bookId;
    private String tourName;
    private String customerName;
    private int slotOrder;
    private String bookStatus;
    private double totalCost;
    private Date bookDate;

//    public BookingDetails(int bookId, String tourName, String customerName, int slotOrder, String bookStatus, double totalCost) {
//        this.bookId = bookId;
//        this.tourName = tourName;
//        this.customerName = customerName;
//        this.slotOrder = slotOrder;
//        this.bookStatus = bookStatus;
//        this.totalCost = totalCost;
//    }
    public BookingDetails(int bookId, String tourName, String customerName, int slotOrder, String bookStatus, double totalCost, Date bookDate) {
        this.bookId = bookId;
        this.tourName = tourName;
        this.customerName = customerName;
        this.slotOrder = slotOrder;
        this.bookStatus = bookStatus;
        this.totalCost = totalCost;
        this.bookDate = bookDate;
    }

    public Date getBookDate() {
        return bookDate;
    }

    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }

    public BookingDetails() {
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
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
        return "BookingDetails{" + "bookId=" + bookId + ", tourName=" + tourName + ", customerName=" + customerName + ", slotOrder=" + slotOrder + ", bookStatus=" + bookStatus + ", totalCost=" + totalCost + '}';
    }
}
