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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class DonationViewModel extends ViewModel {
    private MutableLiveData<List<Donation>> donations = new MutableLiveData<>();
    private MutableLiveData<List<Donation>> filteredDonations = new MutableLiveData<>();
    private MutableLiveData<Map<String, List<DonationHistory>>> filteredDonationHistory = new MutableLiveData<>();
    private MutableLiveData<Boolean> fromHistFilter = new MutableLiveData<>(false);
    private MutableLiveData<Donation> selectedDonation = new MutableLiveData<>();
    private MutableLiveData<Integer> selectedFilter = new MutableLiveData<>();
    private Map<String, Donation> donationMap = new LinkedHashMap<>();

    public MutableLiveData<List<Donation>> getDonations() {
        return donations;
    }

    public MutableLiveData<List<Donation>> getFilteredDonations() {
        return filteredDonations;
    }

    public MutableLiveData<Donation> getSelectedDonation() {
        return selectedDonation;
    }

    public MutableLiveData<Map<String, List<DonationHistory>>> getFilteredDonationHistory() {
        return filteredDonationHistory;
    }

    public MutableLiveData<Boolean> getFromHistFilter() {
        return fromHistFilter;
    }

    public MutableLiveData<Integer> getSelectedFilter() {
        return selectedFilter;
    }

    public void setSelectedDonation(Donation donation) {
        selectedDonation.setValue(donation);
    }

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

    public void filterDonations(Set<String> selectedUrgencies, Set<String> selectedCategorys) {
        List<Donation> originalDonations = donations.getValue();
        if (originalDonations == null || (selectedUrgencies.isEmpty() && selectedCategorys.isEmpty())) {
            // No filters applied, show all donations
            filteredDonations.setValue(originalDonations);
            return;
        }

        // Apply filters
        List<Donation> filteredList = new ArrayList<>();
        for (Donation donation : originalDonations) {
            boolean matchesUrgency = selectedUrgencies.isEmpty() || selectedUrgencies.contains(donation.getUrgency());
            boolean matchesProject = selectedCategorys.isEmpty() || selectedCategorys.contains(donation.getCategory());

            if (matchesUrgency && matchesProject) {
                filteredList.add(donation);
            }
        }

        filteredDonations.setValue(filteredList);
    }

    public void fetchDonationHistory(String userID, int days) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

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

                                Donation donation = donationMap.get(history.getDonationID());
                                if (donation != null) {
                                    history.setPIC(donation.getPIC());
                                    history.setCategory(donation.getCategory());
                                    history.setDonationTitle(donation.getDonationTitle());
                                } else {
                                    Log.d("firestore", "donation is null");
                                }

                                groupedHistory.putIfAbsent(formatDate(date), new ArrayList<>());
                                groupedHistory.get(formatDate(date)).add(history);
                            }
                        }
                    }

                    // Update LiveData on the main thread
                    filteredDonationHistory.postValue(groupedHistory);

                    Log.d("Firestore", "history available: " + querySnapshot.getDocuments().size());
                })
                .addOnFailureListener(e -> Log.e("Firestore", "Error fetching donation history", e));
    }

    public String formatDate(Date date) {
        if (date == null) {
            return null; // Handle null date
        }

        SimpleDateFormat outputFormat = new SimpleDateFormat("d MMM", Locale.getDefault()); //format date to d mmm, e.g. 1 JAN
        return outputFormat.format(date);
    }
}
