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

import java.util.List;

public class NewsStoryAdapter extends RecyclerView.Adapter<NewsStoryAdapter.NewsStoryViewHolder> {

    private Context context;
    private List<NewsStory> storyList;

    public NewsStoryAdapter( Context context, List<NewsStory> storyList) {
        this.storyList = storyList;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsStoryAdapter.NewsStoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_news_story_card, parent, false);
        return new NewsStoryAdapter.NewsStoryViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return storyList.size();
    }

    //onbind ni tak tau do semua sbb den yg buat saya copy je (tanya den)
    @Override
    public void onBindViewHolder(@NonNull NewsStoryAdapter.NewsStoryViewHolder holder, int position) {
        NewsStory story = storyList.get(position);

        if(story.getImageUrl()!=null) {
            Glide.with(context)
                    .load(story.getImageUrl())
                    .placeholder(R.drawable.default_image_news)
                    .into(holder.storyImage);
        } else{
            Log.e("StoryAdapter", "No image to display");
        }

        holder.itemView.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("userID", story.getUserID());
            bundle.putString("username", story.getUsername());
            bundle.putString("description", story.getDescription());
            bundle.putString("imageUrl", story.getImageUrl());
            bundle.putString("userImageUrl", story.getUserImageUrl());


            Fragment storyFullPage = new NewsStoryFullFragment();
            storyFullPage.setArguments(bundle);

            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.action_newsMainPageFragment_to_storyFullFragment, bundle);
        });
    }


    //ni class for recycle view so takyah kacau kot
    public static class NewsStoryViewHolder extends RecyclerView.ViewHolder {
        ImageView storyImage;

        public NewsStoryViewHolder(@NonNull View itemView) {
            super(itemView);
            storyImage = itemView.findViewById(R.id.story_image);
        }
    }
}
