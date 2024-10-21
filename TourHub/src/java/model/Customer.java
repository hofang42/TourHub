package model;

import java.sql.Date;

public class Customer {
    private int cus_Id;
    private Date cus_Birth;
    private int user_Id;

    // Constructor
    public Customer() {}

    public Customer(int cus_Id, Date cus_Birth, int user_Id) {
        this.cus_Id = cus_Id;
        this.cus_Birth = cus_Birth;
        this.user_Id = user_Id;
    }

    // Getters and Setters
    public int getCus_Id() {
        return cus_Id;
    }

    public void setCus_Id(int cus_Id) {
        this.cus_Id = cus_Id;
    }

    public Date getCus_Birth() {
        return cus_Birth;
    }

    public void setCus_Birth(Date cus_Birth) {
        this.cus_Birth = cus_Birth;
    }

    public int getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(int user_Id) {
        this.user_Id = user_Id;
    }

    // toString method for easy debugging
    @Override
    public String toString() {
        return "Customer{" +
                "cus_Id=" + cus_Id +
                ", cus_Birth=" + cus_Birth +
                ", user_Id=" + user_Id +
                '}';
    }
}
