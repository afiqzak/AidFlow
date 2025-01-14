package com.example.aidflow;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.util.List;

class VolunteerAdapter extends RecyclerView.Adapter<VolunteerAdapter.VolunteerViewHolder> {

    private List<Volunteer> volunteerList;
    private Context context;
    private VolunteerViewModel volunteerViewModel;

    // Constructor to initialize the adapter with the volunteer list, context, and ViewModel
    public VolunteerAdapter(List<Volunteer> volunteerList, Context context, VolunteerViewModel volunteerViewModel) {
        this.volunteerList = volunteerList;
        this.context = context;
        this.volunteerViewModel = volunteerViewModel;
    }

    @NonNull
    @Override
    public VolunteerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each item in the RecyclerView
        View view = LayoutInflater.from(context).inflate(R.layout.single_volunteer_recycleview, parent, false);
        return new VolunteerAdapter.VolunteerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VolunteerViewHolder holder, int position) {
        // Get the current volunteer from the list
        Volunteer volunteer = volunteerList.get(position);

        // Calculate the percentage of volunteers applied
        double percentage = (double) volunteer.getNumOfVolunteersApplied() / volunteer.getNumOfVolunteersNeeded() * 100;

        // Bind data to views
        holder.volunteerName.setText(volunteer.getEventTitle());
        holder.districtName.setText(volunteer.getRegion());
        holder.dueDate_volunteer.setText(volunteer.getEventDate());
        holder.volunteerProgress.setText(volunteer.getNumOfVolunteersApplied() + "/" + volunteer.getNumOfVolunteersNeeded());
        holder.volunteerProgressBar.setProgress((int) Math.round(percentage));

        // Set click listener for the card view
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Update the selected volunteer in the ViewModel and navigate to the details screen
                volunteerViewModel.getSelectedVolunteer().setValue(volunteer);
                Navigation.findNavController(v).navigate(R.id.volunteerDetails);
            }
        });
    }

    @Override
    public int getItemCount() {
        // Return the total number of items in the list
        return volunteerList.size();
    }

    // ViewHolder class to hold references to the views in each item
    static class VolunteerViewHolder extends RecyclerView.ViewHolder {
        TextView volunteerName, districtName, dueDate_volunteer, volunteerProgress;
        ProgressBar volunteerProgressBar;
        CardView cardView;

        public VolunteerViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initialize the views
            volunteerName = itemView.findViewById(R.id.TVEventTitle);
            volunteerProgressBar = itemView.findViewById(R.id.volunteerProgressBar);
            volunteerProgress = itemView.findViewById(R.id.volunteerProgress);
            dueDate_volunteer = itemView.findViewById(R.id.TVEventDate);
            districtName = itemView.findViewById(R.id.districtName);
            cardView = itemView.findViewById(R.id.volunteer_cardView);
        }
    }

}
