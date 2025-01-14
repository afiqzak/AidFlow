package com.example.aidflow;

public class Donation {
    // Fields for donation details
private String donationID;
private String donationTitle;
private String description;
private String dueDate;
private String category;
private String urgency;
private boolean status;
private double targetAmount;
private double currentAmount;
private String PIC;
private String contactPic;

// Constructor with parameters
public Donation(String donationID, String donationTitle, String description, String dueDate,
                String category, String urgency, boolean status, int targetAmount, int currentAmount, String PIC, String contactPic) {
    this.donationID = donationID;
    this.donationTitle = donationTitle;
    this.description = description;
    this.dueDate = dueDate;
    this.category = category;
    this.urgency = urgency;
    this.status = status;
    this.targetAmount = targetAmount;
    this.currentAmount = currentAmount;
    this.PIC = PIC;
    this.contactPic = contactPic;
}

// Default constructor
public Donation() {}

// Getter methods
public String getDonationID() {
    return donationID;
}

public String getDonationTitle() {
    return donationTitle;
}

public String getDescription() {
    return description;
}

public String getDueDate() {
    return dueDate;
}

public String getCategory() {
    return category;
}

public String getUrgency() {
    return urgency;
}

public boolean isStatus() {
    return status;
}

public double getTargetAmount() {
    return targetAmount;
}

public double getCurrentAmount() {
    return currentAmount;
}

public String getPIC() {
    return PIC;
}

public String getContactPic() {
    return contactPic;
}

// Setter methods
public void setDonationID(String donationID) {
    this.donationID = donationID;
}

public void setDueDate(String dueDate) {
    this.dueDate = dueDate;
}
}



