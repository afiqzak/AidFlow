package com.example.aidflow;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

public class DonationDonateFilterFragment extends DialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_donation_donate_filter, container, false);
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

