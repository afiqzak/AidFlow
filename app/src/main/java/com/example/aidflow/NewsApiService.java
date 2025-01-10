package com.example.aidflow;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
public interface NewsApiService {
    @GET("v1/search")
    Call<NewsResponse> getLatestNews(
            @Query("apiKey") String apiKey,
            @Query("language") String language,
            @Query("country") String country,
            @Query("category") String category
            //@Query("keywords") String keywords
           // @Query("limit") int limit
    );
}
