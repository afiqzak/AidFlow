package com.example.aidflow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aidflow.R;

import java.util.List;

public class DoneAdapter extends RecyclerView.Adapter<DoneAdapter.DoneViewHolder> {

    private Context context;
    private List<String> doneTitles; // list utk project2 yg done

    public DoneAdapter(Context context, List<String> doneTitles) {
        this.context = context;
        this.doneTitles = doneTitles;
    }

    @NonNull
    @Override
    public DoneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.done_card, parent, false);
        return new DoneViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return doneTitles.size();
    }

    @Override
    public void onBindViewHolder(@NonNull DoneViewHolder holder, int position) {
        String title = doneTitles.get(position);
        holder.doneTitle.setText(title);
        holder.donePlace.setText("Place " + (position + 1)); // Example: Dynamically set the place
        holder.doneStarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.DestRating);
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
