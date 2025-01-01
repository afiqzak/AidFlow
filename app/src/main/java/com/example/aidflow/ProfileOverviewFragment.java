package com.example.aidflow;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileOverviewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileOverviewFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileOverviewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OverviewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileOverviewFragment newInstance(String param1, String param2) {
        ProfileOverviewFragment fragment = new ProfileOverviewFragment();
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
        return inflater.inflate(R.layout.fragment_profile_overview, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //get current user id
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        //observe view model for real time updates on user data
        UserViewModel userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
        userViewModel.fetchUserData(userId);

        userViewModel.getUser().observe(getViewLifecycleOwner(), user -> {
            if (user != null) {
                // Update UI with user data
                Log.d("Fragment", user.toString());
                bindData(view, user);
            } else {
                // Handle the case where user data is null (e.g., show an error message)
                Log.d("Fragment", "User is null");
                TextView TVUsername = view.findViewById(R.id.TVAmounrDonate);
                TVUsername.setText("user not found");
            }
        });
    }

    private void bindData(View v, User user){
        TextView TVAmountDonate = v.findViewById(R.id.TVAmounrDonate);
        TextView TVAmountTime = v.findViewById(R.id.TVAmounrTime);
        TextView TVAmountReport = v.findViewById(R.id.TVAmounrReport);

        TVAmountDonate.setText("RM " + user.getTotalDonate());
        TVAmountTime.setText(user.getVolunteerHours() + " hrs");
        TVAmountReport.setText(String.valueOf(user.getReportSubmitted()));
    }
}