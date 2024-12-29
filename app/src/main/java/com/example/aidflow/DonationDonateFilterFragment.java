package com.example.aidflow;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DonationDonateFilterFragment extends DialogFragment {


    private CheckBox checkBoxOrphanage, checkBoxDisasterRelief, checkBoxCommunityProject,
            checkBoxHealthCare, checkBoxCleanWater, checkBoxHighPriority, checkBoxModerate, checkBoxLowPriority;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_donation_donate_filter, container, false);

        checkBoxOrphanage = view.findViewById(R.id.checkBox_orphanage);
        checkBoxDisasterRelief = view.findViewById(R.id.checkBox_disasterRelief);
        checkBoxCommunityProject = view.findViewById(R.id.checkBox_communityProject);
        checkBoxHealthCare = view.findViewById(R.id.checkBox_healthCare);
        checkBoxCleanWater = view.findViewById(R.id.checkBox_cleanWater);
        checkBoxHighPriority = view.findViewById(R.id.checkBox_highPriority);
        checkBoxLowPriority = view.findViewById(R.id.checkBox_lowPriority);
        checkBoxModerate = view.findViewById(R.id.checkBox_moderate);

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        Log.d("DonationDonateFilterFragment", "onDismiss called");

        // Collect selected filters
        Set<String> selectedUrgencies = new HashSet<>();
        Set<String> selectedProjects = new HashSet<>();

        if (checkBoxHighPriority.isChecked()) selectedUrgencies.add("high");
        if (checkBoxModerate.isChecked()) selectedUrgencies.add("moderate");
        if (checkBoxLowPriority.isChecked()) selectedUrgencies.add("low");

        if (checkBoxOrphanage.isChecked()) selectedProjects.add("Orphanage");
        if (checkBoxDisasterRelief.isChecked()) selectedProjects.add("Disaster Relief");
        if (checkBoxCommunityProject.isChecked()) selectedProjects.add("Community Project");
        if (checkBoxHealthCare.isChecked()) selectedProjects.add("Health Care");
        if (checkBoxCleanWater.isChecked()) selectedProjects.add("Clean Water");

        //Fragment fragment = getActivity().getSupportFragmentManager().findFragmentByTag("DonationDonateFragment");
        Fragment fragment = requireActivity().getSupportFragmentManager().findFragmentByTag("DonationDonateFragment");
        if (fragment instanceof DonationDonateFragment) {
            ((DonationDonateFragment) fragment).setFilterCriteria(selectedUrgencies, selectedProjects);
        } else {
            Log.d("DonationDonateFilterFragment", "Fragment is no instance of DonationDonateFragment");
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        // Set dialog window attributes for a floating effect
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();

            //test
            params.gravity = Gravity.BOTTOM; // Positions the layout at the bottom
            params.y = 500; // Offset from the bottom (in pixels)

            getDialog().getWindow().setAttributes(params);

            getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        }
    }
}

