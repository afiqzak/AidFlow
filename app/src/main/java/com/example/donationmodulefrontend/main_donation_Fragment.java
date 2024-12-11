package com.example.donationmodulefrontend;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class main_donation_Fragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view =  inflater.inflate(R.layout.fragment_main_donation_, container, false);

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
                fr.replace(R.id.fragment_donatehistory,new HistoryFragment());
                fr.addToBackStack(null);
                fr.commit();
            }
        });

        FloatingActionButton fab = view.findViewById(R.id.fab_donatefilter);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DonateFilterFragment floatingLayout = new DonateFilterFragment();
                floatingLayout.show(requireActivity().getSupportFragmentManager().beginTransaction(), "FloatingLayout");


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
                fr.replace(R.id.fragment_donatehistory,new DonationFragment());
                fr.addToBackStack(null);
                fr.commit();
            }
        });

        FloatingActionButton fab1 = view.findViewById(R.id.fab_history);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = requireActivity().getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container,new HistoryFilterFragment());
                fr.addToBackStack(null);
                fr.commit();            }
        });


        if (savedInstanceState == null) {
            FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_donatehistory, new DonationFragment());
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