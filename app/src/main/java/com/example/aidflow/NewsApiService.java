package com.example.aidflow;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
// Interface for the News API service
public interface NewsApiService {
    // GET request to fetch the latest news
    @GET("v1/search")
    Call<NewsResponse> getLatestNews(
            @Query("apiKey") String apiKey,       // API key for authentication
            @Query("language") String language,   // Language of the news
            @Query("country") String country,     // Country of the news
            @Query("category") String category    // Category of the news
            // @Query("keywords") String keywords // Optional keywords for filtering news
            // @Query("limit") int limit           // Optional limit for the number of results
    );
}
