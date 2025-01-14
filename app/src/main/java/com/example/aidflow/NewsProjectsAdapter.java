package com.example.aidflow;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class NewsProjectsAdapter extends RecyclerView.Adapter<NewsProjectsAdapter.NewsProjectsViewHolder> {

    private final List<NewsProjects> projectsList; // List of projects to display
    private Context context; // Context for accessing resources
    private NewsProjectViewModel newsProjectViewModel; // ViewModel for managing project data

    public NewsProjectsAdapter(List<NewsProjects> projectsList, Context context, NewsProjectViewModel newsProjectViewModel) {
        this.projectsList = projectsList;
        this.context = context;
        this.newsProjectViewModel = newsProjectViewModel;
    }

    @NonNull
    @Override
    public NewsProjectsAdapter.NewsProjectsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each project item
        View view = LayoutInflater.from(context).inflate(R.layout.single_news_project_card, parent, false);
        return new NewsProjectsAdapter.NewsProjectsViewHolder(view);
    }

    @Override
    public int getItemCount() {
        // Return the total number of projects
        return projectsList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull NewsProjectsAdapter.NewsProjectsViewHolder holder, int position) {
        // Bind the project data to the view holder
        NewsProjects projects = projectsList.get(position);

        holder.projectTitles.setText(projects.getProjectsName());
        holder.projectDesc.setText(projects.getProjectsDesc());
        holder.projectDate.setText(projects.getSDate() + " - " + projects.getEDate());
        holder.progressBar.setProgress(projects.getProgressRate());

        // Load project image using Glide
        if (projects.getImageUrl() != null) {
            Glide.with(context)
                    .load(projects.getImageUrl())
                    .placeholder(R.drawable.default_image_news)
                    .into(holder.projectImages);
        } else {
            Log.e("ProjectsAdapter", "No image to display");
        }

        // Set click listener for the project button
        holder.ProjectButton.setOnClickListener(v -> {
            if (newsProjectViewModel != null) {
                newsProjectViewModel.getSelectedProject().setValue(projects);
                Log.d("ProjectsAdapter", "Setting selected project: " + newsProjectViewModel.getSelectedProject().getValue().getProjectsName());
                Navigation.findNavController(v).navigate(R.id.destProjectFull);
            } else {
                Log.e("ProjectsAdapter", "ViewModel is null");
            }
        });
    }

    // ViewHolder class for holding project item views
    public static class NewsProjectsViewHolder extends RecyclerView.ViewHolder {
        TextView projectTitles, projectDesc, projectDate;
        ImageView projectImages;
        ProgressBar progressBar;
        ImageButton ProjectButton;

        public NewsProjectsViewHolder(@NonNull View itemView) {
            super(itemView);
            projectTitles = itemView.findViewById(R.id.ProjectsTitle);
            projectDesc = itemView.findViewById(R.id.ProjectsDesc);
            projectDate = itemView.findViewById(R.id.ProjectsDate);
            progressBar = itemView.findViewById(R.id.progressBar);
            projectImages = itemView.findViewById(R.id.ProjectsImage);
            ProjectButton = itemView.findViewById(R.id.ProjectButton);
        }
    }
}
