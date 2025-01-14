package com.example.aidflow;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class VolunteerViewModel extends ViewModel {
    // LiveData to hold lists of volunteers
    private MutableLiveData<List<Volunteer>> notJoinedVolunteers = new MutableLiveData<>();
    private MutableLiveData<List<Volunteer>> joinedVolunteers = new MutableLiveData<>();
    private MutableLiveData<List<Volunteer>> filteredNotJoinedVolunteers = new MutableLiveData<>();
    private MutableLiveData<List<Volunteer>> filteredJoinedVolunteers = new MutableLiveData<>();
    private MutableLiveData<Volunteer> selectedVolunteer = new MutableLiveData<>();

    // List to hold IDs of joined volunteers
    private List<String> joinedID = new ArrayList<>();

    // Getter for joinedID list
    public List<String> getJoinedID() {
        return joinedID;
    }

    // Getter for notJoinedVolunteers LiveData
    public MutableLiveData<List<Volunteer>> getNotJoinedVolunteers() {
        return notJoinedVolunteers;
    }

    // Getter for joinedVolunteers LiveData
    public MutableLiveData<List<Volunteer>> getJoinedVolunteers() {
        return joinedVolunteers;
    }

    // Getter for filteredJoinedVolunteers LiveData
    public MutableLiveData<List<Volunteer>> getFilteredJoinedVolunteers() {
        return filteredJoinedVolunteers;
    }

    // Getter for filteredNotJoinedVolunteers LiveData
    public MutableLiveData<List<Volunteer>> getFilteredNotJoinedVolunteers() {
        return filteredNotJoinedVolunteers;
    }

    // Getter for selectedVolunteer LiveData
    public MutableLiveData<Volunteer> getSelectedVolunteer() {
        return selectedVolunteer;
    }

    // Fetch volunteers that the user has not joined
    public void fetchNotJoinedVolunteers(String uid) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        List<Volunteer> allUnjoinedEvents = Collections.synchronizedList(new ArrayList<>());

        // Get today's date as a Timestamp
        Timestamp today = Timestamp.now();

        // Query for events where the date is greater than or equal to today and status is false
        db.collection("volunteer")
                .whereGreaterThanOrEqualTo("date", today)
                .whereEqualTo("status", false)
                .get()
                .addOnSuccessListener(querySnapshot -> {
                    Log.d("Firestore", "doc size: " + querySnapshot.size());
                    Set<String> joinedEventSet = new HashSet<>(joinedID);

                    // Iterate through the documents and add unjoined events to the list
                    for (DocumentSnapshot doc : querySnapshot.getDocuments()) {
                        if (!joinedEventSet.contains(doc.getId())) {
                            Volunteer event = doc.toObject(Volunteer.class);
                            if (event != null) {
                                Timestamp timestamp = doc.getTimestamp("date");
                                Date date = timestamp.toDate();
                                if (event != null && date != null) {
                                    event.setEventDate(formatDate(date));
                                    event.setVolunteerID(doc.getId());
                                    allUnjoinedEvents.add(event);
                                }
                            }
                        }
                    }

                    // Update LiveData on the main thread
                    notJoinedVolunteers.postValue(new ArrayList<>(allUnjoinedEvents));
                    filteredNotJoinedVolunteers.postValue(new ArrayList<>(allUnjoinedEvents));
                    Log.d("Firestore", "Unjoined events: " + allUnjoinedEvents.size());
                })
                .addOnFailureListener(e -> Log.e("Firestore", "Error fetching unjoined events", e));
    }

    // Filter volunteers based on criteria (e.g., state or region)
    public void filterVolunteers(String state, String region, boolean join) {
        List<Volunteer> targetList = join ? joinedVolunteers.getValue() : notJoinedVolunteers.getValue();

        if (targetList == null) {
            Log.d("Firestore", "Target list is null");
            return;
        }

        List<Volunteer> filtered;

        // Apply filters based on state and region
        if ("Select State".equals(state) && "Select Region".equals(region)) {
            // No filters applied, show all
            filtered = new ArrayList<>(targetList);
        } else if ("Select Region".equals(region)) {
            // Filter by state only
            filtered = targetList.stream()
                    .filter(volunteer -> state.equals(volunteer.getState()))
                    .collect(Collectors.toList());
        } else {
            // Filter by both state and region
            filtered = targetList.stream()
                    .filter(volunteer -> state.equals(volunteer.getState()) && region.equals(volunteer.getRegion()))
                    .collect(Collectors.toList());
        }

        // Update the appropriate LiveData
        if (join) {
            filteredJoinedVolunteers.setValue(filtered);
        } else {
            filteredNotJoinedVolunteers.setValue(filtered);
        }

        Log.d("Firestore", "Filtered events count: " + filtered.size());
    }

    // Fetch IDs of volunteers that the user has joined
    public void fetchJoinedVolunteerID(String userID){
        joinedID.clear();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("volunteer_joined").whereEqualTo("userID", userID).get()
                .addOnSuccessListener(querySnapshot -> {
                    for (DocumentSnapshot doc : querySnapshot) {
                        joinedID.add(doc.getString("eventID"));
                    }
                    Log.d("Firestore", "joined events: " + joinedID.size());
                    fetchVolunteerDetails(joinedID);
                })
                .addOnFailureListener(e -> Log.e("Firestore", "Error fetching joined events", e));
    }

    // Fetch details of volunteers based on their IDs
    private void fetchVolunteerDetails(List<String> eventIds) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        List<List<String>> chunks = chunkList(eventIds, 10); // Split into chunks of 10 IDs
        List<Volunteer> allJoinedEvents = Collections.synchronizedList(new ArrayList<>());
        AtomicInteger pendingChunks = new AtomicInteger(chunks.size()); // Track pending batch queries

        // Iterate through chunks and fetch details
        for (List<String> chunk : chunks) {
            db.collection("volunteer").whereIn(FieldPath.documentId(), chunk)
                    .get()
                    .addOnSuccessListener(querySnapshot -> {
                        for (DocumentSnapshot doc : querySnapshot) {
                            Volunteer event = doc.toObject(Volunteer.class);
                            Timestamp timestamp = doc.getTimestamp("date");
                            Date date = timestamp.toDate();
                            if (event != null && date != null) {
                                event.setEventDate(formatDate(date));
                                event.setVolunteerID(doc.getId());
                                allJoinedEvents.add(event);
                            }
                        }
                        // Decrement pending chunks and update LiveData if all batches are complete
                        if (pendingChunks.decrementAndGet() == 0) {
                            joinedVolunteers.postValue(new ArrayList<>(allJoinedEvents)); // Copy to prevent threading issues
                            filteredJoinedVolunteers.postValue(new ArrayList<>(allJoinedEvents));
                            Log.d("Firestore", "User joined events: " + allJoinedEvents.size());
                        }
                    })
                    .addOnFailureListener(e -> {
                        Log.e("Firestore", "Error fetching volunteer details", e);
                        pendingChunks.decrementAndGet(); // Decrement even on failure to avoid deadlock
                    });
        }
    }

    // Helper method to split the list into chunks
    private <T> List<List<T>> chunkList(List<T> list, int chunkSize) {
        List<List<T>> chunks = new ArrayList<>();
        for (int i = 0; i < list.size(); i += chunkSize) {
            chunks.add(list.subList(i, Math.min(i + chunkSize, list.size())));
        }
        return chunks;
    }

    // Format date to a specific pattern
    public String formatDate(Date date) {
        if (date == null) {
            return null; // Handle null date
        }

        SimpleDateFormat outputFormat = new SimpleDateFormat("d MMM", Locale.getDefault()); // Format date to d MMM, e.g., 1 JAN
        return outputFormat.format(date);
    }
}