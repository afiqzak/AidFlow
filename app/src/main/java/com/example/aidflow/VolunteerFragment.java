package com.example.aidflow;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WaterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class VolunteerFragment extends Fragment {

    private Spinner SPState, SPRegion;
    private JSONObject statesData;
    private String state, region;

    private VolunteerViewModel volunteerViewModel;
    private boolean join;

    // Parameters for fragment initialization
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public VolunteerFragment() {
        // Required empty public constructor
    }

    // Factory method to create a new instance of this fragment
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_volunteer, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.join = false;

        // Get current user id
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        // Fetch all events that user has not joined
        volunteerViewModel = new ViewModelProvider(requireActivity()).get(VolunteerViewModel.class);
        SPState = view.findViewById(R.id.SPState);
        SPRegion = view.findViewById(R.id.SPRegion);

        try {
            // Load state/region data from JSON
            String jsonString = loadJsonFromAssets("state.json");
            statesData = new JSONObject(jsonString);

            // Fetch joined and not joined volunteers
            volunteerViewModel.fetchJoinedVolunteerID(userId);
            volunteerViewModel.fetchNotJoinedVolunteers(userId);

            // Populate state spinner
            List<String> stateNames = getStateNames(statesData);
            stateNames.add(0, "Select State");
            ArrayAdapter<String> stateAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, stateNames);
            stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            SPState.setAdapter(stateAdapter);

            state = "Select State";
            region = "Select Region";
            updateRegionSpinner(state);

            // Set up listener for state selection
            SPState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    state = parent.getItemAtPosition(position).toString();
                    // If no item selected or "Select State" is selected, disable region spinner
                    if (state.equals("Select State")) {
                        SPRegion.setEnabled(false);
                        SPRegion.setSelection(0);
                    } else {
                        // If an item is selected, update region spinner and enable it
                        updateRegionSpinner(state);
                        SPRegion.setEnabled(true);
                    }
                    Log.d("state spinner", "join " + join);
                    volunteerViewModel.filterVolunteers(state, region, join);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {}
            });

            // Set up listener for region selection
            SPRegion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    region = parent.getItemAtPosition(position).toString();
                    volunteerViewModel.filterVolunteers(state, region, join);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {}
            });

        } catch (JSONException | IOException e) {
            Toast.makeText(requireContext(), "Error loading JSON data", Toast.LENGTH_SHORT).show();
        }

        // Set up buttons and their click listeners
        MaterialButton volunteerButton = view.findViewById(R.id.Volunteer_button);
        MaterialButton upcomingButton = view.findViewById(R.id.Upcoming_button);
        MaterialButton badgesButton = view.findViewById(R.id.Badges_button);
        LinearLayout selectLayout = view.findViewById(R.id.select_layout);

        // Default view settings
        selectLayout.setVisibility(View.VISIBLE);
        volunteerButton.setAlpha(1.0F);
        upcomingButton.setAlpha(0.5F);
        badgesButton.setAlpha(0.5F);
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_button, new VolunteerProgFragment());
        transaction.commit();

        // Volunteer button click listener
        volunteerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                join = false;
                SPRegion.setSelection(0);
                SPState.setSelection(0);
                selectLayout.setVisibility(View.VISIBLE);
                volunteerButton.setAlpha(1.0F);
                upcomingButton.setAlpha(0.5F);
                badgesButton.setAlpha(0.5F);
                volunteerViewModel.filterVolunteers(state, region, join);
                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_button, new VolunteerProgFragment());
                transaction.commit();
                Log.d("join", String.valueOf(join));
            }
        });

        // Upcoming button click listener
        upcomingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                join = true;
                SPRegion.setSelection(0);
                SPState.setSelection(0);
                selectLayout.setVisibility(View.VISIBLE);
                volunteerButton.setAlpha(0.5F);
                upcomingButton.setAlpha(1.0F);
                badgesButton.setAlpha(0.5F);
                volunteerViewModel.filterVolunteers(state, region, join);
                Log.d("upcoming", "isfiltered");
                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_button, new VolunteerUpcomingFragment());
                transaction.commit();
                Log.d("upcoming", "join " + String.valueOf(join));
            }
        });

        // Badges button click listener
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
    }

    // Update region spinner based on selected state
    private void updateRegionSpinner(String selectedState) {
        try {
            JSONArray regionsArray = getRegionsForState(selectedState, statesData);
            List<String> regionNames = new ArrayList<>();
            if (regionsArray != null) {
                for (int i = 0; i < regionsArray.length(); i++) {
                    regionNames.add(regionsArray.getString(i));
                }
            }
            regionNames.add(0, "Select Region");
            ArrayAdapter<String> regionAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, regionNames);
            regionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            SPRegion.setAdapter(regionAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // Get list of state names from the JSON file
    private List<String> getStateNames(JSONObject statesData) throws JSONException {
        List<String> stateNames = new ArrayList<>();
        JSONArray statesArray = statesData.getJSONArray("states");
        for (int i = 0; i < statesArray.length(); i++) {
            JSONObject stateObject = statesArray.getJSONObject(i);
            stateNames.add(stateObject.getString("name"));
        }
        return stateNames;
    }

    // Get list of region names for a given state from the JSON file
    private JSONArray getRegionsForState(String stateName, JSONObject statesData) throws JSONException {
        JSONArray statesArray = statesData.getJSONArray("states");
        for (int i = 0; i < statesArray.length(); i++) {
            JSONObject stateObject = statesArray.getJSONObject(i);
            if (stateObject.getString("name").equals(stateName)) {
                return stateObject.getJSONArray("regions");
            }
        }
        return null; // Or throw an exception if the state is not found
    }

    // Load JSON data from assets folder
    private String loadJsonFromAssets(String filename) throws IOException {
        InputStream is = requireContext().getAssets().open(filename);
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();
        return new String(buffer, "UTF-8");
    }
}