package com.example.aidflow;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WaterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class WaterFragment extends Fragment {

    private RadioGroup toggleGroup; // RadioGroup to toggle between pending and done projects
    private WaterViewModel waterViewModel;
    private TextView TVNoti;
    // Parameters
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public WaterFragment() {
        // Required empty public constructor
    }

    public static WaterFragment newInstance(String param1, String param2) {
        WaterFragment fragment = new WaterFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_water, container, false);
    }

    // Method to handle button clicks and other interactions
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TVNoti = view.findViewById(R.id.TVNoti);

        // Get current user ID
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        waterViewModel = new ViewModelProvider(this).get(WaterViewModel.class);

        // Fetch done reports for the current user
        waterViewModel.fetchDoneReport(userId);

        // Observe changes in the done reports
        waterViewModel.getDoneReport().observe(getViewLifecycleOwner(), reports -> {
            if (reports.isEmpty()) {
                TVNoti.setText("No solved reports");
            } else {
                WaterReport latest = reports.get(0);
                TVNoti.setText(latest.getComplaint() + " at " + latest.getAddress() + " has been solved");
            }
        });

        // Navigate to the report fragment
        Button btnReport = view.findViewById(R.id.reportbutton);
        btnReport.setOnClickListener(v ->
                Navigation.findNavController(view).navigate(R.id.waterReport)
        );

        // Handle toggling between pending and done projects
        toggleGroup = view.findViewById(R.id.toggle);

        toggleGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.pending_projects) {
                waterViewModel.fetchPendingReport(userId);
                // Switch to pending fragment
                switchFragment(new WaterRecycleViewPending());
            } else if (checkedId == R.id.done_projects) {
                // Switch to done fragment
                switchFragment(new WaterRecycleViewDone());
            }
        });

        // Default to pending fragment if no selection is made
        switchFragment(new WaterRecycleViewPending());
    }

    // Method to switch between fragments
    private void switchFragment(Fragment fragment) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainerView, fragment);
        transaction.commit();
    }
}