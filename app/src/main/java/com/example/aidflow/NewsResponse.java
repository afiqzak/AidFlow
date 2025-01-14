package com.example.aidflow;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class NewsResponse {
    // Serialized name for the list of news
    @SerializedName("news")
    private List<News> news;

    // Getter for the list of news
    public List<News> getNews() {
        return news;
    }
}
