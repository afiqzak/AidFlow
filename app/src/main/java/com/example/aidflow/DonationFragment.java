package com.example.aidflow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

public class DonationFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_donation, container, false);
        Button donation_button = view.findViewById(R.id.History);
        donation_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction fr = requireActivity().getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.FragmentViewMain,new HistoryFragment());
                fr.addToBackStack(null);
                fr.commit();
            }
        });

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DonateFilterFragment floatingLayout = new DonateFilterFragment();
                floatingLayout.show(requireActivity().getSupportFragmentManager().beginTransaction(), "FloatingLayout");
            }
        });

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
