package com.example.aidflow;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private Context context;
    private List<News> news;

    // Constructor to initialize context and news list
    public NewsAdapter(Context context, List<News> news) {
        this.context = context;
        this.news = news;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each news item
        View view = LayoutInflater.from(context).inflate(R.layout.single_news_card, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public int getItemCount() {
        // Return the total number of news items
        return news.size();
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        // Get the news item at the current position
        News anews = news.get(position);

        // Bind the news data to the views
        holder.newsTitle.setText(anews.getTitle());
        holder.newsDesc.setText(anews.getDescription());
        holder.newsDate.setText(anews.getPublished());

        // Load the news image using Glide if available
        if (anews.getImage() != null) {
            Glide.with(context)
                    .load(anews.getImage())
                    .placeholder(R.drawable.default_image_news)
                    .into(holder.newsImage);
        }

        // Set click listener to navigate to the news details
        holder.itemView.setOnClickListener(v -> {
            // Pass the article details to the destination
            Bundle bundle = new Bundle();
            bundle.putString("url_key", anews.getUrl()); // Use "url_key" to match with the fragment

            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.destNewsWeb, bundle); // Ensure destNewsWeb is the correct action ID
        });
    }

    // ViewHolder class to hold the views for each news item
    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView newsTitle, newsDesc, newsDate;
        ImageView newsImage;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            newsTitle = itemView.findViewById(R.id.newsTitle);
            newsDesc = itemView.findViewById(R.id.newsDesc);
            newsDate = itemView.findViewById(R.id.newsDate);
            newsImage = itemView.findViewById(R.id.newsImageCard);
        }
    }
}
