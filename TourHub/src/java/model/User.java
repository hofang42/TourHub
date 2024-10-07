package model;

import java.util.Date;

public class User {

    private int user_Id;
    private String username;
    private String password;
    private String first_Name;
    private String last_Name;
    private String phone;
    private String email;
    private String address;
    private Date created_At;
    private String user_Status;
    private String role;
    private String avatar;

    // Default constructor
    public User() {
    }

    // Parameterized constructor
    public User(int user_Id, String username, String password, String first_Name, String last_Name, String phone, String email, String address, Date created_At, String user_Status, String role) {
        this.user_Id = user_Id;
        this.username = username;
        this.password = password;
        this.first_Name = first_Name;
        this.last_Name = last_Name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.created_At = created_At;
        this.user_Status = user_Status;
        this.role = role;
    }

    public User(int user_Id, String username, String password, String first_Name, String last_Name, String phone, String email, String address) {
        this.user_Id = user_Id;
        this.username = username;
        this.password = password;
        this.first_Name = first_Name;
        this.last_Name = last_Name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public User(int user_Id, String password, String first_Name, String last_Name, String phone, String email, String address, Date created_At, String user_Status, String role, String avatar) {
        this.user_Id = user_Id;
        this.password = password;
        this.first_Name = first_Name;
        this.last_Name = last_Name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.created_At = created_At;
        this.user_Status = user_Status;
        this.role = role;
        this.avatar = avatar;
    }

    public int getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(int userId) {
        this.user_Id = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirst_Name() {
        return first_Name;
    }

    public void setFirst_Name(String first_Name) {
        this.first_Name = first_Name;
    }

    public String getLast_Name() {
        return last_Name;
    }

    public void setLast_Name(String last_Name) {
        this.last_Name = last_Name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreated_At() {
        return created_At;
    }

    public void setCreated_At(Date created_At) {
        this.created_At = created_At;
    }

    public String getUser_Status() {
        return user_Status;
    }

    public void setUser_Status(String user_Status) {
        this.user_Status = user_Status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

 

    @Override
    public String toString() {
        return "User [userId=" + user_Id + ", username=" + username + ", password=" + password
                + ", firstName=" + first_Name + ", lastName=" + last_Name + ", phone=" + phone
                + ", email=" + email + ", address=" + address + ", createdAt=" + created_At
                + ", userStatus=" + user_Status + ", role=" + role + "]";
    }
}