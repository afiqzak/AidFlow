package com.example.aidflow;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

class DonationAdapter extends RecyclerView.Adapter<DonationAdapter.DonationViewHolder> {

    private final List<Donation> donationList;
    private Context context;

    public DonationAdapter(List<Donation> donationList, Context context) {
        this.donationList = donationList;
        this.context = context;
    }

    @NonNull
    @Override
    public DonationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_donation_recylcleview, parent, false);
        return new DonationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DonationViewHolder holder, int position) {
        Donation donation = donationList.get(position);

        // Bind data to views
        holder.donationName.setText(donation.getName());
        holder.projectName.setText(donation.getProjectName());
        holder.dueDate.setText(donation.getDueDate());
        holder.progressText.setText(donation.getProgress() + "%");
        holder.donationProgress.setProgress(donation.getProgress());

        // Set urgency color
        switch (donation.getUrgency()) {
            case "high":
                holder.urgencyIndicator.setBackgroundResource(R.color.red);
                break;
            case "moderate":
                holder.urgencyIndicator.setBackgroundResource(R.color.green);
                break;
            case "low":
                holder.urgencyIndicator.setBackgroundResource(R.color.yellow);
                break;
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("donationID", donation.getDonationID());
                bundle.putString("name", donation.getName());
                bundle.putString("projectName", donation.getProjectName());
                bundle.putString("description", donation.getDescription());
                bundle.putString("dueDate", donation.getDueDate());
                bundle.putInt("progress", donation.getProgress());
                bundle.putString("urgency", donation.getUrgency());
                bundle.putString("organizationName", donation.getOrganizationName());
                bundle.putLong("targetDonationAmount", donation.getTargetDonationAmount());
                bundle.putLong("currentDonationAmount", donation.getCurrentDonationAmount());


                DonationFormFragment donationFormFragment = new DonationFormFragment();
                donationFormFragment.setArguments(bundle);

                FragmentTransaction transaction = ((FragmentActivity) context)
                        .getSupportFragmentManager()
                        .beginTransaction();
                transaction.replace(R.id.FCVDonation, donationFormFragment);
                //transaction.replace(R.id.FragmentViewMain, donationFormFragment);
                transaction.addToBackStack(null);
                transaction.commit();

//                FragmentTransaction transaction = ((FragmentActivity) context)
//                        .getSupportFragmentManager()
//                        .beginTransaction();
//                transaction.replace(R.id.fragmentContainerView, new DonationFormFragment());
//                transaction.addToBackStack(null);
//                transaction.commit();

            }
        });
    }


    @Override
    public int getItemCount() {
        return donationList.size();
    }

    static class DonationViewHolder extends RecyclerView.ViewHolder {
        TextView donationName, projectName, dueDate, progressText;
        ProgressBar donationProgress;
        View urgencyIndicator;
        CardView cardView;

        public DonationViewHolder(@NonNull View itemView) {
            super(itemView);
            donationName = itemView.findViewById(R.id.donationName);
            projectName = itemView.findViewById(R.id.projectName);
            dueDate = itemView.findViewById(R.id.dueDate);
            progressText = itemView.findViewById(R.id.progressText);
            donationProgress = itemView.findViewById(R.id.donationProgress);
            urgencyIndicator = itemView.findViewById(R.id.urgencyIndicator);
            cardView = itemView.findViewById(R.id.donation_cardView);
        }
    }
}

