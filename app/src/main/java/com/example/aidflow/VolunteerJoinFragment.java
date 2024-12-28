package com.example.aidflow;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;


public class VolunteerJoinFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_volunteer_join, container, false);

        ImageView back_button = view.findViewById(R.id.back_icon_volunteer);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager() != null) {
                    getFragmentManager().popBackStack(); // Go back in the fragment transaction stack
                }
            }
        });

        MaterialButton joinButton = view .findViewById(R.id.join_button);
        joinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "You joined the Volunteer", Toast.LENGTH_LONG).show();
                if (getFragmentManager() != null) {
                    getFragmentManager().popBackStack(); // Go back in the fragment transaction stack
                }
            }
        });

        return view;
    }
}