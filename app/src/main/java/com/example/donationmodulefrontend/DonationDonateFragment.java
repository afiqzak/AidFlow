package com.example.donationmodulefrontend;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


public class DonationDonateFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_donation_donate, container, false);

//        Button donate_Button = view.findViewById(R.id.donateButton_donate);
//        donate_Button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getContext(), "Donate Request Sent", Toast.LENGTH_LONG).show();
//                FragmentTransaction fr = requireActivity().getSupportFragmentManager().beginTransaction();
//                fr.replace(R.id.fragment_container,new DonationFragment());
//                fr.addToBackStack(null);
//                fr.commit();
//            }
//        });
//


        ImageView back_button = view.findViewById(R.id.back_icon);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = requireActivity().getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container,new main_donation_Fragment());
                fr.addToBackStack(null);
                fr.commit();
            }
        });

        LinearLayout donationButton_donate = view.findViewById(R.id.donationButton_donate);
        donationButton_donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Donate Request Sent", Toast.LENGTH_LONG).show();
                FragmentTransaction fr = requireActivity().getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container,new main_donation_Fragment());
                fr.addToBackStack(null);
                fr.commit();
            }
        });

        return view;
    }
}