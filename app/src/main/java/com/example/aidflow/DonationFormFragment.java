package com.example.aidflow;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


public class DonationFormFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_donation_form, container, false);

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


        ImageView back_button = view.findViewById(R.id.IVBack);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager() != null) {
                    getFragmentManager().popBackStack(); // Go back in the fragment transaction stack
                }
            }
        });

        Button donationButton_donate = view.findViewById(R.id.BtnDonate);
        donationButton_donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Donate Request Sent", Toast.LENGTH_LONG).show();
                if (getFragmentManager() != null) {
                    getFragmentManager().popBackStack(); // Go back in the fragment transaction stack
                }
            }
        });

        return view;
    }
}