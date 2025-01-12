package com.example.aidflow;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WaterDoneAdapter extends RecyclerView.Adapter<WaterDoneAdapter.DoneViewHolder> {

    private Context context;
    private List<WaterReport> doneReport;
    private WaterViewModel waterViewModel;

    public WaterDoneAdapter(Context context, List<WaterReport> doneReport, WaterViewModel waterViewModel) {
        this.context = context;
        this.doneReport = doneReport;
        this.waterViewModel = waterViewModel;
    }

    @NonNull
    @Override
    public DoneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_water_done_card, parent, false);
        return new DoneViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return doneReport.size();
    }

    @Override
    public void onBindViewHolder(@NonNull DoneViewHolder holder, int position) {
        WaterReport done = doneReport.get(position);
        holder.doneTitle.setText(done.getComplaint());
        holder.donePlace.setText(done.getAddress()); // Example: Dynamically set the place
        holder.doneStarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                waterViewModel.getSelectedReport().setValue(done);
                Navigation.findNavController(v).navigate(R.id.waterRating);
            }
        });
    }


    //ni class for recyclerview so takyah kacau kot
    public static class DoneViewHolder extends RecyclerView.ViewHolder {
        TextView donePlace,doneTitle;
        ImageButton doneStarButton;

        public DoneViewHolder(@NonNull View itemView) {
            super(itemView);
            doneTitle = itemView.findViewById(R.id.done_title);
            donePlace = itemView.findViewById(R.id.done_place);
            doneStarButton = itemView.findViewById(R.id.doneStarButton);
        }
    }
}