package com.example.aidflow;

import com.google.gson.annotations.SerializedName;

public class News {
    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("image")
    private String image; // Image URL

    @SerializedName("url")
    private String url;

    @SerializedName("author")
    private String author;

    @SerializedName("published")
    private String published;

    // Getters
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getImage() { return image; }
    public String getUrl() { return url; }
    public String getAuthor() { return author; }
    public String getPublished() {
        String published = this.published; // Assuming `this.published` contains the full date string.
        if (published != null && published.contains(" ")) {
            return published.split(" ")[0]; // Extracts only the date part.
        }
        return published;
    }
}