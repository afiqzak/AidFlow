package com.example.aidflow;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

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

    // CheckBox variables for different donation categories and urgency levels
    private CheckBox checkBoxOrphanage, checkBoxDisasterRelief, checkBoxCommunityProject,
            checkBoxHealthCare, checkBoxCleanWater, checkBoxHighPriority, checkBoxModerate, checkBoxLowPriority;

    // ViewModel for managing donation data
    private DonationViewModel donationViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_donation_donate_filter, container, false);

        // Initialize the ViewModel
        donationViewModel = new ViewModelProvider(requireActivity()).get(DonationViewModel.class);

        // Find CheckBox views by their IDs
        checkBoxOrphanage = view.findViewById(R.id.checkBox_orphanage);
        checkBoxDisasterRelief = view.findViewById(R.id.checkBox_disasterRelief);
        checkBoxCommunityProject = view.findViewById(R.id.checkBox_communityProject);
        checkBoxHealthCare = view.findViewById(R.id.checkBox_healthCare);
        checkBoxCleanWater = view.findViewById(R.id.checkBox_cleanWater);
        checkBoxHighPriority = view.findViewById(R.id.checkBox_highPriority);
        checkBoxLowPriority = view.findViewById(R.id.checkBox_lowPriority);
        checkBoxModerate = view.findViewById(R.id.checkBox_moderate);

        return view;
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        Log.d("DonationDonateFilterFragment", "onDismiss called");

        // Collect selected filters
        Set<String> selectedUrgencies = new HashSet<>();
        Set<String> selectedCategorys = new HashSet<>();

        // Add selected urgency levels to the set
        if (checkBoxHighPriority.isChecked()) selectedUrgencies.add("High Priority");
        if (checkBoxModerate.isChecked()) selectedUrgencies.add("Moderate");
        if (checkBoxLowPriority.isChecked()) selectedUrgencies.add("Low Priority");

        // Add selected categories to the set
        if (checkBoxOrphanage.isChecked()) selectedCategorys.add("Orphanage");
        if (checkBoxDisasterRelief.isChecked()) selectedCategorys.add("Disaster Relief");
        if (checkBoxCommunityProject.isChecked()) selectedCategorys.add("Community Project");
        if (checkBoxHealthCare.isChecked()) selectedCategorys.add("Health Care");
        if (checkBoxCleanWater.isChecked()) selectedCategorys.add("Clean Water");

        // Apply filters to the donation list
        donationViewModel.filterDonations(selectedUrgencies, selectedCategorys);

        // Replace the current fragment with DonationDonateFragment
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.FCVDonation, new DonationDonateFragment());
        transaction.commit();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Set dialog window attributes for a floating effect
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();

            // Position the dialog at the bottom with an offset
            params.gravity = Gravity.BOTTOM;
            params.y = 500;

            getDialog().getWindow().setAttributes(params);

            // Set the background to transparent
            getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        }
    }
}

