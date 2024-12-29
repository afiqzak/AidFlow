package com.example.aidflow;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    //private int img;
    private double totalDonate;
    private int volunteerHours;
    private int reportSubmitted;

    public User(String username, String firstName, String lastName, String email, String phone, double totalDonate, int volunteerHours, int reportSubmitted) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.totalDonate = totalDonate;
        this.volunteerHours = volunteerHours;
        this.reportSubmitted = reportSubmitted;
    }

    public User(){}

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public double getTotalDonate() {
        return totalDonate;
    }

    public int getVolunteerHours() {
        return volunteerHours;
    }

    public int getReportSubmitted() {
        return reportSubmitted;
    }
}
