package com.example.aidflow;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

public class DonationFragment extends Fragment {

    private DonationViewModel donationViewModel;
    private FloatingActionButton fab_donate, fab_history;
    private Button history_button, donation_button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_donation, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Get current user id
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        // Initialize ViewModel
        donationViewModel = new ViewModelProvider(requireActivity()).get(DonationViewModel.class);

        // Initialize buttons and FABs
        history_button = view.findViewById(R.id.History_button);
        donation_button = view.findViewById(R.id.Donation_button);
        fab_donate = view.findViewById(R.id.fab_donatefilter);
        fab_history = view.findViewById(R.id.fab_history);

        // Initialize fragments
        Fragment donate = new DonationDonateFragment();
        Fragment history = new DonationHistoryFragment();

        // Observe changes in fromHistFilter LiveData
        donationViewModel.getFromHistFilter().observe(getViewLifecycleOwner(), fromHistFilter -> {
            Log.d("DonationFragment", "fromHistFilter changed:" + fromHistFilter);
            if (fromHistFilter == null || !fromHistFilter) {
                // Update UI for donation view
                history_button.setAlpha(1.0F);
                donation_button.setAlpha(0.5F);
                fab_history.setVisibility(View.GONE);
                fab_donate.setVisibility(View.VISIBLE);

                // Fetch donation data from Firebase
                donationViewModel.fetchDonations();

                // Replace fragment with DonationDonateFragment
                FragmentTransaction fr = requireActivity().getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.FCVDonation, donate, "DonationDonateFragment");
                fr.addToBackStack(null);
                fr.commit();
            } else {
                // Update UI for history view
                donation_button.setAlpha(1.0F);
                history_button.setAlpha(0.5F);
                fab_donate.setVisibility(View.GONE);
                fab_history.setVisibility(View.VISIBLE);

                // Replace fragment with DonationHistoryFragment
                FragmentTransaction fr = requireActivity().getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.FCVDonation, history, "DonationHistoryFragment");
                Log.d("DonationFragment", "FragmentTransaction 2 initiated");
                fr.addToBackStack(null);
                fr.commit();
            }
        });

        // Set click listener for history button
        history_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Fetch donation history and update filter
                donationViewModel.fetchDonationHistory(userId, 0);
                donationViewModel.getSelectedFilter().setValue(R.id.rb_30_days);

                // Update UI for history view
                donation_button.setAlpha(1.0F);
                history_button.setAlpha(0.5F);
                fab_donate.setVisibility(View.GONE);
                fab_history.setVisibility(View.VISIBLE);

                // Replace fragment with DonationHistoryFragment
                FragmentTransaction fr = requireActivity().getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.FCVDonation, history, "DonationHistoryFragment");
                Log.d("DonationFragment", "FragmentTransaction 2 initiated");
                fr.addToBackStack(null);
                fr.commit();
            }
        });

        // Set click listener for donate FAB
        fab_donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show donation filter dialog
                DonationDonateFilterFragment donateFilter = new DonationDonateFilterFragment();
                donateFilter.show(requireActivity().getSupportFragmentManager().beginTransaction(), "donateFilter");
            }
        });

        // Set click listener for donation button
        donation_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Update UI for donation view
                history_button.setAlpha(1.0F);
                donation_button.setAlpha(0.5F);
                fab_history.setVisibility(View.GONE);
                fab_donate.setVisibility(View.VISIBLE);

                // Replace fragment with DonationDonateFragment
                FragmentTransaction fr = requireActivity().getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.FCVDonation, donate, "DonationDonateFragment");
                fr.addToBackStack(null);
                fr.commit();
            }
        });

        // Set click listener for history FAB
        fab_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to donation history filter
                Navigation.findNavController(view).navigate(R.id.donationHistFilter);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        // Reset fromHistFilter value
        donationViewModel.getFromHistFilter().setValue(false);
    }
}


