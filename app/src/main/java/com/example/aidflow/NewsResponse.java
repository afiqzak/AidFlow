package com.example.aidflow;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class NewsResponse {
    @SerializedName("news")
    private List<News> news;

    public List<News> getNews() {
        return news;
    }

}
