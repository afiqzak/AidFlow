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

public class VolunteerProgFragment extends Fragment {
    private VolunteerViewModel volunteerViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_volunteer_prog, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Get current user ID from FirebaseAuth
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        // Initialize RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_volunteer);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Observe ViewModel
        volunteerViewModel = new ViewModelProvider(requireActivity()).get(VolunteerViewModel.class);
        volunteerViewModel.fetchNotJoinedVolunteers(userId);

        // Observe filtered not joined volunteers
        volunteerViewModel.getFilteredNotJoinedVolunteers().observe(getViewLifecycleOwner(), notJoinedVolunteers -> {
            if (notJoinedVolunteers != null) {
                Log.d("volunteer", String.valueOf(notJoinedVolunteers.size()));
                // Attach adapter to RecyclerView
                VolunteerAdapter adapter = new VolunteerAdapter(notJoinedVolunteers, getContext(), volunteerViewModel);
                recyclerView.setAdapter(adapter);
            } else {
                // Handle the case where user data is null (e.g., show an error message)
                Toast.makeText(requireContext(), "No data found", Toast.LENGTH_SHORT).show();
            }
        });
    }
}