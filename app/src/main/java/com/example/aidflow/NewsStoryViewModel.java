package com.example.aidflow;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class NewsStoryViewModel extends ViewModel {
    // LiveData to hold the list of news stories
    private MutableLiveData<List<NewsStory>> storyList = new MutableLiveData<>();
    // LiveData to hold the selected news story
    private MutableLiveData<NewsStory> selectedStory = new MutableLiveData<>();
    // Cache to store accessed user details
    private Map<String, User> accessedUsers = new HashMap<>();

    // Getter for the story list LiveData
    public MutableLiveData<List<NewsStory>> getStoryList() {
        return storyList;
    }

    // Getter for the selected story LiveData
    public MutableLiveData<NewsStory> getSelectedStory() {
        return selectedStory;
    }

    // Method to fetch the list of news stories from Firestore
    public void fetchStoryList() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, User> userCache = new HashMap<>(); // Cache for user data

        // Calculate the timestamp for 24 hours ago
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -24);
        Date cutoffDate = calendar.getTime();
        Timestamp cutoffTimestamp = new Timestamp(cutoffDate);

        // Query for stories created within the last 24 hours
        db.collection("story")
                .whereGreaterThanOrEqualTo("dateTime", cutoffTimestamp)
                .orderBy("dateTime", Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener(querySnapshot -> {
                    Log.d("Firestore", "Stories available: " + querySnapshot.size());
                    List<NewsStory> recentStories = new ArrayList<>();

                    for (DocumentSnapshot storyDoc : querySnapshot.getDocuments()) {
                        NewsStory story = storyDoc.toObject(NewsStory.class);
                        if (story != null) {
                            String userID = story.getUserID(); // Assumes Story class has a userID field

                            if (userCache.containsKey(userID)) {
                                // Use cached user details
                                User user = userCache.get(userID);
                                populateStoryWithUser(story, user);
                                recentStories.add(story);
                            } else {
                                // Fetch user details from Firebase for uncached user IDs
                                db.collection("users")
                                        .document(userID)
                                        .get()
                                        .addOnSuccessListener(userDoc -> {
                                            if (userDoc.exists()) {
                                                User user = userDoc.toObject(User.class);
                                                if (user != null) {
                                                    userCache.put(userID, user); // Cache the user details
                                                    populateStoryWithUser(story, user);
                                                    recentStories.add(story);
                                                    Log.d("Firestore", "User found for ID: " + userID);
                                                }
                                            } else {
                                                Log.d("Firestore", "User not found for ID: " + userID);
                                            }

                                            // Update LiveData or UI
                                            storyList.postValue(recentStories);
                                        })
                                        .addOnFailureListener(e -> Log.e("Firestore", "Error fetching user details", e));
                            }
                        }
                    }

                    Log.d("Firestore", "Fetched recent stories.");
                })
                .addOnFailureListener(e -> Log.e("Firestore", "Error fetching stories", e));

    }

    // Helper method to populate a story with user details
    private void populateStoryWithUser(NewsStory story, User user) {
        story.setUsername(user.getUsername());
        story.setImgUrl(user.getImageUrl());
    }
}
