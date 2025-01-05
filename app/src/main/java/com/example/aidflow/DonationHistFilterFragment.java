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

        durationRadioGroup = view.findViewById(R.id.duration_radio_group);
        ImageView close_button = view.findViewById(R.id.close_icon);

        donationViewModel = new ViewModelProvider(requireActivity()).get(DonationViewModel.class);

        donationViewModel.getSelectedFilter().observe(getViewLifecycleOwner(), selectedFilter -> {
            if (selectedFilter != null) {
                durationRadioGroup.check(selectedFilter);
            }
        });

        //get current user id
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        close_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Optionally, reset the ViewModel state if needed
                donationViewModel.fetchDonationHistory(userId, 0);// Reset selected filter value
                donationViewModel.getFromHistFilter().setValue(true);
                donationViewModel.getSelectedFilter().setValue(R.id.rb_30_days);

                if (getFragmentManager() != null) {
                    getFragmentManager().popBackStack(); // Go back in the fragment transaction stack
                }
            }
        });

        Button applyFilter_button = view.findViewById(R.id.apply_filter_button);
        applyFilter_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedDurationId = durationRadioGroup.getCheckedRadioButtonId();
                int selectedDuration = getSelectedDuration(selectedDurationId, v);

                // Optionally, reset the ViewModel state if needed
                donationViewModel.fetchDonationHistory(userId, selectedDuration);// Reset selected filter value
                donationViewModel.getFromHistFilter().setValue(true);

                if (getFragmentManager() != null) {
                    getFragmentManager().popBackStack(); // Go back in the fragment transaction stack
                }

                Log.d("DonationHistFilterFragment", "fromHistFilter changed:" + donationViewModel.getFromHistFilter().getValue());
            }
        });

        return view;
    }

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

