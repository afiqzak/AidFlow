package com.example.aidflow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class VolunteerBadgesAdaptor extends RecyclerView.Adapter<VolunteerBadgesAdaptor.ViewHolder>{
    Context context;
    ArrayList<ProfileBadges> badges;
    private User user;

    // Constructor to initialize the adapter with the context, badges list, and user
    public VolunteerBadgesAdaptor(Context context, ArrayList<ProfileBadges> badges, User user) {
        this.context = context;
        this.badges = badges;
        this.user = user;
    }

    @NonNull
    @Override
    public VolunteerBadgesAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each item in the RecyclerView
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.single_volunteer_badge, parent, false);
        return new VolunteerBadgesAdaptor.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VolunteerBadgesAdaptor.ViewHolder holder, int position) {
        // Bind data to views
        holder.IVBadgeV.setImageResource(badges.get(position).getImg());
        String condition = badges.get(position).getCondition();
        String[] parts = condition.split(" >= ");
        int value = Integer.parseInt(parts[1]);

        // Check the condition and set the badge transparency accordingly
        if(condition.contains("hour")){
            holder.IVBadgeV.setImageResource(badges.get(position).getImg());
            if (!(user.getVolunteerHours() >= value))
                holder.IVBadgeV.setAlpha(0.3F);
        }
    }

    @Override
    public int getItemCount() {
        // Return the total number of items in the list
        return badges.size();
    }

    // ViewHolder class to hold references to the views in each item
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView IVBadgeV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initialize the views
            IVBadgeV = itemView.findViewById(R.id.IVBadgeV);
        }
    }
}
