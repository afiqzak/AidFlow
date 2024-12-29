package com.example.aidflow;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DonationFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_donation, container, false);

        Button history_button = view.findViewById(R.id.History_button);
        Button donation_button = view.findViewById(R.id.Donation_button);

        FloatingActionButton fab_donate;
        FloatingActionButton fab_history;

        fab_donate = view.findViewById(R.id.fab_donatefilter);
        fab_history = view.findViewById(R.id.fab_history);

        Fragment donate = new DonationDonateFragment();
        Fragment history = new DonationHistoryFragment();

        //get arguments(histfilt) from bundle
        boolean hist = DonationFragmentArgs.fromBundle(getArguments()).getHistFilt();
        if(hist) {
            donation_button.setAlpha(1.0F);
            history_button.setAlpha(0.5F);

            // Make FAB2 visible, FAB1 invisible
            fab_donate.setVisibility(View.GONE);
            fab_history.setVisibility(View.VISIBLE);

            FragmentTransaction fr = requireActivity().getSupportFragmentManager().beginTransaction();
            fr.replace(R.id.FCVDonation, history, "DonationHistoryFragment");
            Log.d("DonationFragment", "FragmentTransaction 1 initiated");
            fr.addToBackStack(null);
            fr.commit();

            // Debug to check if fragment is properly added

            //reset the value of argument (histFilt) to false
            Bundle newArgs = new Bundle();
            newArgs.putBoolean("histFilt", false);
            setArguments(newArgs);

        } else if (savedInstanceState == null) {
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


        history_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                donation_button.setAlpha(1.0F);
                history_button.setAlpha(0.5F);

                // Make FAB2 visible, FAB1 invisible
                fab_donate.setVisibility(View.GONE);
                fab_history.setVisibility(View.VISIBLE);

                FragmentTransaction fr = requireActivity().getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.FCVDonation, history, "DonationHistoryFragment");
                Log.d("DonationFragment", "FragmentTransaction 2 initiated");
                fr.addToBackStack(null);
                fr.commit();

                // Debug to check if fragment is properly added
                Fragment fragment = requireActivity().getSupportFragmentManager().findFragmentByTag("DonationHistoryFragment");
                if (fragment != null) {
                    Log.d("DonationFragment", "DonationHistoryFragment added successfully!");
                }
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
                fr.replace(R.id.FCVDonation, donate, "DonationDonateFragment");
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

