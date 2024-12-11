package com.example.newsmodule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.StoryViewHolder> {

    private Context context;
    private List<Integer> storyImage;

    public StoryAdapter(Context context, List<Integer> storyImage) {
        this.context = context;
        this.storyImage = storyImage;
    }

    @NonNull
    @Override
    public StoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.story_card, parent, false);
        return new StoryViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return storyImage.size();
    }

    //onbind ni tak tau do semua sbb den yg buat saya copy je (tanya den)
    @Override
    public void onBindViewHolder(@NonNull StoryViewHolder holder, int position) {
        holder.storyImage.setImageResource(storyImage.get(position));

        holder.storyImage.setOnClickListener( v -> {
            Toast.makeText(context, "Story " + (position + 1), Toast.LENGTH_SHORT).show();
        });
    }


    //ni class for recycle view so takyah kacau kot
    public static class StoryViewHolder extends RecyclerView.ViewHolder {
        ImageView storyImage;

        public StoryViewHolder(@NonNull View itemView) {
            super(itemView);
            storyImage = itemView.findViewById(R.id.story_image);
        }
    }
}
