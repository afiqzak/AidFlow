package com.example.aidflow;


public class DonationHistory {
    private String donationID;
    private String donationTitle;
    private String category;
    private String PIC;
    private String date;
    private double amount;
    private String paymentMethod;

    // Constructor with parameters
    public DonationHistory(String donationID, String donationTitle, String category, String PIC, String date, int amount, String paymentMethod) {
        this.donationID = donationID;
        this.donationTitle = donationTitle;
        this.category = category;
        this.PIC = PIC;
        this.date = date;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }

    // Default constructor
    public DonationHistory() {}

    // Getter for donationID
    public String getDonationID() {
        return donationID;
    }

    // Getter for donationTitle
    public String getDonationTitle() {
        return donationTitle;
    }

    // Getter for category
    public String getCategory() {
        return category;
    }

    // Getter for PIC
    public String getPIC() {
        return PIC;
    }

    // Getter for date
    public String getDate() {
        return date;
    }

    // Getter for amount
    public double getAmount() {
        return amount;
    }

    // Getter for paymentMethod
    public String getPaymentMethod() {
        return paymentMethod;
    }

    // Setter for date
    public void setDate(String date) {
        this.date = date;
    }

    // Setter for donationTitle
    public void setDonationTitle(String donationTitle) {
        this.donationTitle = donationTitle;
    }

    // Setter for category
    public void setCategory(String category) {
        this.category = category;
    }

    // Setter for PIC
    public void setPIC(String PIC) {
        this.PIC = PIC;
    }
}