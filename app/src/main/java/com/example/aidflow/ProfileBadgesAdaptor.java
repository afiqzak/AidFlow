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
    Context context;
    ArrayList<ProfileBadges> badges;

    public ProfileBadgesAdaptor(Context context, ArrayList<ProfileBadges> badges) {
        this.context = context;
        this.badges = badges;
    }
    @NonNull
    @Override
    public ProfileBadgesAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate the layout
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.single_profile_badge, parent, false);
        return new ProfileBadgesAdaptor.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileBadgesAdaptor.ViewHolder holder, int position) {
        //assign value to the views in single_profile_badges.xml
        holder.TVRequirement.setText(badges.get(position).getRequirement());
        holder.IVBadge.setImageResource(badges.get(position).getImg());
    }

    @Override
    public int getItemCount() {
        //return the size of the list
        return badges.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //grab views in single_profile_badges.xml
        TextView TVRequirement;
        ImageView IVBadge;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            IVBadge = itemView.findViewById(R.id.IVBadge);
            TVRequirement = itemView.findViewById(R.id.TVRequirement);
        }
    }
}
