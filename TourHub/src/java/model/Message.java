/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

public class Message {
    private int messageId;       // ID của tin nhắn
    private int senderId;        // ID của người gửi
    private int receiverId;      // ID của người nhận
    private String messageText;   // Nội dung tin nhắn
    private Date messageTime;     // Thời gian gửi

    // Constructor
    public Message(int messageId, int senderId, int receiverId, String messageText, Date messageTime) {
        this.messageId = messageId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.messageText = messageText;
        this.messageTime = messageTime;
    }

    // Constructor
    public Message(int senderId, int receiverId, String messageText) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.messageText = messageText;
        this.messageTime = new Date(); // Default to current time
    }

    // Getters and Setters
    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public Date getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(Date messageTime) {
        this.messageTime = messageTime;
    }

    // Thêm một phương thức để lấy tên người gửi (giả sử bạn sẽ xử lý lấy tên từ database)
    public String getSenderName() {
        // Có thể thực hiện truy vấn để lấy tên người gửi từ userId
        // Đoạn mã giả
        return "User " + senderId; // Thay thế bằng logic thực tế để lấy tên người dùng
    }
}

