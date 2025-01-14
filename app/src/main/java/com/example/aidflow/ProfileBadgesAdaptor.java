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

public class ProfileBadgesAdaptor extends RecyclerView.Adapter<ProfileBadgesAdaptor.ViewHolder>{
    // Context for accessing resources and layout inflaters
    Context context;
    // List of ProfileBadges to display
    ArrayList<ProfileBadges> badges;
    // User object to check badge conditions
    private User user;

    // Constructor to initialize context, badges list, and user
    public ProfileBadgesAdaptor(Context context, ArrayList<ProfileBadges> badges, User user) {
        this.context = context;
        this.badges = badges;
        this.user = user;
    }

    @NonNull
    @Override
    public ProfileBadgesAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each badge item
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.single_profile_badge, parent, false);
        return new ProfileBadgesAdaptor.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileBadgesAdaptor.ViewHolder holder, int position) {
        // Assign values to the views in single_profile_badges.xml
        holder.TVRequirement.setText(badges.get(position).getRequirement());
        holder.IVBadge.setImageResource(badges.get(position).getImg());

        // Get the condition for the badge and split it to get the value
        String condition = badges.get(position).getCondition();
        String[] parts = condition.split(" >= ");
        int value = Integer.parseInt(parts[1]);

        // Check the condition type and set the badge transparency accordingly
        if(condition.contains("hour")){
            if (!(user.getVolunteerHours() >= value))
                holder.IVBadge.setAlpha(0.3F);
        } else if(condition.contains("report")){
            if (!(user.getReportSubmitted() >= value))
                holder.IVBadge.setAlpha(0.3F);
        } else {
            if (!(user.getTotalDonate() >= value))
                holder.IVBadge.setAlpha(0.3F);
        }
    }

    @Override
    public int getItemCount() {
        // Return the size of the badges list
        return badges.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // TextView for displaying the badge requirement
        TextView TVRequirement;
        // ImageView for displaying the badge image
        ImageView IVBadge;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Initialize the views from the layout
            IVBadge = itemView.findViewById(R.id.IVBadge);
            TVRequirement = itemView.findViewById(R.id.TVRequirement);
        }
    }
}
