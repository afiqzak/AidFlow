package com.example.aidflow;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WaterRatingAdapter extends RecyclerView.Adapter<WaterRatingAdapter.RatingViewHolder> {

    private Context context;
    private List<String> ratingTitles; // List of rating titles

    public WaterRatingAdapter(Context context, List<String> ratingTitles) {
        this.context = context;
        this.ratingTitles = ratingTitles;
    }

    @NonNull
    @Override
    public RatingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each item in the RecyclerView
        View view = LayoutInflater.from(context).inflate(R.layout.single_water_rating_cardview, parent, false);
        return new RatingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RatingViewHolder holder, int position) {
        // Bind data to the views in each item
        String title = ratingTitles.get(position);
        holder.ratingTitle.setText(title);

        holder.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Handle progress change
                holder.ratingTitle.setText(title + " - Rating: " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Handle start of touch event
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Handle stop of touch event
            }
        });
    }

    @Override
    public int getItemCount() {
        // Return the size of the rating titles list
        return ratingTitles.size();
    }

    // ViewHolder class to hold references to the views in each item
    public static class RatingViewHolder extends RecyclerView.ViewHolder {
        TextView ratingTitle;
        SeekBar seekBar;

        public RatingViewHolder(@NonNull View itemView) {
            super(itemView);
            ratingTitle = itemView.findViewById(R.id.TVService);
            seekBar = itemView.findViewById(R.id.SBTime);
        }
    }
}

