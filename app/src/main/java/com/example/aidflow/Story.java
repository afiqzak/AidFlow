package com.example.aidflow;

public class Story {
    private final String userID;
    private final String username;
    private final String description;
    private final String userImageUrl;
    private final String imageUrl;

    public Story(String userID, String username, String description, String userImageUrl, String imageUrl) {
        this.userID = userID;
        this.username = username;
        this.description = description;
        this.userImageUrl = userImageUrl;
        this.imageUrl = imageUrl;
    }

    public String getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public String getDescription() {
        return description;
    }

    public String getUserImageUrl() {
        return userImageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
