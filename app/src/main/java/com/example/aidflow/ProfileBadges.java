package com.example.aidflow;

public class ProfileBadges {
    String requirement;
    String condition;
    int img;

    public ProfileBadges(String requirement, String condition, int img) {
        this.requirement = requirement;
        this.condition = condition;
        this.img = img;
    }

    public String getRequirement() {
        return requirement;
    }

    public String getCondition() {
        return condition;
    }

    public int getImg() {
        return img;
    }
}
