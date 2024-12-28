package com.example.aidflow;


public class DonationHistory {
    private final String UserID;
    private final String donatorName;
    private final String donationName;
    private final String projectName;
    private final String transactionDate;
    private final int amount;
    private final String paymentMethod;

    public DonationHistory(String userID, String donatorName, String donationName, String projectName, String transactionDate, int amount, String paymentMethod) {
        UserID = userID;
        this.donatorName = donatorName;
        this.donationName = donationName;
        this.projectName = projectName;
        this.transactionDate = transactionDate;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }


    public String getUserID() { return UserID;}

    public String getDonatorName() {
        return donatorName;
    }

    public String getDonationName() {
        return donationName;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public int getAmount() {
        return amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }



}