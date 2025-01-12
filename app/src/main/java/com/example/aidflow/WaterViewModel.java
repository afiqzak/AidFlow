package com.example.aidflow;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WaterViewModel extends ViewModel {
    private MutableLiveData<List<WaterReport>> pendingReport = new MutableLiveData<>();
    private MutableLiveData<List<WaterReport>> doneReport = new MutableLiveData<>();
    private MutableLiveData<WaterReport> selectedReport = new MutableLiveData<>();

    public MutableLiveData<List<WaterReport>> getPendingReport() {
        return pendingReport;
    }

    public MutableLiveData<List<WaterReport>> getDoneReport() {
        return doneReport;
    }

    public MutableLiveData<WaterReport> getSelectedReport() {
        return selectedReport;
    }

    public void fetchPendingReport(String userID) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        List<WaterReport> allPending = new ArrayList<>();

        // Query for report that haven't been solve yet
        db.collection("report")
                .whereEqualTo("status", false)
                .whereEqualTo("userID", userID)
                .orderBy("reportDate", Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener(querySnapshot -> {
                    Log.d("Firestore", "pending report available: " + querySnapshot.size());

                    for (DocumentSnapshot doc : querySnapshot.getDocuments()) {
                        WaterReport report = doc.toObject(WaterReport.class);
                        if (report != null) {
                            allPending.add(report);
                        }
                    }

                    // Update LiveData on the main thread
                    pendingReport.postValue(allPending);

                    Log.d("Firestore", "pending report available: " + allPending.size());
                })
                .addOnFailureListener(e -> Log.e("Firestore", "Error fetching pending report", e));
    }

    public void fetchDoneReport(String userID) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        List<WaterReport> allPending = new ArrayList<>();

        // Query for report that haven't been solve yet
        db.collection("report")
                .whereEqualTo("status", true)
                .whereEqualTo("userID", userID)
                .whereEqualTo("rate", false)
                .orderBy("doneDate", Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener(querySnapshot -> {
                    Log.d("Firestore", "done report available: " + querySnapshot.size());

                    for (DocumentSnapshot doc : querySnapshot.getDocuments()) {
                        WaterReport report = doc.toObject(WaterReport.class);
                        if (report != null) {
                            allPending.add(report);
                        }
                    }

                    // Update LiveData on the main thread
                    doneReport.postValue(allPending);

                    Log.d("Firestore", "done report available: " + allPending.size());
                })
                .addOnFailureListener(e -> Log.e("Firestore", "Error fetching done report", e));
    }
}