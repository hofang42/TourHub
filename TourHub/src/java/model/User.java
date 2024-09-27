package model;

import java.util.Date;

public class User {

    private int userId;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String address;
    private Date createdAt;
    private String userStatus;
    private String role;
    private String avatar;

    // Default constructor
    public User() {
    }

    // Parameterized constructor
    public User(int userId, String username, String password, String firstName, String lastName, String phone, String email, String address, Date createdAt, String userStatus, String role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.createdAt = createdAt;
        this.userStatus = userStatus;
        this.role = role;
    }

    public User(int userId, String username, String password, String firstName, String lastName, String phone, String email, String address) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public User(int userId, String password, String firstName, String lastName, String phone, String email, String address, Date createdAt, String userStatus, String role, String avatar) {
        this.userId = userId;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.createdAt = createdAt;
        this.userStatus = userStatus;
        this.role = role;
        this.avatar = avatar;
    }

    // Getters and setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
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
        return "User [userId=" + userId + ", username=" + username + ", password=" + password
                + ", firstName=" + firstName + ", lastName=" + lastName + ", phone=" + phone
                + ", email=" + email + ", address=" + address + ", createdAt=" + createdAt
                + ", userStatus=" + userStatus + ", role=" + role + "]";
    }
}
