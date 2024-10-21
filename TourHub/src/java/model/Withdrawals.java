/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author hoang
 */
public class Withdrawals {

    private int id; // Auto-incrementing primary key
    private int providerId; // Foreign key referencing Company
    private BigDecimal withdrawMoney; // Amount withdrawn
    private Date requestDate; // Date of request
    private Date respondDate; // Date of response
    private String status; // Status of the withdrawal

    // Constructor
    public Withdrawals() {
    }

    public Withdrawals(int id, int providerId, BigDecimal withdrawMoney, Date requestDate, Date respondDate, String status) {
        this.id = id;
        this.providerId = providerId;
        this.withdrawMoney = withdrawMoney;
        this.requestDate = requestDate;
        this.respondDate = respondDate;
        this.status = status;
    }

    public Withdrawals(int providerId, BigDecimal withdrawMoney, String status) {
        this.providerId = providerId;
        this.withdrawMoney = withdrawMoney;
        this.status = status;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }

    public BigDecimal getWithdrawMoney() {
        return withdrawMoney;
    }

    public void setWithdrawMoney(BigDecimal withdrawMoney) {
        this.withdrawMoney = withdrawMoney;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Date getRespondDate() {
        return respondDate;
    }

    public void setRespondDate(Date respondDate) {
        this.respondDate = respondDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Withdrawals{"
                + "id=" + id
                + ", providerId=" + providerId
                + ", withdrawMoney=" + withdrawMoney
                + ", requestDate=" + requestDate
                + ", respondDate=" + respondDate
                + ", status='" + status + '\''
                + '}';
    }
}
