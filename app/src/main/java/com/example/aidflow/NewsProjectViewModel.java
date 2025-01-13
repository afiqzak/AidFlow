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
    private MutableLiveData<List<NewsProjects>> projects = new MutableLiveData<>();
    private MutableLiveData<NewsProjects> selectedProject = new MutableLiveData<>();

    public MutableLiveData<List<NewsProjects>> getProjects() {
        return projects;
    }

    public MutableLiveData<NewsProjects> getSelectedProject() {
        return selectedProject;
    }

    public void fetchProjects() {
        // Fetch projects from Firestore
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



                    Log.d("NewsProjectViewModel", "Projects fetched: " + projectsList.size());
                    projects.postValue(projectsList);

                })
                .addOnFailureListener(e -> {
                    Log.e("NewsProjectViewModel", "Error getting projects", e);
                });
    }

    public String formatDate(Date date) {
        if (date == null) {
            return null; // Handle null date
        }

        SimpleDateFormat outputFormat = new SimpleDateFormat("MMM yyyy", Locale.getDefault());
        return outputFormat.format(date);
    }

}