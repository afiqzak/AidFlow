package com.example.aidflow;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class RetrofitClient {
    // Base URL for the API
    private static final String BASE_URL = "https://api.currentsapi.services/";

    // Retrofit instance
    private static Retrofit retrofit;

    // Method to get the Retrofit instance
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}