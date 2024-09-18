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
public class Tour {

    private int tourId;
    private String tourName;
    private String description;
    private String totalTime;
    private float price;
    private int slot;
    private String tourStatus;
    private int companyId;
    private Date createdAt;
    private String tourImg;

    public Tour() {
    }

    public String getTourImg() {
        return tourImg;
    }

    public void setTourImg(String tourImg) {
        this.tourImg = tourImg;
    }

    public Tour(int tourId, String tourName, String description, String totalTime, float price, int slot, String tourStatus, int companyId, Date createdAt, String tourImg) {
        this.tourId = tourId;
        this.tourName = tourName;
        this.description = description;
        this.totalTime = totalTime;
        this.price = price;
        this.slot = slot;
        this.tourStatus = tourStatus;
        this.companyId = companyId;
        this.createdAt = createdAt;
        this.tourImg = tourImg;
    }

    public int getTourId() {
        return tourId;
    }

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public String getTourStatus() {
        return tourStatus;
    }

    public void setTourStatus(String tourStatus) {
        this.tourStatus = tourStatus;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Tour{" + "tourId=" + tourId + ", tourName=" + tourName + ", description=" + description + ", totalTime=" + totalTime + ", price=" + price + ", slot=" + slot + ", tourStatus=" + tourStatus + ", companyId=" + companyId + ", createdAt=" + createdAt + '}';
    }

}
