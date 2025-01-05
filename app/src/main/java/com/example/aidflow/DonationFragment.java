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

        //get current user id
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        donationViewModel = new ViewModelProvider(requireActivity()).get(DonationViewModel.class);

        //fetch data from firebase
        donationViewModel.fetchDonations();

        history_button = view.findViewById(R.id.History_button);
        donation_button = view.findViewById(R.id.Donation_button);

        fab_donate = view.findViewById(R.id.fab_donatefilter);
        fab_history = view.findViewById(R.id.fab_history);

        Fragment donate = new DonationDonateFragment();
        Fragment history = new DonationHistoryFragment();

        donationViewModel.getFromHistFilter().observe(getViewLifecycleOwner(), fromHistFilter -> {
            Log.d("DonationFragment", "fromHistFilter changed:" + fromHistFilter);
            if (fromHistFilter == null || !fromHistFilter){
                //make donation button partially transparent, history button visible
                history_button.setAlpha(1.0F);
                donation_button.setAlpha(0.5F);

                // Make FAB2 visible, FAB1 invisible
                fab_history.setVisibility(View.GONE);
                fab_donate.setVisibility(View.VISIBLE);

                // Replace the FCVDonation with DonationDonateFragment
                FragmentTransaction fr = requireActivity().getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.FCVDonation, donate, "DonationDonateFragment");
                fr.addToBackStack(null);
                fr.commit();
            } else {
                //make history button partially transparent, donation button visible
                donation_button.setAlpha(1.0F);
                history_button.setAlpha(0.5F);

                // Make FAB2 visible, FAB1 invisible
                fab_donate.setVisibility(View.GONE);
                fab_history.setVisibility(View.VISIBLE);

                // Replace the FCVDonation with DonationHistoryFragment
                FragmentTransaction fr = requireActivity().getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.FCVDonation, history, "DonationHistoryFragment");
                Log.d("DonationFragment", "FragmentTransaction 2 initiated");
                fr.addToBackStack(null);
                fr.commit();
            }
        });

        history_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                donationViewModel.fetchDonationHistory(userId, 0);
                donationViewModel.getSelectedFilter().setValue(R.id.rb_30_days);

                //make history button partially transparent, donation button visible
                donation_button.setAlpha(1.0F);
                history_button.setAlpha(0.5F);

                // Make FAB2 visible, FAB1 invisible
                fab_donate.setVisibility(View.GONE);
                fab_history.setVisibility(View.VISIBLE);

                // Replace the FCVDonation with DonationHistoryFragment
                FragmentTransaction fr = requireActivity().getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.FCVDonation, history, "DonationHistoryFragment");
                Log.d("DonationFragment", "FragmentTransaction 2 initiated");
                fr.addToBackStack(null);
                fr.commit();
            }
        });

        fab_donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DonationDonateFilterFragment donateFilter = new DonationDonateFilterFragment();
                donateFilter.show(requireActivity().getSupportFragmentManager().beginTransaction(), "donateFilter");
            }
        });


        donation_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //make donation button partially transparent, history button visible
                history_button.setAlpha(1.0F);
                donation_button.setAlpha(0.5F);

                // Make FAB2 visible, FAB1 invisible
                fab_history.setVisibility(View.GONE);
                fab_donate.setVisibility(View.VISIBLE);

                FragmentTransaction fr = requireActivity().getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.FCVDonation, donate, "DonationDonateFragment");
                fr.addToBackStack(null);
                fr.commit();
            }
        });

        fab_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.donationHistFilter);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        donationViewModel.getFromHistFilter().setValue(false);
    }
}


