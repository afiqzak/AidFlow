package com.example.aidflow;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileBadgesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileBadgesFragment extends Fragment {

    // List to store ProfileBadges objects
    ArrayList<ProfileBadges> badges = new ArrayList<>();
    private User user;
    private ProfileBadgesAdaptor adapter;

    // Fragment initialization parameters
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public ProfileBadgesFragment() {
        // Required empty public constructor
    }

    /**
     * Factory method to create a new instance of this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileBadgesFragment.
     */
    public static ProfileBadgesFragment newInstance(String param1, String param2) {
        ProfileBadgesFragment fragment = new ProfileBadgesFragment();
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
        return inflater.inflate(R.layout.fragment_profile_badges, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpBadges();

        // Get current user id
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        // Observe ViewModel for real-time updates on user data
        UserViewModel userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
        userViewModel.fetchUserData(userId);

        userViewModel.getUser().observe(getViewLifecycleOwner(), user -> {
            if (user != null) {
                // Update UI with user data
                Log.d("Fragment", user.toString());
                adapter = new ProfileBadgesAdaptor(getContext(), badges, user);
                RecyclerView recyclerView = view.findViewById(R.id.RVPBadges);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            } else {
                // Handle the case where user data is null (e.g., show an error message)
                Log.d("Fragment", "User is null");
            }
        });
    }

    // Method to set up the list of badges
    private void setUpBadges() {
        badges.add(new ProfileBadges("Complete 7 total volunteer hours", "hour >= 7", R.drawable.badge_7hours));
        badges.add(new ProfileBadges("Complete 12 total volunteer hours", "hour >= 12", R.drawable.badge_12hours));
        badges.add(new ProfileBadges("Complete 24 total volunteer hours", "hour >= 24", R.drawable.badge_1day));
        badges.add(new ProfileBadges("Complete 168 total volunteer hours", "hour >= 168", R.drawable.badge_1week));
        badges.add(new ProfileBadges("Complete 720 total volunteer hours", "hour >= 720", R.drawable.badge_1month));
        badges.add(new ProfileBadges("Submitted 10 reports", "report >= 10", R.drawable.badge_10reports));
        badges.add(new ProfileBadges("Make a total donation of RM50", "donation >= 50", R.drawable.badge_rm50donate));
    }
}