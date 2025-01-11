package com.example.aidflow;

import java.sql.Timestamp;

public class WaterReport {
    private String reportID;
    private String address;
    private boolean status;
    private String complaint;
    private boolean rate;

    public WaterReport(String reportID, String address, boolean status, String complaint, boolean rate) {
        this.reportID = reportID;
        this.address = address;
        this.status = status;
        this.complaint = complaint;
        this.rate = rate;
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


}
