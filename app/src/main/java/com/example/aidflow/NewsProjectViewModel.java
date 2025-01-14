package com.example.aidflow;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NewsProjectViewModel extends ViewModel {
    // LiveData to hold the list of projects
    private MutableLiveData<List<NewsProjects>> projects = new MutableLiveData<>();
    // LiveData to hold the selected project
    private MutableLiveData<NewsProjects> selectedProject = new MutableLiveData<>();

    // Getter for projects LiveData
    public MutableLiveData<List<NewsProjects>> getProjects() {
        return projects;
    }

    // Getter for selected project LiveData
    public MutableLiveData<NewsProjects> getSelectedProject() {
        return selectedProject;
    }

    // Method to fetch projects from Firestore
    public void fetchProjects() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        List<NewsProjects> projectsList = new ArrayList<>();
        db.collection("Project")
                .whereGreaterThan("endDate", new Date())
                .orderBy("endDate", Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    for (DocumentSnapshot doc : queryDocumentSnapshots) {
                        NewsProjects project = doc.toObject(NewsProjects.class);
                        if(project != null){
                            project.setEDate(formatDate(doc.getDate("endDate")));
                            project.setSDate(formatDate(doc.getDate("startDate")));
                            projectsList.add(project);
                        }
                    }

                    // Log the number of projects fetched
                    Log.d("NewsProjectViewModel", "Projects fetched: " + projectsList.size());
                    projects.postValue(projectsList);

                })
                .addOnFailureListener(e -> {
                    // Log error if fetching projects fails
                    Log.e("NewsProjectViewModel", "Error getting projects", e);
                });
    }

    // Method to format date to "MMM yyyy"
    public String formatDate(Date date) {
        if (date == null) {
            return null; // Handle null date
        }

        SimpleDateFormat outputFormat = new SimpleDateFormat("MMM yyyy", Locale.getDefault());
        return outputFormat.format(date);
    }

}