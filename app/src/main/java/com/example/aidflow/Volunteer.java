package com.example.aidflow;

public class Volunteer {

    private String volunteerID;
    private String eventTitle;
    private String location;
    private String eventDate;
    private int numOfHours;
    private String state;
    private String region;
    private String description;
    private String PIC;
    private String contactPIC;
    private int numOfVolunteersNeeded;
    private int numOfVolunteersApplied;
    private boolean status;

    public Volunteer() {
    }

    public Volunteer(String volunteerID, String eventTitle, String location, String eventDate, int numOfHours, String state, String region,
                     String description, String PIC, String contactPIC, int numOfVolunteersNeeded, int numOfVolunteersApplied, boolean status) {
        this.volunteerID = volunteerID;
        this.eventTitle = eventTitle;
        this.location = location;
        this.eventDate = eventDate;
        this.numOfHours = numOfHours;
        this.state = state;
        this.region = region;
        this.description = description;
        this.PIC = PIC;
        this.contactPIC = contactPIC;
        this.numOfVolunteersNeeded = numOfVolunteersNeeded;
        this.numOfVolunteersApplied = numOfVolunteersApplied;
        this.status = status;
    }

    public String getVolunteerID() {
        return volunteerID;
    }

    public String getPIC() {
        return PIC;
    }

    public String getContactPIC() {
        return contactPIC;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public String getLocation() {
        return location;
    }

    public String getEventDate() {
        return eventDate;
    }

    public int getNumOfHours() {
        return numOfHours;
    }

    public String getState() {
        return state;
    }

    public String getRegion() {
        return region;
    }

    public String getDescription() {
        return description;
    }

    public int getNumOfVolunteersNeeded() {
        return numOfVolunteersNeeded;
    }

    public int getNumOfVolunteersApplied() {
        return numOfVolunteersApplied;
    }

    public boolean isStatus() {
        return status;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public void setVolunteerID(String volunteerID){
        this.volunteerID = volunteerID;
    }
}
