package com.example.aidflow;

public class ProfileBadges {
    // Fields to store badge requirements, conditions, and image resource ID
    String requirement;
    String condition;
    int img;

    // Constructor to initialize the ProfileBadges object
    public ProfileBadges(String requirement, String condition, int img) {
        this.requirement = requirement;
        this.condition = condition;
        this.img = img;
    }

    // Getter method for requirement
    public String getRequirement() {
        return requirement;
    }

    // Getter method for condition
    public String getCondition() {
        return condition;
    }

    // Getter method for image resource ID
    public int getImg() {
        return img;
    }
}