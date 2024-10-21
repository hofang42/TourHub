/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author TRONG DUC
 */
public class ReviewReply {

    private int reply_Id;
    private String reply_Content;
    private Date reply_Date;
    private int review_Id;
    private int user_Id;

    public ReviewReply() {
    }

    public ReviewReply(int reply_Id, String reply_Content, Date reply_Date, int review_Id, int user_Id) {
        this.reply_Id = reply_Id;
        this.reply_Content = reply_Content;
        this.reply_Date = reply_Date;
        this.review_Id = review_Id;
        this.user_Id = user_Id;
    }

    public int getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(int user_Id) {
        this.user_Id = user_Id;
    }

    public int getReply_Id() {
        return reply_Id;
    }

    public void setReply_Id(int reply_Id) {
        this.reply_Id = reply_Id;
    }

    public String getReply_Content() {
        return reply_Content;
    }

    public void setReply_Content(String reply_Content) {
        this.reply_Content = reply_Content;
    }

    public Date getReply_Date() {
        return reply_Date;
    }

    public void setReply_Date(Date reply_Date) {
        this.reply_Date = reply_Date;
    }

    public int getReview_Id() {
        return review_Id;
    }

    public void setReview_Id(int review_Id) {
        this.review_Id = review_Id;
    }

}
