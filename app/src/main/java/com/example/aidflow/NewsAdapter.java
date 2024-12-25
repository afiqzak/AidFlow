package com.example.aidflow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aidflow.R;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private Context context;
    private List<String> newsTitle,newsDesc,newsDate;

    public NewsAdapter(Context context, List<String> newsTitle, List<String> newsDesc, List<String> newsDate) {
        this.context = context;
        this.newsTitle = newsTitle;
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
        return newsTitle.size();
    }

    //onbind ni tak tau do semua sbb den yg buat saya copy je (tanya den)
    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        holder.newsTitle.setText(newsTitle.get(position));
        holder.newsDesc.setText(newsDesc.get(position));
        holder.newsDate.setText(newsDate.get(position));

        holder.itemView.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);

            navController.navigate(R.id.action_newsMainPageFragment_to_destNewsFull);
        });

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
