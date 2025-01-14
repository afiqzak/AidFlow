package com.example.aidflow;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class WaterPendingAdapter extends RecyclerView.Adapter<WaterPendingAdapter.PendingViewHolder> {

    private Context context;
    private List<WaterReport> reports; // List of pending projects

    public WaterPendingAdapter(Context context, List<WaterReport> reports) {
        this.context = context;
        this.reports = reports;
    }

    @NonNull
    @Override
    public PendingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each item in the RecyclerView
        View view = LayoutInflater.from(context).inflate(R.layout.single_water_pending_projects_card, parent, false);
        return new PendingViewHolder(view);
    }

    @Override
    public int getItemCount() {
        // Return the size of the reports list
        return reports.size();
    }

    @Override
    public void onBindViewHolder(@NonNull PendingViewHolder holder, int position) {
        // Bind data to the views in each item
        holder.pendingTitle.setText(reports.get(position).getComplaint());
        holder.pendingPlace.setText(reports.get(position).getAddress());

        if (reports.get(position).getImageUrl() != null) {
            // Load image using Glide library
            Glide.with(context)
                    .load(reports.get(position).getImageUrl())
                    .placeholder(R.drawable.default_image_news)
                    .into(holder.IVReport);
        } else {
            Log.e("ProjectsAdapter", "No image to display");
        }
    }

    // ViewHolder class to hold references to the views in each item
    public static class PendingViewHolder extends RecyclerView.ViewHolder {
        TextView pendingPlace, pendingTitle;
        ImageView IVReport;

        public PendingViewHolder(@NonNull View itemView) {
            super(itemView);
            pendingTitle = itemView.findViewById(R.id.pending_title);
            pendingPlace = itemView.findViewById(R.id.pending_place);
            IVReport = itemView.findViewById(R.id.IVReport);
        }
    }
}
