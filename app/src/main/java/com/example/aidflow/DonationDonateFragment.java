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

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DonationDonateFragment extends Fragment{

    private FirebaseFirestore db;
    private ArrayList<Donation> donationList;
    private RecyclerView recyclerView;
    private DonationAdapter adapter;
    private boolean isFiltered = false;
    private Set<String> filterUrgencies = new HashSet<>();
    private Set<String> filterProjects = new HashSet<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_donation_donate, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        donationList = new ArrayList<Donation>();

        db = FirebaseFirestore.getInstance();

        if (isFiltered == true){
            fetchFilteredDonation();
        } else {
            fetchDonations();
        }

//
//         //Initialize FloatingActionButton
//        FloatingActionButton fab = view.findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getContext(), "FAB clicked", Toast.LENGTH_SHORT).show();
//            }
//        });


    }

    private void fetchFilteredDonation() {

        List<String> urgencyList = new ArrayList<>(filterUrgencies);
        List<String> projectList = new ArrayList<>(filterProjects);

        Query query = db.collection("donations");

        if (!urgencyList.isEmpty()) {
            updateRecyclerView();
            query = query.whereIn("urgency", urgencyList);
        }
        if (!projectList.isEmpty()) {
            updateRecyclerView();
            query = query.whereIn("projectName", projectList);
        }

        if (urgencyList.isEmpty() && projectList.isEmpty()) {
            updateRecyclerView();
            Toast.makeText(getContext(), "No filters selected. Showing all donations.", Toast.LENGTH_SHORT).show();
            fetchDonations();
            return;
        }

        query.get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    if (queryDocumentSnapshots != null) {
                        List<Donation> filteredList = new ArrayList<>();

                        for (DocumentSnapshot document : queryDocumentSnapshots) {
                            Donation donation = retrieveFromDB(document);
                            filteredList.add(donation);
                        }

                        donationList.clear();
                        donationList.addAll(filteredList);

                        updateRecyclerView();
                    }
                })
                .addOnFailureListener(e -> {
                    Log.e("DonationFragment", "Error fetching filtered data", e);
                    Toast.makeText(getContext(), "Error fetching filtered data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });

    }

    private void updateRecyclerView() {
        if (adapter == null) {
            adapter = new DonationAdapter(donationList, getContext());
            recyclerView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    private void fetchDonations() {
        db.collection("donations")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    Log.d("DonationFragment", "Data fetched successfully!");

                    if(queryDocumentSnapshots != null) {
                        List<Donation> filteredList = new ArrayList<>(); // Temporary list to store filtered data

                        for (DocumentSnapshot document : queryDocumentSnapshots) {
                            Donation donation = retrieveFromDB(document);
                            filteredList.add(donation);
                        }

                        donationList.clear();
                        donationList.addAll(filteredList);

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

    public void setFilterCriteria(Set<String> selectedUrgencies, Set<String> selectedProjects) {
        this.filterUrgencies = selectedUrgencies;
        this.filterProjects = selectedProjects;

        isFiltered = true;
        fetchFilteredDonation();
    }

    private Donation retrieveFromDB(DocumentSnapshot document) {
        String name = document.get("name", String.class);
        String donationId = document.getId();
        String projectName = document.get("projectName", String.class);
        String description = document.get("description", String.class);
        String dueDate = document.get("dueDate", String.class);

        Long progressLong = document.getLong("progress");
        int progressValue = progressLong != null ? progressLong.intValue() : 0;

        String organizationName = document.get("organizationName", String.class);

        Long targetDonation = document.getLong("targetDonationAmount");
        long targetDonationAmount = targetDonation != null ? targetDonation.longValue() : 0;

        Long currentDonation = document.getLong("currentDonationAmount");
        long currentDonationAmount = currentDonation != null ? currentDonation.longValue() : 0;

        String urgency = document.get("urgency", String.class);

        return new Donation(donationId, name, projectName, description, dueDate, progressValue, urgency, organizationName, targetDonationAmount, currentDonationAmount);
    }
}
