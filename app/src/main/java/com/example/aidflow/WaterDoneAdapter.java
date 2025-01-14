package com.example.aidflow;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class WaterDoneAdapter extends RecyclerView.Adapter<WaterDoneAdapter.DoneViewHolder> {

    private Context context;
    private List<WaterReport> doneReport;
    private WaterViewModel waterViewModel;

    // Constructor to initialize context, doneReport list, and waterViewModel
    public WaterDoneAdapter(Context context, List<WaterReport> doneReport, WaterViewModel waterViewModel) {
        this.context = context;
        this.doneReport = doneReport;
        this.waterViewModel = waterViewModel;
    }

    @NonNull
    @Override
    public DoneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each item in the RecyclerView
        View view = LayoutInflater.from(context).inflate(R.layout.single_water_done_card, parent, false);
        return new DoneViewHolder(view);
    }

    @Override
    public int getItemCount() {
        // Return the size of the doneReport list
        return doneReport.size();
    }

    @Override
    public void onBindViewHolder(@NonNull DoneViewHolder holder, int position) {
        // Bind data to the views in each item
        WaterReport done = doneReport.get(position);
        holder.doneTitle.setText(done.getComplaint());
        holder.donePlace.setText(done.getAddress()); // Dynamically set the place
        if (done.getImageUrl() != null) {
            // Load image using Glide library
            Glide.with(context)
                    .load(done.getImageUrl())
                    .placeholder(R.drawable.default_image_news)
                    .into(holder.IVDone);
        } else {
            Log.e("ProjectsAdapter", "No image to display");
        }
        holder.doneStarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set the selected report in the ViewModel and navigate to the rating screen
                waterViewModel.getSelectedReport().setValue(done);
                Log.d("WaterDoneAdapter", "Selected report: " + done.getReportID());
                Navigation.findNavController(v).navigate(R.id.waterRating);
            }
        });
    }

    // ViewHolder class to hold references to the views in each item
    public static class DoneViewHolder extends RecyclerView.ViewHolder {
        TextView donePlace, doneTitle;
        ImageButton doneStarButton;
        ImageView IVDone;

        public DoneViewHolder(@NonNull View itemView) {
            super(itemView);
            doneTitle = itemView.findViewById(R.id.done_title);
            donePlace = itemView.findViewById(R.id.done_place);
            doneStarButton = itemView.findViewById(R.id.doneStarButton);
            IVDone = itemView.findViewById(R.id.IVDone);
        }
    }
}