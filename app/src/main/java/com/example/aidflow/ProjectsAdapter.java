package com.example.aidflow;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.aidflow.R;

import java.util.EventListenerProxy;
import java.util.List;

public class ProjectsAdapter extends RecyclerView.Adapter<ProjectsAdapter.ProjectsViewHolder> {

    private final List<Projects> projectsList;
    private Context context;

    public ProjectsAdapter(List<Projects> projectsList, Context context) {
        this.projectsList = projectsList;
        this.context = context;
    }

    @NonNull
    @Override
    public ProjectsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.project_card, parent, false);
        return new ProjectsViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return projectsList.size();
    }

    //onbind ni tak tau do semua sbb den yg buat saya copy je (tanya den)
    @Override
    public void onBindViewHolder(@NonNull ProjectsViewHolder holder, int position) {
        Projects projects = projectsList.get(position);

        holder.projectTitles.setText(projects.getProjectsName());
        holder.projectDesc.setText(projects.getProjectsDesc());
        holder.projectDate.setText(projects.getStartDate() + " - " + projects.getEndDate());
        //holder.projectImages.setImageResource(projectImages.get(position));
        holder.progressBar.setProgress(projects.getProgress());

        if (projects.getImageUrl()!=null) {
            Glide.with(context)
                    .load(projects.getImageUrl())
                    .placeholder(R.drawable.default_image_news)
                    .into(holder.projectImages);
        } else{
            Log.e("ProjectsAdapter", "No image to display");
        }

        holder.ProjectButton.setOnClickListener(v -> {
            // Pass the projects details to the destination
            Bundle bundle = new Bundle();
            bundle.putString("projectsTitle", projects.getProjectsName());
            bundle.putString("projectsDesc", projects.getProjectsDesc());
            bundle.putString("projectsGoals", projects.getProjectsGoal());
            bundle.putString("startDate", projects.getStartDate());
            bundle.putString("endDate", projects.getEndDate());
            bundle.putInt("progressRate", projects.getProgress());
            bundle.putString("imageUrl", projects.getImageUrl());

            // set to pass arguments
            Fragment projectsFullPage = new ProjectsFullPage();
            projectsFullPage.setArguments(bundle);

            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.action_newsMainPageFragment_to_projectsFullPage);
        });
    }


    //ni class for recycle view so takyah kacau kot
    public static class ProjectsViewHolder extends RecyclerView.ViewHolder {
        TextView projectTitles, projectDesc, projectDate;
        ImageView projectImages;
        ProgressBar progressBar;
        ImageButton ProjectButton;

        public ProjectsViewHolder(@NonNull View itemView) {
            super(itemView);
            projectTitles = itemView.findViewById(R.id.ProjectsTitle);
            projectDesc = itemView.findViewById(R.id.ProjectsDesc);
            projectDate = itemView.findViewById(R.id.ProjectsDate);
            progressBar =itemView.findViewById(R.id.progressBar);
            projectImages = itemView.findViewById(R.id.ProjectsImage);
            ProjectButton = itemView.findViewById(R.id.ProjectButton);
        }
    }
}
