/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author LENOVO
 */
public class TourOptionDetail {
    private int detailId;
    private int optionId;
    private int categoryId;
    private String detailDescription;

    // Constructor
    public TourOptionDetail(int detailId, int optionId, int categoryId, String detailDescription) {
        this.detailId = detailId;
        this.optionId = optionId;
        this.categoryId = categoryId;
        this.detailDescription = detailDescription;
    }

    // Getters and Setters
    public int getDetailId() {
        return detailId;
    }

    public void setDetailId(int detailId) {
        this.detailId = detailId;
    }

    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getDetailDescription() {
        return detailDescription;
    }

    public void setDetailDescription(String detailDescription) {
        this.detailDescription = detailDescription;
    }

    // toString method for easy display
    @Override
    public String toString() {
        return "TourOptionDetail{" +
                "detailId=" + detailId +
                ", optionId=" + optionId +
                ", categoryId=" + categoryId +
                ", detailDescription='" + detailDescription + '\'' +
                '}';
    }
}
