package com.example.aidflow;

public class Volunteer {

    // Volunteer attributes
    private String volunteerID;
    private String eventTitle;
    private String location;
    private String eventDate;
    private int numOfHours;
    private String state;
    private String region;
    private String description;
    private String PIC; // Person In Charge
    private String contactPIC;
    private int numOfVolunteersNeeded;
    private int numOfVolunteersApplied;
    private boolean status;

    // Default constructor
    public Volunteer() {
    }

    // Constructor with parameters
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

    // Getter for volunteer ID
    public String getVolunteerID() {
        return volunteerID;
    }

    // Getter for Person In Charge
    public String getPIC() {
        return PIC;
    }

    // Getter for contact of Person In Charge
    public String getContactPIC() {
        return contactPIC;
    }

    // Getter for event title
    public String getEventTitle() {
        return eventTitle;
    }

    // Getter for location
    public String getLocation() {
        return location;
    }

    // Getter for event date
    public String getEventDate() {
        return eventDate;
    }

    // Getter for number of hours
    public int getNumOfHours() {
        return numOfHours;
    }

    // Getter for state
    public String getState() {
        return state;
    }

    // Getter for region
    public String getRegion() {
        return region;
    }

    // Getter for description
    public String getDescription() {
        return description;
    }

    // Getter for number of volunteers needed
    public int getNumOfVolunteersNeeded() {
        return numOfVolunteersNeeded;
    }

    // Getter for number of volunteers applied
    public int getNumOfVolunteersApplied() {
        return numOfVolunteersApplied;
    }

    // Getter for status
    public boolean isStatus() {
        return status;
    }

    // Setter for event date
    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    // Setter for volunteer ID
    public void setVolunteerID(String volunteerID){
        this.volunteerID = volunteerID;
    }
}
