package com.example.aidflow;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    // User attributes
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String imageUrl;
    private double totalDonate;
    private int volunteerHours;
    private int reportSubmitted;

    // Constructor with parameters
    public User(String username, String firstName, String lastName, String email, String phone, String imageUrl,
                double totalDonate, int volunteerHours, int reportSubmitted) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.imageUrl = imageUrl;
        this.totalDonate = totalDonate;
        this.volunteerHours = volunteerHours;
        this.reportSubmitted = reportSubmitted;
    }

    // Default constructor
    public User(){}

    // Getter for username
    public String getUsername() {
        return username;
    }

    // Getter for first name
    public String getFirstName() {
        return firstName;
    }

    // Getter for last name
    public String getLastName() {
        return lastName;
    }

    // Getter for email
    public String getEmail() {
        return email;
    }

    // Getter for phone
    public String getPhone() {
        return phone;
    }

    // Getter for total donations
    public double getTotalDonate() {
        return totalDonate;
    }

    // Getter for volunteer hours
    public int getVolunteerHours() {
        return volunteerHours;
    }

    // Getter for reports submitted
    public int getReportSubmitted() {
        return reportSubmitted;
    }

    // Getter for image URL
    public String getImageUrl() {
        return imageUrl;
    }
}
