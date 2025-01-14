package com.example.aidflow;

public class NewsStory {
    // Fields to store user and story details
    private String userID;
    private String username;
    private String description;
    private String userImageUrl;
    private String imageUrl;

    // Constructor to initialize all fields
    public NewsStory(String userID, String username, String description, String userImageUrl, String imageUrl) {
        this.userID = userID;
        this.username = username;
        this.description = description;
        this.userImageUrl = userImageUrl;
        this.imageUrl = imageUrl;
    }

    // Default constructor required for calls to DataSnapshot.getValue(NewsStory.class)
    public NewsStory() {
    }

    // Getter for userID
    public String getUserID() {
        return userID;
    }

    // Getter for username
    public String getUsername() {
        return username;
    }

    // Getter for description
    public String getDescription() {
        return description;
    }

    // Getter for userImageUrl
    public String getUserImageUrl() {
        return userImageUrl;
    }

    // Getter for imageUrl
    public String getImageUrl() {
        return imageUrl;
    }

    // Setter for username
    public void setUsername(String username) {
        this.username = username;
    }

    // Setter for userImageUrl
    public void setImgUrl(String imgUrl) {
        this.userImageUrl = imgUrl;
    }
}
