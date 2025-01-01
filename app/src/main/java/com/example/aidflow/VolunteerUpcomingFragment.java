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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class VolunteerUpcomingFragment extends Fragment {

    private VolunteerViewModel volunteerViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_volunteer_upcoming, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        volunteerViewModel = new ViewModelProvider(requireActivity()).get(VolunteerViewModel.class);

        // Initialize RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_upcoming);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        volunteerViewModel.getFilteredJoinedVolunteers().observe(getViewLifecycleOwner(), joinedVolunteers -> {
            if (joinedVolunteers != null) {
                Log.d("Fragment", joinedVolunteers.toString());
                // Attach adapter to RecyclerView
                VolunteerUpcomingAdapter adapter = new VolunteerUpcomingAdapter(joinedVolunteers,getContext(), volunteerViewModel);
                recyclerView.setAdapter(adapter);
            } else {
                // Handle the case where user data is null (e.g., show an error message)
                Toast.makeText(requireContext(), "No data found", Toast.LENGTH_SHORT).show();
            }
        });
    }
}