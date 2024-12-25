package com.example.aidflow;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Set;


public class DonationFragment  extends Fragment {


    private DonationAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view =  inflater.inflate(R.layout.fragment_donation, container, false);


        Button history_button = view.findViewById(R.id.History_button);
        Button donation_button = (Button) view.findViewById(R.id.Donation_button);
        history_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FloatingActionButton fab_donate;
                FloatingActionButton fab_history;
                donation_button.setAlpha(0.5F);
                history_button.setAlpha(1.0F);


                fab_donate = view.findViewById(R.id.fab_donatefilter);
                fab_history = view.findViewById(R.id.fab_history);

                // Make FAB2 visible, FAB1 invisible
                fab_donate.setVisibility(View.GONE);
                fab_history.setVisibility(View.VISIBLE);

                FragmentTransaction fr = requireActivity().getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.FCVDonation,new DonationHistoryFragment());
                fr.addToBackStack(null);
                fr.commit();
            }
        });

        FloatingActionButton fab = view.findViewById(R.id.fab_donatefilter);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DonationDonateFilterFragment filterFragment = new DonationDonateFilterFragment();
                filterFragment.show(requireActivity().getSupportFragmentManager(), "FilterFragment");

            }
        });


        donation_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                FloatingActionButton fab_donate;
                FloatingActionButton fab_history;
                history_button.setAlpha(0.5F);
                donation_button.setAlpha(1.0F);


                fab_donate = view.findViewById(R.id.fab_donatefilter);
                fab_history = view.findViewById(R.id.fab_history);

                // Make FAB2 visible, FAB1 invisible
                fab_history.setVisibility(View.GONE);
                fab_donate.setVisibility(View.VISIBLE);

                FragmentTransaction fr = requireActivity().getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.FCVDonation,new DonationDonateFragment());
                fr.addToBackStack(null);
                fr.commit();
            }
        });

        FloatingActionButton fab1 = view.findViewById(R.id.fab_history);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.donationHistFilter);        }
        });


        if (savedInstanceState == null) {
            FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.FCVDonation, new DonationDonateFragment(), "DonationDonateFragment");
            transaction.commit();

            FloatingActionButton fab_donate;
            FloatingActionButton fab_history;
            history_button.setAlpha(0.5F);
            donation_button.setAlpha(1.0F);

            fab_donate = view.findViewById(R.id.fab_donatefilter);
            fab_history = view.findViewById(R.id.fab_history);

            // Make FAB2 visible, FAB1 invisible
            fab_donate.setVisibility(View.VISIBLE);
            fab_history.setVisibility(View.GONE);

        }

        return view;
    }
}