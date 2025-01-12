package com.example.aidflow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DonationDonateFragment extends Fragment {

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
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Sample data
        List<Donation> donationList = new ArrayList<>();
        donationList.add(new Donation("Funds for Elderly Home", "Community Project", "4 Jan", 20, "medium"));
        donationList.add(new Donation("Upgrading Clean Water Supply", "Water and Sanitation", "4 Jan", 20, "low"));
        donationList.add(new Donation("Funds for Elderly Home", "Community Project", "4 Jan", 20, "high"));
        donationList.add(new Donation("Funds for Elderly Home", "Community Project", "4 Jan", 20, "medium"));
        donationList.add(new Donation("Upgrading Clean Water Supply", "Water and Sanitation", "4 Jan", 20, "low"));
        donationList.add(new Donation("Funds for Elderly Home", "Community Project", "4 Jan", 20, "high"));
        donationList.add(new Donation("Funds for Elderly Home", "Community Project", "4 Jan", 20, "medium"));
        donationList.add(new Donation("Upgrading Clean Water Supply", "Water and Sanitation", "4 Jan", 20, "low"));
        donationList.add(new Donation("Funds for Elderly Home", "Community Project", "4 Jan", 20, "high"));
        donationList.add(new Donation("Funds for Elderly Home", "Community Project", "4 Jan", 20, "medium"));
        donationList.add(new Donation("Upgrading Clean Water Supply", "Water and Sanitation", "4 Jan", 20, "low"));
        donationList.add(new Donation("Funds for Elderly Home", "Community Project", "4 Jan", 20, "high"));


        // Attach adapter to RecyclerView
        DonationAdapter adapter = new DonationAdapter(donationList,getContext());
        recyclerView.setAdapter(adapter);

        // Initialize FloatingActionButton
//        FloatingActionButton fab = view.findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getContext(), "FAB clicked", Toast.LENGTH_SHORT).show();
//            }
//        });


    }
}
