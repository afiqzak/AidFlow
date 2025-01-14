package com.example.aidflow;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;

public class UserViewModel extends ViewModel {
    // LiveData to hold the user data
    private final MutableLiveData<User> user = new MutableLiveData<>();

    // Getter for the user LiveData
    public LiveData<User> getUser() {
        return user;
    }

    // Method to fetch user data from Firestore based on userID
    public void fetchUserData(String userID) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("users").document(userID).get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        // Convert the document snapshot to a User object
                        User fetchedUser = documentSnapshot.toObject(User.class);
                        Log.d("UserViewModel", "User data: " + fetchedUser.getImageUrl());
                        user.setValue(fetchedUser);
                    } else {
                        // Handle the case where the document doesn't exist
                        Log.d("UserViewModel", "No such document");
                        user.setValue(null); // Or set a default User object
                    }
                })
                .addOnFailureListener(e -> {
                    // Handle the error case
                    Log.w("UserViewModel", "Error getting document", e);
                    user.setValue(null); // Set to null or handle the error appropriately
                });
    }
}
