package com.example.aidflow;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;

public class UserViewModel extends ViewModel {
    private final MutableLiveData<User> user = new MutableLiveData<>();
    private ListenerRegistration userListener;

    public LiveData<User> getUser() {
        return user;
    }

    public void startListening(String userID){
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        //listen for any change
        userListener = db.collection("users").document(userID)
                .addSnapshotListener((documentSnapshot,e ) -> {
                    if (e != null){
                        Log.w("UserViewModel", "Listen failed.", e);
                        return;
                    }
                    if (documentSnapshot != null && documentSnapshot.exists()) {
                        User updatedUser = documentSnapshot.toObject(User.class);
                        Log.d("UserViewModel", "User data: " + updatedUser);
                        user.setValue(updatedUser);
                    }
                });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (userListener != null) {
            userListener.remove(); // stop listening to firebase updates when viewmodel is cleared
        }
    }
}
