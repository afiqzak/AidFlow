package com.example.aidflow;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_donation_hist_filter, container, false);

        durationRadioGroup = view.findViewById(R.id.duration_radio_group);

        ImageView close_button = view.findViewById(R.id.close_icon);

        close_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
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
                String selectedDuration = getSelectedDuration(selectedDurationId);

                Fragment fragment = requireActivity().getSupportFragmentManager().findFragmentByTag("DonationHistoryFragment");
                if (fragment instanceof DonationHistoryFragment) {
                    ((DonationHistoryFragment) fragment).setHistFilterCriteria(selectedDuration);

                    FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                    transaction.detach(fragment).attach(fragment).commit();

                    NavController navController = Navigation.findNavController(requireView());
                    navController.popBackStack();
                }

                //untuk pergi balik kt DonationFragment tapi keluar part history
//                boolean hist = true;
//                DonationHistFilterFragmentDirections.HistFiltDonate action = DonationHistFilterFragmentDirections.histFiltDonate();
//                action.setHistFilt(hist);
//                Navigation.findNavController(v).navigate(action);
            }
        });

        return view;
    }

    private String getSelectedDuration(int selectedDurationId) {
        if (selectedDurationId == R.id.rb_30_days) {
            return "30";
        } else if (selectedDurationId == R.id.rb_60_days) {
            return "60";
        } else if (selectedDurationId == R.id.rb_90_days) {
            return "90";
        } else if (selectedDurationId == R.id.rb_1_year) {
            return "365";
        } else {
            return "No duration selected";
        }
    }
}

