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
    private boolean isHistoryFiltered=false;
    private RecyclerView recyclerView;
    private FirebaseFirestore db;
    private DonationViewModel donationViewModel;

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

        donationViewModel = new ViewModelProvider(requireActivity()).get(DonationViewModel.class);

        donationViewModel.getFilteredDonationHistory().observe(getViewLifecycleOwner(), filteredHistory -> {
            RecyclerView RVHistory = view.findViewById(R.id.recyclerView_history);
            RVHistory.setLayoutManager(new LinearLayoutManager(getContext()));
            DonationHistoryGroupAdaptor adaptor = new DonationHistoryGroupAdaptor(filteredHistory, getContext());
            RVHistory.setAdapter(adaptor);
        });
    }

}


