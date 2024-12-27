package com.example.volunteermodulefrontend;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class UpcomingAdapter extends RecyclerView.Adapter<UpcomingAdapter.UpcomingViewHolder> {

    private final List<Upcoming> upcomingList;
    private Context context;

    public UpcomingAdapter(List<Upcoming> upcomingList, Context context) {
        this.upcomingList = upcomingList;
        this.context = context;
    }

    @NonNull
    @Override
    public UpcomingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.upcoming_recycleview, parent, false);
        return new UpcomingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UpcomingViewHolder holder, int position) {
        Upcoming upcoming = upcomingList.get(position);

        // Bind data to views
        holder.volunteerName_upcoming.setText(upcoming.getName());
        holder.dueDate_upcoming.setText(upcoming.getDueDate());
        holder.address_upcoming.setText(upcoming.getAddress());
        holder.contactNum_upcoming.setText(upcoming.getContactNumber());
        holder.organizer_upcoming.setText(upcoming.getOrganizer());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = ((FragmentActivity) context)
                        .getSupportFragmentManager()
                        .beginTransaction();
                transaction.replace(R.id.fragment_container, new UpcomingCancelFragment());
                transaction.addToBackStack(null);
                transaction.commit();

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
            volunteerName_upcoming = itemView.findViewById(R.id.volunteerName_upcoming);
            dueDate_upcoming = itemView.findViewById(R.id.dueDate_upcoming);
            address_upcoming = itemView.findViewById(R.id.address_upcoming);
            contactNum_upcoming = itemView.findViewById(R.id.contactnum_upcoming);
            organizer_upcoming = itemView.findViewById(R.id.organizer_upcoming);
            cardView = itemView.findViewById(R.id.upcoming_cardView);
        }
    }

}
