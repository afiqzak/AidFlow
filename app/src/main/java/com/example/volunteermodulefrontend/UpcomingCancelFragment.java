package com.example.volunteermodulefrontend;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class UpcomingCancelFragment extends Fragment {

   @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

       View view = inflater.inflate(R.layout.fragment_upcoming_cancel, container, false);

       ImageView back_button = view.findViewById(R.id.back_icon_upcoming);
       back_button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               FragmentTransaction fr = requireActivity().getSupportFragmentManager().beginTransaction();
               fr.replace(R.id.fragment_container,new MainVolunteerFragment());
               fr.addToBackStack(null);
               fr.commit();
           }
       });

       MaterialButton cancelButton = view .findViewById(R.id.cancel_button_upcoming);
       cancelButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Toast.makeText(getContext(), "You canceled to join the Volunteer", Toast.LENGTH_LONG).show();
               FragmentTransaction fr = requireActivity().getSupportFragmentManager().beginTransaction();
               fr.replace(R.id.fragment_container,new MainVolunteerFragment());
               fr.addToBackStack(null);
               fr.commit();
           }
       });

       return view;
    }
}