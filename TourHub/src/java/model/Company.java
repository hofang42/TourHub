/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author hoang
 */
public class Company {

    // Fields corresponding to the columns in the Company table
    private int companyId;
    private String taxCode;
    private double balance;
    private String bankInformation;
    private int user_Id;

    // Default constructor
    public Company() {
    }

    // Parameterized constructor
    public Company(int companyId, String taxCode, double balance, String bankInformation, int userId) {
        this.companyId = companyId;
        this.taxCode = taxCode;
        this.balance = balance;
        this.bankInformation = bankInformation;
        this.user_Id = user_Id;
    }

    // Getters and Setters
    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getBankInformation() {
        return bankInformation;
    }

    public void setBankInformation(String bankInformation) {
        this.bankInformation = bankInformation;
    }

    public int getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(int user_Id) {
        this.user_Id = user_Id;
    }

    @Override
    public String toString() {
        return "Company{"
                + "companyId=" + companyId
                + ", taxCode='" + taxCode + '\''
                + ", balance=" + balance
                + ", bankInformation='" + bankInformation + '\''
                + ", user_Id=" + user_Id
                + '}';
    }
}
