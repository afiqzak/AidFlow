package com.example.aidflow;

public class NewsStory {
    private String userID;
    private String username;
    private String description;
    private String userImageUrl;
    private String imageUrl;

    public NewsStory(String userID, String username, String description, String userImageUrl, String imageUrl) {
        this.userID = userID;
        this.username = username;
        this.description = description;
        this.userImageUrl = userImageUrl;
        this.imageUrl = imageUrl;
    }

    public NewsStory() {
        // Default constructor required for calls to DataSnapshot.getValue(NewsStory.class)
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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setImgUrl(String imgUrl) {
        this.userImageUrl = imgUrl;
    }
}
