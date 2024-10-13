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
public class Bill {
    private int bill_Id;
    private double amount;
    private Date bill_Date;
    private String pay_Method;
    private int book_Id;

    public Bill(int bill_Id, double amount, Date bill_Date, String pay_Method, int book_Id) {
        this.bill_Id = bill_Id;
        this.amount = amount;
        this.bill_Date = bill_Date;
        this.pay_Method = pay_Method;
        this.book_Id = book_Id;
    }

    // Getters and Setters

    public int getBill_Id() {
        return bill_Id;
    }

    public void setBill_Id(int bill_Id) {
        this.bill_Id = bill_Id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getBill_Date() {
        return bill_Date;
    }

    public void setBill_Date(Date bill_Date) {
        this.bill_Date = bill_Date;
    }

    public String getPay_Method() {
        return pay_Method;
    }

    public void setPay_Method(String pay_Method) {
        this.pay_Method = pay_Method;
    }

    public int getBook_Id() {
        return book_Id;
    }

    public void setBook_Id(int book_Id) {
        this.book_Id = book_Id;
    }
   
}

