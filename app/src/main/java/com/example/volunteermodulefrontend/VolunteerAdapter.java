package com.example.volunteermodulefrontend;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

class VolunteerAdapter extends RecyclerView.Adapter<VolunteerAdapter.VolunteerViewHolder> {

    private final List<Volunteer> volunteerList;
    private Context context;

    public VolunteerAdapter(List<Volunteer> volunteerList, Context context) {
        this.volunteerList = volunteerList;
        this.context = context;
    }

    @NonNull
    @Override
    public VolunteerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.volunteer_recycleview, parent, false);
        return new VolunteerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VolunteerViewHolder holder, int position) {
        Volunteer volunteer = volunteerList.get(position);

        // Bind data to views
        holder.volunteerName.setText(volunteer.getName());
        holder.districtName.setText(volunteer.getDistrict());
        holder.dueDate_volunteer.setText(volunteer.getDueDate());
        holder.volunteerProgress.setText(volunteer.getProgress()+ "/50");
        holder.volunteerProgressBar.setProgress(volunteer.getProgress());
//
//
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = ((FragmentActivity) context)
                        .getSupportFragmentManager()
                        .beginTransaction();
                transaction.replace(R.id.fragment_container, new VolunteerJoinFragment());
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });
    }

    @Override
    public int getItemCount() {
        return volunteerList.size();
    }

    static class VolunteerViewHolder extends RecyclerView.ViewHolder {
        TextView volunteerName,districtName,dueDate_volunteer,volunteerProgress;
        ProgressBar volunteerProgressBar;
        CardView cardView;

        public VolunteerViewHolder(@NonNull View itemView) {
            super(itemView);
            volunteerName = itemView.findViewById(R.id.volunteerName);
            volunteerProgressBar = itemView.findViewById(R.id.volunteerProgressBar);
            volunteerProgress = itemView.findViewById(R.id.volunteerProgress);
            dueDate_volunteer = itemView.findViewById(R.id.dueDate_volunteer);
            districtName = itemView.findViewById(R.id.districtName);
            cardView = itemView.findViewById(R.id.volunteer_cardView);
        }
    }

}
