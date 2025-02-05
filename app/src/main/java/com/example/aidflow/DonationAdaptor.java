package com.example.aidflow;
import android.content.Context;
import android.content.res.ColorStateList;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// Adapter class for displaying donation items in a RecyclerView
class DonationAdapter extends RecyclerView.Adapter<DonationAdapter.DonationViewHolder> {

    private final List<Donation> donationList;
    private Context context;
    private DonationViewModel donationViewModel;

    // Constructor for initializing the adapter with donation data, context, and ViewModel
    public DonationAdapter(List<Donation> donationList, Context context, DonationViewModel donationViewModel) {
        this.donationList = donationList;
        this.context = context;
        this.donationViewModel = donationViewModel;
    }

    @NonNull
    @Override
    public DonationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each donation item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_donation_recylcleview, parent, false);
        return new DonationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DonationViewHolder holder, int position) {
        Donation donation = donationList.get(position);

        // Calculate the progress percentage
        double progress = donation.getCurrentAmount() / donation.getTargetAmount() * 100;

        // Bind data to views
        holder.TVDonationTitle.setText(donation.getDonationTitle());
        holder.TVDonationCategory.setText(donation.getCategory());
        holder.TVDonationDueDate.setText(donation.getDueDate());
        holder.TVDonationProgress.setText(Math.round(progress) + "%");
        holder.PBDonation.setProgress((int) Math.round(progress));

        // Set urgency color based on donation urgency level
        switch (donation.getUrgency()) {
            case "High Priority":
                holder.urgencyIndicator.setBackgroundTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.red)));
                break;
            case "Moderate":
                holder.urgencyIndicator.setBackgroundTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.yellow)));
                break;
            case "Low Priority":
                holder.urgencyIndicator.setBackgroundTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.green)));
                break;
        }

        // Set click listener for the card view to navigate to the donation form
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                donationViewModel.setSelectedDonation(donation);
                Navigation.findNavController(v).navigate(R.id.donationForm);
            }
        });
    }

    @Override
    public int getItemCount() {
        return donationList.size();
    }

    // ViewHolder class for holding the views of each donation item
    static class DonationViewHolder extends RecyclerView.ViewHolder {
        TextView TVDonationTitle, TVDonationCategory, TVDonationDueDate, TVDonationProgress;
        ProgressBar PBDonation;
        View urgencyIndicator;
        CardView cardView;

        public DonationViewHolder(@NonNull View itemView) {
            super(itemView);
            TVDonationTitle = itemView.findViewById(R.id.TVDonationTitle);
            TVDonationCategory = itemView.findViewById(R.id.TVDonationCategory);
            TVDonationDueDate = itemView.findViewById(R.id.date);
            TVDonationProgress = itemView.findViewById(R.id.TVDonationProgress);
            PBDonation = itemView.findViewById(R.id.PBDonation);
            urgencyIndicator = itemView.findViewById(R.id.urgencyIndicator);
            cardView = itemView.findViewById(R.id.donation_cardView);
        }
    }
}

