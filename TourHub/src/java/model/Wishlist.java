/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author NOMNOM
 */
public class Wishlist {
    private int wish_Id;
    private int cus_Id;
    private String tour_Id;
    private String tour_Name;

    public Wishlist(int wish_Id, int cus_Id, String tour_Id) {
        this.wish_Id = wish_Id;
        this.cus_Id = cus_Id;
        this.tour_Id = tour_Id;
    }

    public Wishlist(int wish_Id, int cus_Id, String tour_Id, String tour_Name) {
        this.wish_Id = wish_Id;
        this.cus_Id = cus_Id;
        this.tour_Id = tour_Id;
        this.tour_Name = tour_Name;
    }
    
    

    public Wishlist() {
    }

    public int getWish_Id() {
        return wish_Id;
    }

    public void setWish_Id(int wish_Id) {
        this.wish_Id = wish_Id;
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

