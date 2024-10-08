/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author NOMNOM
 */
public class Booking {

    private int book_Id;
    private Date book_Date;
    private int slot_Order;
    private BigDecimal total_Cost;
    private String book_Status;
    private int cus_Id;
    private String tour_Id;
    private String tour_Name;

    public Booking(int book_Id, Date book_Date, int slot_Order, BigDecimal total_Cost, String book_Status, int cus_Id, String tour_Id) {
        this.book_Id = book_Id;
        this.book_Date = book_Date;
        this.slot_Order = slot_Order;
        this.total_Cost = total_Cost;
        this.book_Status = book_Status;
        this.cus_Id = cus_Id;
        this.tour_Id = tour_Id;
    }

    public Booking(int book_Id, Date book_Date, int slot_Order, BigDecimal total_Cost, String book_Status, int cus_Id, String tour_Id, String tour_Name) {
        this.book_Id = book_Id;
        this.book_Date = book_Date;
        this.slot_Order = slot_Order;
        this.total_Cost = total_Cost;
        this.book_Status = book_Status;
        this.cus_Id = cus_Id;
        this.tour_Id = tour_Id;
        this.tour_Name = tour_Name;
    }

    public Booking() {
    }

    public int getBook_Id() {
        return book_Id;
    }

    public void setBook_Id(int book_Id) {
        this.book_Id = book_Id;
    }

    public Date getBook_Date() {
        return book_Date;
    }

    public void setBook_Date(Date book_Date) {
        this.book_Date = book_Date;
    }

    public int getSlot_Order() {
        return slot_Order;
    }

    public void setSlot_Order(int slot_Order) {
        this.slot_Order = slot_Order;
    }

    public BigDecimal getTotal_Cost() {
        return total_Cost;
    }

    public void setTotal_Cost(BigDecimal total_Cost) {
        this.total_Cost = total_Cost;
    }

    public String getBook_Status() {
        return book_Status;
    }

    public void setBook_Status(String book_Status) {
        this.book_Status = book_Status;
    }

    public int getCus_Id() {
        return cus_Id;
    }

    public void setCus_Id(int cus_Id) {
        this.cus_Id = cus_Id;
    }

    public String getTour_Id() {
        return tour_Id;
    }

    public void setTour_Id(String tour_Id) {
        this.tour_Id = tour_Id;
    }

    public String getTour_Name() {
        return tour_Name;
    }

    public void setTour_Name(String tour_Name) {
        this.tour_Name = tour_Name;
    }

}
