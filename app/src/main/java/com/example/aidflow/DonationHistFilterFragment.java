package com.example.aidflow;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class DonationHistFilterFragment extends Fragment {

    private RadioGroup durationRadioGroup;
    private DonationViewModel donationViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_donation_hist_filter, container, false);

        // Initialize UI components
        durationRadioGroup = view.findViewById(R.id.duration_radio_group);
        ImageView close_button = view.findViewById(R.id.close_icon);

        // Initialize ViewModel
        donationViewModel = new ViewModelProvider(requireActivity()).get(DonationViewModel.class);

        // Observe selected filter and update UI accordingly
        donationViewModel.getSelectedFilter().observe(getViewLifecycleOwner(), selectedFilter -> {
            if (selectedFilter != null) {
                durationRadioGroup.check(selectedFilter);
            }
        });

        // Get current user ID
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        // Set click listener for close button
        close_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Reset ViewModel state if needed
                donationViewModel.fetchDonationHistory(userId, 0); // Reset selected filter value
                donationViewModel.getFromHistFilter().setValue(true);
                donationViewModel.getSelectedFilter().setValue(R.id.rb_30_days);

                // Go back in the fragment transaction stack
                if (getFragmentManager() != null) {
                    getFragmentManager().popBackStack();
                }
            }
        });

        // Set click listener for apply filter button
        Button applyFilter_button = view.findViewById(R.id.apply_filter_button);
        applyFilter_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get selected duration and update ViewModel
                int selectedDurationId = durationRadioGroup.getCheckedRadioButtonId();
                int selectedDuration = getSelectedDuration(selectedDurationId, v);

                // Reset ViewModel state if needed
                donationViewModel.fetchDonationHistory(userId, selectedDuration); // Reset selected filter value
                donationViewModel.getFromHistFilter().setValue(true);

                // Go back in the fragment transaction stack
                if (getFragmentManager() != null) {
                    getFragmentManager().popBackStack();
                }

                Log.d("DonationHistFilterFragment", "fromHistFilter changed:" + donationViewModel.getFromHistFilter().getValue());
            }
        });

        return view;
    }

    // Get selected duration based on selected radio button ID
    private int getSelectedDuration(int selectedDurationId, View view) {
        donationViewModel.getSelectedFilter().setValue(selectedDurationId);
        if (selectedDurationId == R.id.rb_30_days) {
            return 30;
        } else if (selectedDurationId == R.id.rb_60_days) {
            return 60;
        } else if (selectedDurationId == R.id.rb_90_days) {
            return 90;
        } else if (selectedDurationId == R.id.rb_1_year) {
            return 365;
        }
        return selectedDurationId;
    }
}

