package com.example.aidflow;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class VolunteerUpcomingAdapter extends RecyclerView.Adapter<VolunteerUpcomingAdapter.UpcomingViewHolder> {

    private final List<Volunteer> upcomingList;
    private Context context;
    private VolunteerViewModel volunteerViewModel;

    public VolunteerUpcomingAdapter(List<Volunteer> upcomingList, Context context, VolunteerViewModel volunteerViewModel) {
        this.upcomingList = upcomingList;
        this.context = context;
        this.volunteerViewModel = volunteerViewModel;
    }

    @NonNull
    @Override
    public UpcomingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_volunteer_upcoming_recycleview, parent, false);
        return new UpcomingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UpcomingViewHolder holder, int position) {
        Volunteer upcoming = upcomingList.get(position);

        // Bind data to views
        holder.volunteerName_upcoming.setText(upcoming.getEventTitle());
        holder.dueDate_upcoming.setText(upcoming.getEventDate());
        holder.address_upcoming.setText(upcoming.getLocation());
        holder.contactNum_upcoming.setText(upcoming.getContactPIC());
        holder.organizer_upcoming.setText(upcoming.getPIC());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.volunteerDetails);
                volunteerViewModel.getSelectedVolunteer().setValue(upcoming);
            }
        });
    }

    @Override
    public int getItemCount() {
        return upcomingList.size();
    }

    static class UpcomingViewHolder extends RecyclerView.ViewHolder {
        TextView volunteerName_upcoming,dueDate_upcoming,address_upcoming,contactNum_upcoming,organizer_upcoming;
        CardView cardView;

        public UpcomingViewHolder(@NonNull View itemView) {
            super(itemView);
            volunteerName_upcoming = itemView.findViewById(R.id.TVUpcomingTitle);
            dueDate_upcoming = itemView.findViewById(R.id.TVUpcomingDate);
            address_upcoming = itemView.findViewById(R.id.TVUpcomingLoc);
            contactNum_upcoming = itemView.findViewById(R.id.TVUpcomingContact);
            organizer_upcoming = itemView.findViewById(R.id.organizer_upcoming);
            cardView = itemView.findViewById(R.id.upcoming_cardView);
        }
    }

}
