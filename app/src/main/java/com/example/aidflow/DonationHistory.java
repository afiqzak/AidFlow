package com.example.aidflow;


public class DonationHistory {
    private String donationID;
    private String donationTitle;
    private String category;
    private String PIC;
    private String date;
    private double amount;
    private String paymentMethod;

    public DonationHistory(String donationID, String donationTitle, String category, String PIC, String date, int amount, String paymentMethod) {
        this.donationID = donationID;
        this.donationTitle = donationTitle;
        this.category = category;
        this.PIC = PIC;
        this.date = date;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }

    public DonationHistory() {}

    public String getDonationID() {
        return donationID;
    }

    public String getDonationTitle() {
        return donationTitle;
    }

    public String getCategory() {
        return category;
    }

    public String getPIC() {
        return PIC;
    }

    public String getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDonationTitle(String donationTitle) {
        this.donationTitle = donationTitle;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPIC(String PIC) {
        this.PIC = PIC;
    }
}