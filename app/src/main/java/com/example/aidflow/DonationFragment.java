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


public class DonationFragment extends Fragment {


    private DonationAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view =  inflater.inflate(R.layout.fragment_donation, container, false);


        Button history_button = view.findViewById(R.id.History_button);
        Button donation_button = (Button) view.findViewById(R.id.Donation_button);

        FloatingActionButton fab_donate;
        FloatingActionButton fab_history;

        fab_donate = view.findViewById(R.id.fab_donatefilter);
        fab_history = view.findViewById(R.id.fab_history);

        Fragment donate = new DonationDonateFragment();
        Fragment history = new DonationHistoryFragment();

        donation_button.setAlpha(0.5F);
        history_button.setAlpha(1.0F);

        fab_history.setVisibility(View.GONE);
        fab_donate.setVisibility(View.VISIBLE);

        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.FCVDonation, donate);
        transaction.commit();

        history_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                donation_button.setAlpha(1.0F);
                history_button.setAlpha(0.5F);

                // Make FAB2 visible, FAB1 invisible
                fab_donate.setVisibility(View.GONE);
                fab_history.setVisibility(View.VISIBLE);

                FragmentTransaction fr = requireActivity().getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.FCVDonation,history);
                fr.addToBackStack(null);
                fr.commit();
            }
        });

        FloatingActionButton fab = view.findViewById(R.id.fab_donatefilter);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DonationDonateFilterFragment floatingLayout = new DonationDonateFilterFragment();
                floatingLayout.show(requireActivity().getSupportFragmentManager().beginTransaction(), "FloatingLayout");


            }
        });


        donation_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                history_button.setAlpha(1.0F);
                donation_button.setAlpha(0.5F);

                // Make FAB2 visible, FAB1 invisible
                fab_history.setVisibility(View.GONE);
                fab_donate.setVisibility(View.VISIBLE);

                FragmentTransaction fr = requireActivity().getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.FCVDonation,donate);
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

//        if (savedInstanceState == null) {
//            donation_button.setAlpha(0.5F);
//            history_button.setAlpha(1.0F);
//
//            fab_history.setVisibility(View.GONE);
//            fab_donate.setVisibility(View.VISIBLE);
//
//            FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
//            transaction.add(R.id.FCVDonation, donate);
//            transaction.commit();
//        }


        return view;
    }
}