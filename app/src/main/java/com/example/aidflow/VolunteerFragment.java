package com.example.aidflow;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WaterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class VolunteerFragment extends Fragment {

    private Spinner stateSpinner, districtSpinner;
    private Map<String, List<String>> stateDistrictMap;

    // Parameters
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public VolunteerFragment() {
        // Required empty public constructor
    }

    public static VolunteerFragment newInstance(String param1, String param2) {
        VolunteerFragment fragment = new VolunteerFragment();
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

        View view = inflater.inflate(R.layout.fragment_volunteer, container, false);

        stateSpinner = view.findViewById(R.id.state_spinner);
        districtSpinner = view.findViewById(R.id.district_spinner);

        setupStateDistrictData();

        List<String> states = new ArrayList<>(stateDistrictMap.keySet());
        ArrayAdapter<String> stateAdapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_item, states);
        stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stateSpinner.setAdapter(stateAdapter);

        stateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedState = parent.getItemAtPosition(position).toString();
                updateDistrictSpinner(selectedState);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        MaterialButton volunteerButton = view.findViewById(R.id.Volunteer_button);
        MaterialButton upcomingButton = view.findViewById(R.id.Upcoming_button);
        MaterialButton badgesButton = view.findViewById(R.id.Badges_button);
        LinearLayout selectLayout = view.findViewById(R.id.select_layout);

        selectLayout.setVisibility(View.VISIBLE);
        volunteerButton.setAlpha(1.0F);
        upcomingButton.setAlpha(0.5F);
        badgesButton.setAlpha(0.5F);
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_button, new VolunteerProgFragment());
        transaction.commit();

        volunteerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectLayout.setVisibility(View.VISIBLE);
                volunteerButton.setAlpha(1.0F);
                upcomingButton.setAlpha(0.5F);
                badgesButton.setAlpha(0.5F);
                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_button, new VolunteerProgFragment());
                transaction.commit();
            }
        });

        upcomingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectLayout.setVisibility(View.VISIBLE);
                volunteerButton.setAlpha(0.5F);
                upcomingButton.setAlpha(1.0F);
                badgesButton.setAlpha(0.5F);
                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_button, new VolunteerUpcomingFragment());
                transaction.commit();
            }
        });

        badgesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectLayout.setVisibility(View.GONE);
                volunteerButton.setAlpha(0.5F);
                upcomingButton.setAlpha(0.5F);
                badgesButton.setAlpha(1.0F);
                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_button, new VolunteerBadgesFragment());
                transaction.commit();
            }
        });

        return view;
    }

    private void setupStateDistrictData() {
        // Initialize the map with states and corresponding districts
        stateDistrictMap = new HashMap<>();
        stateDistrictMap.put("Selangor", Arrays.asList("Klang", "Damansara", "Shah Alam", "Petaling Jaya","Subang Jaya","Sabak Bernam","Gombak"));
        stateDistrictMap.put("Negeri Sembilan", Arrays.asList("Seremban", "Port Dickson", "Rembau", "Jempol"));
        stateDistrictMap.put("Johor", Arrays.asList("Johor Bahru", "Muar", "Batu Pahat", "Kluang"));
        stateDistrictMap.put("Penang", Arrays.asList("George Town", "Bayan Lepas", "Bukit Mertajam", "Butterworth"));
        stateDistrictMap.put("Perak", Arrays.asList("Ipoh", "Taiping", "Kuala Kangsar", "Teluk Intan"));
    }

    private void updateDistrictSpinner(String state) {
        // Get the district list for the selected state
        List<String> districts = stateDistrictMap.get(state);

        if (districts != null) {
            ArrayAdapter<String> districtAdapter = new ArrayAdapter<>(requireContext(),
                    android.R.layout.simple_spinner_item, districts);
            districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            districtSpinner.setAdapter(districtAdapter);
        }
    }

}