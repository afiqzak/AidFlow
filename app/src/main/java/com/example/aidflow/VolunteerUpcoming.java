package com.example.aidflow;

public class VolunteerUpcoming {

    private final String name;
    private final String dueDate;
    private final String address;
    private final String contactNumber;
    private final String organizer;

    public String getName() {
        return name;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getAddress() {
        return address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getOrganizer() {
        return organizer;
    }

    public VolunteerUpcoming(String name, String dueDate, String address, String contactNumber, String organizer) {
        this.name = name;
        this.dueDate = dueDate;
        this.address = address;
        this.contactNumber = contactNumber;
        this.organizer = organizer;
    }


}
