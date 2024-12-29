package com.example.aidflow;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.List;

public class DonationHistFilterFragment extends Fragment {

    private RadioGroup durationRadioGroup;


    ViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_donation_hist_filter, container, false);
        durationRadioGroup = view.findViewById(R.id.duration_radio_group);
        ImageView close_button = view.findViewById(R.id.close_icon);

        viewModel = new ViewModelProvider(requireActivity()).get(ViewModel.class);

        close_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                durationRadioGroup.clearCheck();

                // Optionally, reset the ViewModel state if needed
                viewModel.getIsHistoryFiltered().setValue(false);
                viewModel.getSelectedHistFilter().setValue(0);  // Reset selected filter value

                //set the argument value(histFilt) to true
                //to be used igi n DonationFragment
                boolean hist = true;
                DonationHistFilterFragmentDirections.HistFiltDonate action = DonationHistFilterFragmentDirections.histFiltDonate();
                action.setHistFilt(hist);
                Navigation.findNavController(v).navigate(action);
            }
        });

        Button applyFilter_button = view.findViewById(R.id.apply_filter_button);
        applyFilter_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedDurationId = durationRadioGroup.getCheckedRadioButtonId();
                int selectedDuration = getSelectedDuration(selectedDurationId);

                Fragment fragment = requireActivity().getSupportFragmentManager().findFragmentByTag("DonationHistoryFragment");
                if (fragment != null) {
                    Log.d("DonationHistFilterFragment", "Fragment class: " + fragment.getClass().getSimpleName());
                    if (fragment instanceof DonationHistoryFragment) {
                        Log.d("DonationHistFilterFragment", "Fragment is part of instance of DonationHistoryFragment");
                        ((DonationHistoryFragment) fragment).setHistFilterCriteria(selectedDuration);
                    } else {
                        Log.d("DonationHistFilterFragment", "Fragment not instance of DonationHistoryFragment");
                    }
                } else {
                    List<Fragment> fragments = requireActivity().getSupportFragmentManager().getFragments();
                    for (Fragment frag : fragments) {
                        Log.d("FragmentList", "Fragment in the manager: " + frag.getClass().getSimpleName());
                    }
                    Log.d("DonationHistFilterFragment", "Fragment is null");
                }

                viewModel.getIsHistoryFiltered().setValue(true);
                viewModel.getSelectedHistFilter().setValue(selectedDuration);

                //untuk pergi balik kt DonationFragment tapi keluar part history
                boolean hist = true;
                DonationHistFilterFragmentDirections.HistFiltDonate action = DonationHistFilterFragmentDirections.histFiltDonate();
                action.setHistFilt(hist);
                Navigation.findNavController(v).navigate(action);
            }
        });
        return view;
    }

    private int getSelectedDuration(int selectedDurationId) {
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

