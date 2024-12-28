package com.example.aidflow;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;


public class VolunteerBadgesFragment extends Fragment {

    private CircularProgressBar circularProgressBar;
    private TextView progressText_numerator,progressText_denominator;
    private ImageView badgeImage;

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

        circularProgressBar = view.findViewById(R.id.circularProgressBar);
        progressText_numerator = view.findViewById(R.id.progressText_numerator);
        progressText_denominator = view.findViewById(R.id.progressText_denominator);
        badgeImage = view.findViewById(R.id.badgeImage);

        // Example data
        int totalHours = 24;
        int completedHours = 14;

        // Set progress bar
        circularProgressBar.setProgressWithAnimation((completedHours / (float) totalHours) * 100,  1500L);

        // Set text
        progressText_numerator.setText(completedHours+ " ");
        progressText_denominator.setText("/"+ totalHours +"hours");

        // Change badge based on progress
        if (completedHours <= 7) {
            badgeImage.setImageResource(R.drawable.badge_sevenhours);
        } else if (completedHours > 7 && completedHours <= 12) {
            badgeImage.setImageResource(R.drawable.badge_twelvehours);
        } else if (completedHours > 12 && completedHours <= 24) {
            badgeImage.setImageResource(R.drawable.badge_oneday);
        } else if (completedHours > 24 && completedHours <= 168) {
            badgeImage.setImageResource(R.drawable.badge_oneweek);
        } else if (completedHours > 168 && completedHours <= 672) {
            badgeImage.setImageResource(R.drawable.badge_onemonth);
        } else {
            badgeImage.setImageResource(R.drawable.badge_sevenhours);
        }

    }
}