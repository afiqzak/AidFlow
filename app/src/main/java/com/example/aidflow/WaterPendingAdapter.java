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
    private List<WaterReport> reports; // list pending projects

    public WaterPendingAdapter(Context context, List<WaterReport> reports) {
        this.context = context;
        this.reports = reports;
    }

    @NonNull
    @Override
    public PendingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_water_pending_projects_card, parent, false);
        return new PendingViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return reports.size();
    }

    //onbind ni tak tau do semua sbb den yg buat saya copy je (tanya den)
    @Override
    public void onBindViewHolder(@NonNull PendingViewHolder holder, int position) {
        holder.pendingTitle.setText(reports.get(position).getComplaint());
        holder.pendingPlace.setText(reports.get(position).getAddress());

        if (reports.get(position).getImageUrl()!=null) {
            Glide.with(context)
                    .load(reports.get(position).getImageUrl())
                    .placeholder(R.drawable.default_image_news)
                    .into(holder.IVReport);
        } else{
            Log.e("ProjectsAdapter", "No image to display");
        }
    }


    //ni class for recycle view so takyah kacau kot
    public static class PendingViewHolder extends RecyclerView.ViewHolder {
        TextView pendingPlace,pendingTitle;
        ImageView IVReport;

        public PendingViewHolder(@NonNull View itemView) {
            super(itemView);
            pendingTitle = itemView.findViewById(R.id.pending_title);
            pendingPlace = itemView.findViewById(R.id.pending_place);
            IVReport = itemView.findViewById(R.id.IVReport);
        }
    }
}
