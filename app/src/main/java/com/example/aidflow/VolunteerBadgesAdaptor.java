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

    public VolunteerBadgesAdaptor(Context context, ArrayList<ProfileBadges> badges, User user) {
        this.context = context;
        this.badges = badges;
        this.user = user;
    }

    @NonNull
    @Override
    public VolunteerBadgesAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate the layout
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.single_volunteer_badge, parent, false);
        return new VolunteerBadgesAdaptor.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VolunteerBadgesAdaptor.ViewHolder holder, int position) {
        holder.IVBadgeV.setImageResource(badges.get(position).getImg());
        String condition = badges.get(position).getCondition();
        String[] parts = condition.split(" >= ");
        int value = Integer.parseInt(parts[1]);

        if(condition.contains("hour")){
            holder.IVBadgeV.setImageResource(badges.get(position).getImg());
            if (!(user.getVolunteerHours() >= value))
                holder.IVBadgeV.setAlpha(0.3F);
        }
    }

    @Override
    public int getItemCount() {
        return badges.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //grab views in single_profile_badges.xml
        ImageView IVBadgeV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            IVBadgeV = itemView.findViewById(R.id.IVBadgeV);
        }
    }
}
