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

    public NewsAdapter(Context context, List<News> news) {
        this.context = context;
        this.news = news;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_news_card, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return news.size();
    }


    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        News anews = news.get(position);

        holder.newsTitle.setText(anews.getTitle());
        holder.newsDesc.setText(anews.getDescription());
        holder.newsDate.setText(anews.getPublished());

        // Optionally load image using Glide or Picasso
        if (anews.getImage() != null) {
            Glide.with(context)
                    .load(anews.getImage())
                    .placeholder(R.drawable.default_image_news)
                    .into(holder.newsImage);
        }

        holder.itemView.setOnClickListener(v -> {
            // Pass the article details to the destination
            Bundle bundle = new Bundle();
            bundle.putString("title", anews.getTitle());
            bundle.putString("description", anews.getDescription());
            bundle.putString("url", anews.getUrl());
            bundle.putString("imageUrl", anews.getImage());
            bundle.putString("publisherName", anews.getAuthor());

            // set to pass arguments
            Fragment newsFullPage = new NewsFullPage();
            newsFullPage.setArguments(bundle);

            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.action_newsMainPageFragment_to_destNewsFull, bundle);
        });
    }


    //ni class for recycle view so takyah kacau kot
    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView newsTitle, newsDesc, newsDate;
        ImageView newsImage;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            newsTitle = itemView.findViewById(R.id.newsTitle);
            //newsDesc = itemView.findViewById(R.id.newsDesc);
            newsDate = itemView.findViewById(R.id.newsDate);
            newsImage = itemView.findViewById(R.id.newsImageCard);
        }


    }
}
