package com.example.aidflow;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class DonationHistoryFragment extends Fragment {

    private String selectedHistFilter;
    private boolean isHistoryFiltered = false;
    private ArrayList<DonationHistory> historyList;
    private RecyclerView recyclerView;
    private FirebaseFirestore db;
    private HistoryAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_donation_history, container, false);

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.recyclerView_history);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        db = FirebaseFirestore.getInstance();

        historyList = new ArrayList<DonationHistory>();

        super.onViewCreated(view, savedInstanceState);
        Log.d("DonationHistoryFragment", "onViewCreated - isHistoryFiltered: " + isHistoryFiltered);

        if (isHistoryFiltered==true) {
            Log.d("DonationHistoryFragment", "Fetching filtered history donations...");
            fetchFilteredHistDonation();
        } else {
            Log.d("DonationHistoryFragment", "Fetching non-filtered history donations...");
            fetchHistDonations();
        }

        // Sample data
//        List<History> historyList = new ArrayList<>();
//        historyList.add(new History("noy","bagi makan kucing","community project","21 Feb", 1000,"cash"))   ;
//        historyList.add(new History("noy","bagi makan kucing","community project","21 Feb", 1000,"cash"))   ;
//        historyList.add(new History("noy","bagi makan kucing","community project","21 Feb", 1000,"cash"))   ;
//        historyList.add(new History("noy","bagi makan kucing","community project","21 Feb", 1000,"cash"))   ;
//        historyList.add(new History("noy","bagi makan kucing","community project","21 Feb", 1000,"cash"))   ;
//        historyList.add(new History("noy","bagi makan kucing","community project","21 Feb", 1000,"cash"))   ;
//        historyList.add(new History("noy","bagi makan kucing","community project","21 Feb", 1000,"cash"))   ;
//        historyList.add(new History("noy","bagi makan kucing","community project","21 Feb", 1000,"cash"))   ;
//        historyList.add(new History("noy","bagi makan kucing","community project","21 Feb", 1000,"cash"))   ;


//        // Attach adapter to RecyclerView
//        HistoryAdapter adapter = new HistoryAdapter(historyList);
//        recyclerView.setAdapter(adapter);



    }

    private void updateRecyclerView() {
        if (adapter == null) {
            adapter = new HistoryAdapter(historyList, getContext());
            recyclerView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    private void fetchFilteredHistDonation() {
        String selectedDay = selectedHistFilter;
        int days = Integer.parseInt(selectedDay);

        if (selectedHistFilter == null || selectedHistFilter.isEmpty()) {
            Log.e("DonationHistoryFragment", "Filter duration not set.");
            return;
        }

        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime startDateTime = currentDateTime.minusDays(days);

        // Convert LocalDateTime to Date (using system default zone)
        Date startDate = Date.from(startDateTime.atZone(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant());

        // Convert Date to Firebase Timestamp
        Timestamp startTimestamp = new Timestamp(startDate);
        Timestamp endTimestamp = new Timestamp(endDate);

        Log.d("DonationFragment", "Filtering donations from: " + startTimestamp.toDate() + " to: " + endTimestamp.toDate());

        String currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        Query query = db.collection("donationSubmit")
                .whereEqualTo("userId", currentUserId)
                .whereGreaterThanOrEqualTo("transactionDate", startTimestamp)
                .whereLessThanOrEqualTo("transactionDate", endTimestamp);

        query.get()
                .addOnSuccessListener(queryDocumentSnapshots -> {

                    if (queryDocumentSnapshots != null) {
                        Log.d("DonationHistoryFragment", "Number of documents fetched: " + queryDocumentSnapshots.size());
                        historyList.clear(); // Clear previous list

                        for (DocumentSnapshot document : queryDocumentSnapshots) {
                            Log.d("DonationHistoryFragment", "Fetched document: " + document.getData());
                            DonationHistory hist = retrieveHistFromDB(document);
                            historyList.add(hist);
                        }
                        updateRecyclerView();
                    }
                })
                .addOnFailureListener(e -> {
                    Log.e("DonationHistoryFragment", "Error fetching filtered data", e);
                    Toast.makeText(getContext(), "Error fetching filtered data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    private void fetchHistDonations() {
        String currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        Query query = db.collection("donationSubmit")
                .whereEqualTo("userId", currentUserId);
        query.get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    Log.d("DonationHistoryFragment", "Data fetched for no filter successfully!");

                    if(queryDocumentSnapshots != null) {
                        List<DonationHistory> filteredHist = new ArrayList<>(); // Temporary list to store filtered data

                        for (DocumentSnapshot document : queryDocumentSnapshots) {
                            DonationHistory history = retrieveHistFromDB(document);
                            filteredHist.add(history);
                        }

                        historyList.clear();
                        historyList.addAll(filteredHist);

                        updateRecyclerView();
                    } else {
                        Log.e("DonationFragment", "No data found in Firestore");
                    }
                })
                .addOnFailureListener(e ->{
                    Log.e("DonationFragment", "Error fetching data", e);
                    Toast.makeText(getContext(), "Error fetching data: "+ e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    public void setHistFilterCriteria(String selectedHistFilter) {
        Log.d("DonationHistoryFragment", "Filter criteria set: " + selectedHistFilter);
        this.selectedHistFilter = selectedHistFilter;
        isHistoryFiltered = true;
    }

    private DonationHistory retrieveHistFromDB(DocumentSnapshot document){
        String userID = FirebaseAuth.getInstance().getUid();
        String orgName = document.get("organizerName", String.class);
        String donationName = document.get("donationName", String.class);
        String projectName = document.get("projectName", String.class);
        Timestamp transactionDateTimestamp = document.getTimestamp("transactionDate");

        // Convert Timestamp to readable date string
        String transactionDate = transactionDateTimestamp != null
                ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                .format(transactionDateTimestamp.toDate())
                : "-";

        Long amount = document.get("donationAmount", Long.class);
        int amountDonation = amount != null ? amount.intValue() : 0;
        String paymentMethod = document.get("selectedMethod", String.class);

        return new DonationHistory(userID, orgName, donationName, projectName, transactionDate, amountDonation, paymentMethod);
    }



}


