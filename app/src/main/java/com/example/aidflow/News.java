package com.example.aidflow;

import com.google.gson.annotations.SerializedName;

public class News {
    // Serialized name for the title field
    @SerializedName("title")
    private String title;

    // Serialized name for the description field
    @SerializedName("description")
    private String description;

    // Serialized name for the image URL field
    @SerializedName("image")
    private String image; // Image URL

    // Serialized name for the URL field
    @SerializedName("url")
    private String url;

    // Serialized name for the author field
    @SerializedName("author")
    private String author;

    // Serialized name for the published date field
    @SerializedName("published")
    private String published;

    // Getter for the title field
    public String getTitle() { return title; }

    // Getter for the description field
    public String getDescription() { return description; }

    // Getter for the image URL field
    public String getImage() { return image; }

    // Getter for the URL field
    public String getUrl() { return url; }

    // Getter for the author field
    public String getAuthor() { return author; }

    // Getter for the published date field
    // Extracts only the date part if the published string contains a time component
    public String getPublished() {
        String published = this.published; // Assuming `this.published` contains the full date string.
        if (published != null && published.contains(" ")) {
            return published.split(" ")[0]; // Extracts only the date part.
        }
        return published;
    }
}