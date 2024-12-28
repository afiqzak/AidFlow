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
    private List<String> ratingTitles; // list yg rating

    public WaterRatingAdapter(Context context, List<String> ratingTitles) {
        this.context = context;
        this.ratingTitles = ratingTitles;
    }

    @NonNull
    @Override
    public RatingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_water_rating_cardview, parent, false);
        return new RatingViewHolder(view);
    }


    //bind yg ni lak utk seek bar gerak2 tu
    @Override
    public void onBindViewHolder(@NonNull RatingViewHolder holder, int position) {
        String title = ratingTitles.get(position);
        holder.ratingTitle.setText(title);

        holder.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Handle progress change
                holder.ratingTitle.setText(title + " - Rating: " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    @Override
    public int getItemCount() {
        return ratingTitles.size();
    }

    //ni class utk recycle view so takyah kacau kot
    public static class RatingViewHolder extends RecyclerView.ViewHolder {
        TextView ratingTitle;
        SeekBar seekBar;

        public RatingViewHolder(@NonNull View itemView) {
            super(itemView);
            ratingTitle = itemView.findViewById(R.id.rating_title);
            seekBar = itemView.findViewById(R.id.rating_seekbar);
        }
    }
}

