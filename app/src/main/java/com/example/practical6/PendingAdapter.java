package com.example.practical6;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practical6.R;

import java.util.List;

public class PendingAdapter extends RecyclerView.Adapter<PendingAdapter.PendingViewHolder> {

    private Context context;
    private List<String> pendingTitles; // List of titles or data for the cards

    public PendingAdapter(Context context, List<String> ratingTitles) {
        this.context = context;
        this.pendingTitles = ratingTitles;
    }

    @NonNull
    @Override
    public PendingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.pending_projects_card, parent, false);
        return new PendingViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return pendingTitles.size();
    }

    @Override
    public void onBindViewHolder(@NonNull PendingViewHolder holder, int position) {
        String title = pendingTitles.get(position);
        holder.pendingTitle.setText(title);
        holder.pendingPlace.setText("Place " + (position + 1)); // Example: Dynamically set the place
    }


    public static class PendingViewHolder extends RecyclerView.ViewHolder {
        TextView pendingPlace,pendingTitle;

        public PendingViewHolder(@NonNull View itemView) {
            super(itemView);
            pendingTitle = itemView.findViewById(R.id.pending_title);
            pendingPlace = itemView.findViewById(R.id.pending_place);
        }
    }
}
