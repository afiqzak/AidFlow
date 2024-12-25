package com.example.aidflow;


public class Donation {
    private final String donationID;
    private final String name;
    private final String projectName;
    private final String description;
    private final String dueDate;
    private final int progress;
    private final String urgency;
    private final String organizationName;
    private final long targetDonationAmount;
    private final long currentDonationAmount;

    //    public Donation(String name, String projectName, String dueDate, int progress, String urgency) {
//        this.name = name;
//        this.projectName = projectName;
//        this.dueDate = dueDate;
//        this.progress = progress;
//        this.urgency = urgency;
//    }
    public Donation(String donationID, String name, String projectName, String description, String dueDate, int progress, String urgency, String organizationName, long targetDonationAmount, long currentDonationAmount) {
        this.donationID = donationID;
        this.name = name;
        this.projectName = projectName;
        this.description = description;
        this.dueDate = dueDate;
        this.progress = progress;
        this.urgency = urgency;
        this.organizationName = organizationName;
        this.targetDonationAmount = targetDonationAmount;
        this.currentDonationAmount = currentDonationAmount;
    }


    public String getDonationID() { return donationID; }

    public String getName() {
        return name;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getDescription() {return description;}

    public String getDueDate() {
        return dueDate;
    }

    public int getProgress() {
        return progress;
    }

    public String getUrgency() {
        return urgency;
    }

    public String getOrganizationName(){ return organizationName;}

    public long getTargetDonationAmount(){ return targetDonationAmount;}

    public long getCurrentDonationAmount(){return currentDonationAmount;}




}

