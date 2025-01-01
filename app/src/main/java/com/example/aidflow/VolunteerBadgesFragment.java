package com.example.aidflow;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.util.ArrayList;


public class VolunteerBadgesFragment extends Fragment {

    ArrayList<ProfileBadges> badges = new ArrayList<>();
    private CircularProgressBar circularProgressBar;
    private TextView progressText_numerator,progressText_denominator;
    private ImageView badgeImage;
    private VolunteerBadgesAdaptor adaptor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_volunteer_badges, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUpBadges();

        //get current user id
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        //observe view model for real time updates on user data
        UserViewModel userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
        userViewModel.fetchUserData(userId);

        userViewModel.getUser().observe(getViewLifecycleOwner(), user -> {
            if (user != null) {
                // Update UI with user data
                Log.d("Fragment", user.toString());

                setUpProgress(user, badges, view);

                adaptor = new VolunteerBadgesAdaptor(getContext(), badges, user);
                RecyclerView recyclerView = view.findViewById(R.id.RVVBadges);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
                recyclerView.setLayoutManager(gridLayoutManager);
                recyclerView.setAdapter(adaptor);
            } else {
                // Handle the case where user data is null (e.g., show an error message)
                Log.d("Fragment", "User is null");
            }
        });


    }

    private void setUpBadges(){
        badges.add(new ProfileBadges("Complete 7 total volunteer hours", "hour >= 7", R.drawable.badge_7hours));
        badges.add(new ProfileBadges("Complete 12 total volunteer hours", "hour >= 12", R.drawable.badge_12hours));
        badges.add(new ProfileBadges("Complete 24 total volunteer hours", "hour >= 24", R.drawable.badge_1day));
        badges.add(new ProfileBadges("Complete 168 total volunteer hours", "hour >= 168", R.drawable.badge_1week));
        badges.add(new ProfileBadges("Complete 720 total volunteer hours", "hour >= 720", R.drawable.badge_1month));
    }

    private void setUpProgress(User user, ArrayList<ProfileBadges> badges, View view){
        circularProgressBar = view.findViewById(R.id.circularProgressBar);
        progressText_numerator = view.findViewById(R.id.progressText_numerator);
        progressText_denominator = view.findViewById(R.id.progressText_denominator);
        badgeImage = view.findViewById(R.id.badgeImage);

        int userHours = user.getVolunteerHours();

        for (ProfileBadges badge : badges) {
            String condition = badge.getCondition();
            String[] parts = condition.split(" >= ");
            int value = Integer.parseInt(parts[1]);

            if (userHours < value) {
                // User has not yet achieved this badge
                badgeImage.setImageResource(badge.getImg()); // Display the badge image
                badgeImage.setAlpha(userHours/(float)value);
                int progress = (int) ((double) userHours / value * 100); // Calculate progress percentage
                progressText_numerator.setText(userHours + " ");
                progressText_denominator.setText("/" + value + " hours");
                circularProgressBar.setProgress(progress); // Set progress bar value
                return; // Stop further iteration as this is the active badge
            }
        }

        // If all badges are achieved, handle the case here (e.g., set progress to max or hide the progress bar)
        badgeImage.setImageResource(R.drawable.badge_1month); // Show a "completed" badge
        circularProgressBar.setProgress(100);

    }
}