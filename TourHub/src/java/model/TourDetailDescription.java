/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;

/**
 *
 * @author LENOVO
 */
public class TourDetailDescription {
    private int detailId; // Assuming this will be auto-generated
    private String tourId;
    private List<String> experiences;
    private List<String> languageService;
    private List<String> suggestion;
    private String contactNumber;
    private List<String> additionalInformation;
    private List<String> tourItinerary;

    // Constructor
    public TourDetailDescription(String tourId, List<String> experiences, List<String> languageService, List<String> suggestion, 
                                 String contactNumber, List<String> additionalInformation, List<String> tourItinerary) {
        this.tourId = tourId;
        this.experiences = experiences;
        this.languageService = languageService;
        this.suggestion = suggestion;
        this.contactNumber = contactNumber;
        this.additionalInformation = additionalInformation;
        this.tourItinerary = tourItinerary;
    }

    // Getters and Setters
    public int getDetailId() {
        return detailId;
    }

    public void setDetailId(int detailId) {
        this.detailId = detailId;
    }

    public String getTourId() {
        return tourId;
    }

    public void setTourId(String tourId) {
        this.tourId = tourId;
    }

    public List<String> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<String> experiences) {
        this.experiences = experiences;
    }

    public List<String> getLanguageService() {
        return languageService;
    }

    public void setLanguageService(List<String> languageService) {
        this.languageService = languageService;
    }

    public List<String> getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(List<String> suggestion) {
        this.suggestion = suggestion;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public List<String> getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(List<String> additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public List<String> getTourItinerary() {
        return tourItinerary;
    }

    public void setTourItinerary(List<String> tourItinerary) {
        this.tourItinerary = tourItinerary;
    }

    @Override
    public String toString() {
        return "TourDetailDescription{" +
               "detailId=" + detailId +
               ", tourId='" + tourId + '\'' +
               ", experiences=" + experiences +
               ", languageService=" + languageService +
               ", suggestion=" + suggestion +
               ", contactNumber='" + contactNumber + '\'' +
               ", additionalInformation=" + additionalInformation +
               ", tourItinerary=" + tourItinerary +
               '}';
    }
}
