/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author TRONG DUC
 */
public class Review {

    private int review_Id;
    private String comment;
    private int rating_Star;
    private int user_Id;
    private String tour_Id;

    public Review() {
    }

    public int getReview_Id() {
        return review_Id;
    }

    public void setReview_Id(int review_Id) {
        this.review_Id = review_Id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating_Star() {
        return rating_Star;
    }

    public void setRating_Star(int rating_Star) {
        this.rating_Star = rating_Star;
    }

    public int getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(int user_Id) {
        this.user_Id = user_Id;
    }

    public String getTour_Id() {
        return tour_Id;
    }

    public void setTour_Id(String tour_Id) {
        this.tour_Id = tour_Id;
    }

    

}
