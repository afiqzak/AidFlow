package com.example.newsmodule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsmodule.R;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private Context context;
    private List<String> newsTitles,newsDesc,newsDate;

    public NewsAdapter(Context context, List<String> newsTitles, List<String> newsDesc, List<String> newsDate) {
        this.context = context;
        this.newsTitles = newsTitles;
        this.newsDesc = newsDesc;
        this.newsDate = newsDate;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.news_card, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return newsTitles.size();
    }

    //onbind ni tak tau do semua sbb den yg buat saya copy je (tanya den)
    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        String title = newsTitles.get(position);
        holder.pendingTitle.setText(title);
        holder.pendingPlace.setText("Place " + (position + 1)); // Example: Dynamically set the place
    }


    //ni class for recycle view so takyah kacau kot
    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView newsTitle, newsDesc, newsDate;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            newsTitle = itemView.findViewById(R.id.newsTitle);
            newsDesc = itemView.findViewById(R.id.newsDesc);
            newsDate = itemView.findViewById(R.id.newsDate);
        }
    }
}
