package com.example.aidflow;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

public class NewsStoryAdapter extends RecyclerView.Adapter<NewsStoryAdapter.NewsStoryViewHolder> {

    private Context context;
    private List<NewsStory> storyList;
    private NewsStoryViewModel newsStoryViewModel;

    // Constructor to initialize context, story list, and view model
    public NewsStoryAdapter(Context context, List<NewsStory> storyList, NewsStoryViewModel newsStoryViewModel) {
        this.storyList = storyList;
        this.context = context;
        this.newsStoryViewModel = newsStoryViewModel;
    }

    @NonNull
    @Override
    public NewsStoryAdapter.NewsStoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each news story item
        View view = LayoutInflater.from(context).inflate(R.layout.single_news_story_card, parent, false);
        return new NewsStoryAdapter.NewsStoryViewHolder(view);
    }

    @Override
    public int getItemCount() {
        // Return the total number of news stories
        return storyList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull NewsStoryAdapter.NewsStoryViewHolder holder, int position) {
        // Bind the data for each news story item
        NewsStory story = storyList.get(position);

        // Load the story image using Glide
        if (story.getImageUrl() != null) {
            Glide.with(context)
                    .load(story.getImageUrl())
                    .placeholder(R.drawable.default_image_news)
                    .into(holder.storyImage);
        } else {
            Log.e("StoryAdapter", "No image to display");
        }

        // Load the user profile image using Glide
        if (story.getUserImageUrl() != null) {
            Glide.with(context)
                    .load(story.getUserImageUrl())
                    .placeholder(R.drawable.default_image_news)
                    .into(holder.IVProfileStory);
        } else {
            Log.e("StoryAdapter", "No image to display");
        }

        // Set click listener for the news story item
        holder.itemView.setOnClickListener(v -> {
            newsStoryViewModel.getSelectedStory().setValue(story);
            Navigation.findNavController(v).navigate(R.id.destStoryFull);
        });
    }

    // ViewHolder class for the news story item
    public static class NewsStoryViewHolder extends RecyclerView.ViewHolder {
        ImageView storyImage;
        ShapeableImageView IVProfileStory;

        public NewsStoryViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initialize the views for the story image and user profile image
            storyImage = itemView.findViewById(R.id.story_image);
            IVProfileStory = itemView.findViewById(R.id.IVProfileStory);
        }
    }
}
