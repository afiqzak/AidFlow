package com.example.aidflow;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.WriteBatch;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewsView#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsView extends Fragment {

    // Fragment initialization parameters
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // Parameters
    private String mParam1;
    private String mParam2;

    public NewsView() {
        // Required empty public constructor
    }

    /**
     * Factory method to create a new instance of this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewsView.
     */
    public static NewsView newInstance(String param1, String param2) {
        NewsView fragment = new NewsView();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private RecyclerView recyclerView;
    private NewsAdapter adapter;
    private Context context;
    private static final long CACHE_EXPIRATION_TIME = 24 * 60 * 60 * 1000; // 24 hours
    private static final String NEWS_CACHE_PREF = "news_cache";
    private static final String LAST_FETCH_TIME = "last_fetch_time";
    private static final String CACHED_NEWS = "cached_news";
    private SharedPreferences sharedPreferences;
    private FirebaseFirestore firestore;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_view, container, false);

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.newsList);

        // Set up RecyclerView
        context = getContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);

        // Initialize Firestore and SharedPreferences
        firestore = FirebaseFirestore.getInstance();
        sharedPreferences = context.getSharedPreferences("news_prefs", Context.MODE_PRIVATE);

        // Check and fetch news
        checkAndFetchNews("lifestyle");

        return view;
    }

    // Check cache and fetch news if necessary
    private void checkAndFetchNews(String topic) {
        long lastFetchTime = sharedPreferences.getLong(LAST_FETCH_TIME, 0);

        fetchNewsFromApiAndStoreInFirestore(topic);
        if (System.currentTimeMillis() - lastFetchTime <= CACHE_EXPIRATION_TIME) {
            fetchNewsFromFirestore();
        } else {
            fetchNewsFromApiAndStoreInFirestore(topic);
        }
    }

    // Fetch news from Firestore
    private void fetchNewsFromFirestore() {
        firestore.collection("news")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && task.getResult() != null) {
                        List<News> newsList = new ArrayList<>();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            newsList.add(document.toObject(News.class));
                        }
                        displayNews(newsList);
                    } else {
                        Log.e("NewsView", "Error fetching data from Firestore", task.getException());
                        Toast.makeText(context, "Failed to load news from Firestore", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    // Fetch news from API and store in Firestore
    private void fetchNewsFromApiAndStoreInFirestore(String topic) {
        NewsApiService apiService = RetrofitClient.getRetrofitInstance().create(NewsApiService.class);
        Call<NewsResponse> call = apiService.getLatestNews(
                "uYaOqB5DmWsFUYHD_Hwcuf-P-4C5Go1lAPYeyc8ZtEie9N9r",
                "en",
                "MY",
                topic
        );

        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<News> newsList = response.body().getNews();
                    updateFirestore(newsList);
                    displayNews(newsList);

                    // Update last fetch time
                    sharedPreferences.edit().putLong(LAST_FETCH_TIME, System.currentTimeMillis()).apply();
                } else {
                    Log.e("NewsView", "API Response Failed: " + response.code());
                    Toast.makeText(context, "Failed to fetch news from API", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                Log.e("NewsView", "API Request Failed", t);
            }
        });
    }

    // Update Firestore with new news data
    private void updateFirestore(List<News> newsList) {
        if (newsList == null || newsList.isEmpty()) {
            Log.e("NewsView", "No news found to update Firestore.");
            return;
        }

        WriteBatch batch = firestore.batch();

        firestore.collection("news").get().addOnCompleteListener(task -> {
            if (task.isSuccessful() && task.getResult() != null) {
                // Clear existing documents
                for (QueryDocumentSnapshot document : task.getResult()) {
                    batch.delete(document.getReference());
                }

                // Add new documents
                for (News news : newsList) {
                    Log.d("NewsView", "Adding news: " + news.toString());
                    batch.set(firestore.collection("news").document(), news);
                }

                // Commit the batch write
                batch.commit().addOnSuccessListener(unused -> {
                    Log.d("NewsView", "Firestore updated successfully");
                }).addOnFailureListener(e -> {
                    Log.e("NewsView", "Error updating Firestore", e);
                });
            } else {
                Log.e("NewsView", "Failed to fetch current Firestore documents", task.getException());
            }
        });
    }

    // Display news in RecyclerView
    private void displayNews(List<News> newsList) {
        adapter = new NewsAdapter(context, newsList);
        recyclerView.setAdapter(adapter);
    }
}