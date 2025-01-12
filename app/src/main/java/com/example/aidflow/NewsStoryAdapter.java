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
    private NewsStoryViewModel newsStoryViewModel;

    public NewsStoryAdapter( Context context, List<NewsStory> storyList, NewsStoryViewModel newsStoryViewModel) {
        this.storyList = storyList;
        this.context = context;
        this.newsStoryViewModel = newsStoryViewModel;
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
            newsStoryViewModel.getSelectedStory().setValue(story);
            Navigation.findNavController(v).navigate(R.id.destStoryFull);
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
