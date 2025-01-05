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
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DonationDonateFragment extends Fragment{

    private FirebaseFirestore db;
    private DonationViewModel donationViewModel;
    private RecyclerView recyclerView;
    private DonationAdapter adapter;  // Declare the adapter
    private boolean isDonationFiltered = false;
    private ArrayList<Donation> donationList;
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

        donationViewModel = new ViewModelProvider(requireActivity()).get(DonationViewModel.class);
        donationViewModel.fetchDonations();

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        donationViewModel.getFilteredDonations().observe(getViewLifecycleOwner(), donations -> {
            Log.d("Fragment", donations.toString());
            DonationAdapter donationAdapter = new DonationAdapter(donations, getContext(), donationViewModel);
            recyclerView.setAdapter(donationAdapter);
        });
    }
}
