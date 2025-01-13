package com.example.aidflow;

import java.sql.Timestamp;

public class WaterReport {
    private String reportID;
    private String address;
    private boolean status;
    private String complaint;
    private boolean rate;
    private String imageUrl;

    public WaterReport(String reportID, String address, boolean status, String complaint, boolean rate, String imageUrl) {
        this.reportID = reportID;
        this.address = address;
        this.status = status;
        this.complaint = complaint;
        this.rate = rate;
        this.imageUrl = imageUrl;
    }

    public WaterReport() {}

    public String getReportID() {
        return reportID;
    }

    public String getAddress() {
        return address;
    }

    public boolean isStatus() {
        return status;
    }

    public String getComplaint() {
        return complaint;
    }

    public boolean isRate() {
        return rate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setReportID(String reportID) {
        this.reportID = reportID;
    }
}
