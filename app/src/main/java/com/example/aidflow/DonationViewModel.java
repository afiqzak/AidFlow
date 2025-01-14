package com.example.aidflow;

import android.util.Log;
import android.widget.RadioButton;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class DonationViewModel extends ViewModel {
    // LiveData to hold the list of all donations
    private MutableLiveData<List<Donation>> donations = new MutableLiveData<>();
    // LiveData to hold the list of filtered donations
    private MutableLiveData<List<Donation>> filteredDonations = new MutableLiveData<>();
    // LiveData to hold the map of filtered donation history grouped by date
    private MutableLiveData<Map<String, List<DonationHistory>>> filteredDonationHistory = new MutableLiveData<>();
    // LiveData to indicate if the history filter is applied
    private MutableLiveData<Boolean> fromHistFilter = new MutableLiveData<>(false);
    // LiveData to hold the selected donation
    private MutableLiveData<Donation> selectedDonation = new MutableLiveData<>();
    // LiveData to hold the selected filter
    private MutableLiveData<Integer> selectedFilter = new MutableLiveData<>();
    // Map to hold the donation data
    private Map<String, Donation> donationMap = new LinkedHashMap<>();

    // Getter for donations LiveData
    public MutableLiveData<List<Donation>> getDonations() {
        return donations;
    }

    // Getter for filtered donations LiveData
    public MutableLiveData<List<Donation>> getFilteredDonations() {
        return filteredDonations;
    }

    // Getter for selected donation LiveData
    public MutableLiveData<Donation> getSelectedDonation() {
        return selectedDonation;
    }

    // Getter for filtered donation history LiveData
    public MutableLiveData<Map<String, List<DonationHistory>>> getFilteredDonationHistory() {
        return filteredDonationHistory;
    }

    // Getter for history filter LiveData
    public MutableLiveData<Boolean> getFromHistFilter() {
        return fromHistFilter;
    }

    // Getter for selected filter LiveData
    public MutableLiveData<Integer> getSelectedFilter() {
        return selectedFilter;
    }

    // Setter for selected donation
    public void setSelectedDonation(Donation donation) {
        selectedDonation.setValue(donation);
    }

    // Method to fetch donations from Firestore
    public void fetchDonations() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        List<Donation> allDonationAvailable = new ArrayList<>();

        // Get today's date as a Timestamp
        Timestamp today = Timestamp.now();

        // Query for events where the date is greater than or equal to today and status is false
        db.collection("donation")
                .whereGreaterThanOrEqualTo("endDate", today)
                .whereEqualTo("status", false)
                .get()
                .addOnSuccessListener(querySnapshot -> {
                    Log.d("Firestore", "donation available: " + querySnapshot.size());

                    for (DocumentSnapshot doc : querySnapshot.getDocuments()) {
                        Donation donation = doc.toObject(Donation.class);
                        if (donation != null) {
                            Timestamp timestamp = doc.getTimestamp("endDate");
                            Date date = timestamp.toDate();
                            if (donation != null && date != null) {
                                donation.setDueDate(formatDate(date));
                                donation.setDonationID(doc.getId());
                                donationMap.putIfAbsent(doc.getId(), donation);
                                allDonationAvailable.add(donation);
                            }
                        }
                    }

                    // Update LiveData on the main thread
                    donations.postValue(allDonationAvailable);
                    filteredDonations.postValue(allDonationAvailable);

                    Log.d("Firestore", "donation available: " + allDonationAvailable.size());
                })
                .addOnFailureListener(e -> Log.e("Firestore", "Error fetching unjoined events", e));
    }

    // Method to filter donations based on selected urgencies and categories
    public void filterDonations(Set<String> selectedUrgencies, Set<String> selectedCategorys) {
        List<Donation> originalDonations = donations.getValue();
        if (originalDonations == null || (selectedUrgencies.isEmpty() && selectedCategorys.isEmpty())) {
            // No filters applied, show all donations
            filteredDonations.setValue(originalDonations);
            return;
        }

        filteredDonations.setValue(null); // Clear the list while filtering

        Log.d("DonationViewModel", "selectedUrgencies:: " + selectedUrgencies);
        Log.d("DonationViewModel", "selectedCategorys:: " + selectedCategorys);

        // Apply filters
        List<Donation> filteredList = new ArrayList<>();
        for (Donation donation : originalDonations) {
            boolean matchesUrgency = selectedUrgencies.isEmpty() || selectedUrgencies.contains(donation.getUrgency());
            boolean matchesProject = selectedCategorys.isEmpty() || selectedCategorys.contains(donation.getCategory());
            Log.d("DonationViewModel", "donation: " + donation.getDonationTitle());
            Log.d("DonationViewModel", "matchesUrgency: " + matchesUrgency);
            Log.d("DonationViewModel", "matchesCategory: " + matchesProject);

            if (matchesUrgency && matchesProject) {
                Log.d("DonationViewModel", "Donation matches filter: " + donation.getDonationTitle());
                filteredList.add(donation);
            }
        }

        Log.d("DonationViewModel", "Filtered donations: " + filteredList.size());
        filteredDonations.postValue(filteredList);
    }

    // Method to fetch donation history from Firestore
    public void fetchDonationHistory(String userID, int days) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Donation> donationCache = new HashMap<>(); // Cache for donation data

        // Default to 30 days if an invalid value is passed
        if (days <= 0) days = 30;

        // Calculate the start date
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -days);
        Date startDate = calendar.getTime();
        Timestamp startTimestamp = new Timestamp(startDate);

        // Query for donation history within the date range
        db.collection("donation_payment")
                .whereEqualTo("userID", userID)
                .whereGreaterThanOrEqualTo("donatedDate", startTimestamp)
                .orderBy("donatedDate", Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener(querySnapshot -> {
                    Log.d("Firestore", "history available: " + querySnapshot.size());
                    Map<String, List<DonationHistory>> groupedHistory = new LinkedHashMap<>();

                    for (DocumentSnapshot doc : querySnapshot.getDocuments()) {
                        DonationHistory history = doc.toObject(DonationHistory.class);
                        if (history != null) {
                            Timestamp timestamp = doc.getTimestamp("donatedDate");
                            if (timestamp != null) {
                                Date date = timestamp.toDate();
                                history.setDate(formatDate(date));

                                String donationID = history.getDonationID();

                                if (donationCache.containsKey(donationID)) {
                                    // Use cached donation details
                                    Donation donation = donationCache.get(donationID);
                                    populateDonationHistory(history, donation);

                                    groupedHistory.putIfAbsent(formatDate(date), new ArrayList<>());
                                    groupedHistory.get(formatDate(date)).add(history);
                                } else {
                                    // Fetch from Firebase for uncached donation IDs
                                    db.collection("donation")
                                            .document(donationID)
                                            .get()
                                            .addOnSuccessListener(donationDoc -> {
                                                if (donationDoc.exists()) {
                                                    Donation donation = donationDoc.toObject(Donation.class);
                                                    if (donation != null) {
                                                        donationCache.put(donationID, donation); // Cache the donation
                                                        populateDonationHistory(history, donation);
                                                    }
                                                } else {
                                                    Log.d("Firestore", "Donation not found for ID: " + donationID);
                                                }

                                                groupedHistory.putIfAbsent(formatDate(date), new ArrayList<>());
                                                groupedHistory.get(formatDate(date)).add(history);

                                                // Update LiveData
                                                filteredDonationHistory.postValue(groupedHistory);
                                            })
                                            .addOnFailureListener(e -> Log.e("Firestore", "Error fetching donation details", e));
                                }
                            }
                        }
                    }

                    Log.d("Firestore", "Fetched donation history.");
                })
                .addOnFailureListener(e -> Log.e("Firestore", "Error fetching donation history", e));
    }

    // Method to format date to "d MMM" format
    public String formatDate(Date date) {
        if (date == null) {
            return null; // Handle null date
        }

        SimpleDateFormat outputFormat = new SimpleDateFormat("d MMM", Locale.getDefault()); //format date to d mmm, e.g. 1 JAN
        return outputFormat.format(date);
    }

    // Method to populate donation history with donation details
    private void populateDonationHistory(DonationHistory history, Donation donation) {
        history.setPIC(donation.getPIC());
        history.setCategory(donation.getCategory());
        history.setDonationTitle(donation.getDonationTitle());
    }
}
