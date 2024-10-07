/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;
import java.math.BigDecimal;
import java.sql.Date;
/**
 *
 * @author LENOVO
 */
public class Tour {
    private String tourId;
    private String tourName;
    private String tourDescription;
    private Date startDate;
    private Date endDate;
    private String location;
    private int purchasesTime;
    private BigDecimal averageReviewRating;
    private int numberOfReview;
    private String totalTime;
    private BigDecimal price;
    private int slot;
    private String tourStatus;
    private Date createdAt;
    private List<String> tourImg;
    private int companyId;

    public Tour() {
    }

    public Tour(String tourId, String tourName, String tourDescription, Date startDate, Date endDate, String location, int purchasesTime, BigDecimal averageReviewRating, int numberOfReview, String totalTime, BigDecimal price, int slot, String tourStatus, Date createdAt, List<String> tourImg, int companyId) {
        this.tourId = tourId;
        this.tourName = tourName;
        this.tourDescription = tourDescription;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.purchasesTime = purchasesTime;
        this.averageReviewRating = averageReviewRating;
        this.numberOfReview = numberOfReview;
        this.totalTime = totalTime;
        this.price = price;
        this.slot = slot;
        this.tourStatus = tourStatus;
        this.createdAt = createdAt;
        this.tourImg = tourImg;
        this.companyId = companyId;
    }

    public String getTourId() {
        return tourId;
    }

    public void setTourId(String tourId) {
        this.tourId = tourId;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public String getTourDescription() {
        return tourDescription;
    }

    public void setTourDescription(String tourDescription) {
        this.tourDescription = tourDescription;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getPurchasesTime() {
        return purchasesTime;
    }

    public void setPurchasesTime(int purchasesTime) {
        this.purchasesTime = purchasesTime;
    }

    public BigDecimal getAverageReviewRating() {
        return averageReviewRating;
    }

    public void setAverageReviewRating(BigDecimal averageReviewRating) {
        this.averageReviewRating = averageReviewRating;
    }

    public int getNumberOfReview() {
        return numberOfReview;
    }

    public void setNumberOfReview(int numberOfReview) {
        this.numberOfReview = numberOfReview;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<String> getTourImg() {
        return tourImg;
    }

    public void setTourImg(List<String> tourImg) {
        this.tourImg = tourImg;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return "Tour{" + "tourId=" + tourId + ", tourName=" + tourName + ", tourDescription=" + tourDescription + ", startDate=" + startDate + ", endDate=" + endDate + ", location=" + location + ", purchasesTime=" + purchasesTime + ", averageReviewRating=" + averageReviewRating + ", numberOfReview=" + numberOfReview + ", totalTime=" + totalTime + ", price=" + price + ", slot=" + slot + ", tourStatus=" + tourStatus + ", createdAt=" + createdAt + ", tourImg=" + tourImg + ", companyId=" + companyId + '}';
    }
}
