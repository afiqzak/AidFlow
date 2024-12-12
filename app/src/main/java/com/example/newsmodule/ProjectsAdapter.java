package com.example.newsmodule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsmodule.R;

import java.util.List;

public class ProjectsAdapter extends RecyclerView.Adapter<ProjectsAdapter.ProjectsViewHolder> {

    private Context context;
    private List<String> projectTitles,projectDesc,projectDate;
    private List<Integer> projectImages;

    public ProjectsAdapter(Context context, List<String> projectTitles, List<String> projectDesc, List<String> projectDate, List<Integer> projectImages) {
        this.context = context;
        this.projectTitles = projectTitles;
        this.projectDesc = projectDesc;
        this.projectDate = projectDate;
        this.projectImages = projectImages;
    }

    @NonNull
    @Override
    public ProjectsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.project_card, parent, false);
        return new ProjectsViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return projectTitles.size();
    }

    //onbind ni tak tau do semua sbb den yg buat saya copy je (tanya den)
    @Override
    public void onBindViewHolder(@NonNull ProjectsViewHolder holder, int position) {
        holder.projectTitles.setText(projectTitles.get(position));
        holder.projectDesc.setText(projectDesc.get(position));
        holder.projectDate.setText(projectDate.get(position));
        holder.projectImages.setImageResource(projectImages.get(position));
        holder.progressBar.setProgress(75);
    }


    //ni class for recycle view so takyah kacau kot
    public static class ProjectsViewHolder extends RecyclerView.ViewHolder {
        TextView projectTitles, projectDesc, projectDate;
        ImageView projectImages;
        ProgressBar progressBar;

        public ProjectsViewHolder(@NonNull View itemView) {
            super(itemView);
            projectTitles = itemView.findViewById(R.id.ProjectsTitle);
            projectDesc = itemView.findViewById(R.id.ProjectsDesc);
            projectDate = itemView.findViewById(R.id.ProjectsDate);
            progressBar =itemView.findViewById(R.id.progressBar);
            projectImages = itemView.findViewById(R.id.ProjectsImage);
        }
    }
}
