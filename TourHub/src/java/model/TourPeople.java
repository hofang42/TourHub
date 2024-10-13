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
public class TourPeople {
    private int peopleId;
    private int optionId;
    private String peopleType;
    private int minCount;
    private int maxCount;
    private BigDecimal price;
    private String description;

    // Constructor
    public TourPeople(int peopleId, int optionId, String peopleType, int minCount, int maxCount, BigDecimal price, String description) {
        this.peopleId = peopleId;
        this.optionId = optionId;
        this.peopleType = peopleType;
        this.minCount = minCount;
        this.maxCount = maxCount;
        this.price = price;
        this.description = description;
    }

    public int getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(int peopleId) {
        this.peopleId = peopleId;
    }

    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    public String getPeopleType() {
        return peopleType;
    }

    public void setPeopleType(String peopleType) {
        this.peopleType = peopleType;
    }

    public int getMinCount() {
        return minCount;
    }

    public void setMinCount(int minCount) {
        this.minCount = minCount;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
}
