package com.example.aidflow;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProjectsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProjectsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProjectsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProjectsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProjectsFragment newInstance(String param1, String param2) {
        ProjectsFragment fragment = new ProjectsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private RecyclerView recyclerView;
    private ProjectsAdapter adapter;
    private ArrayList<Projects> projectsList;
    private FirebaseFirestore db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_projects, container, false);

        recyclerView = view.findViewById(R.id.projectList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        projectsList = new ArrayList<Projects>();

        db = FirebaseFirestore.getInstance();

        fetchProjects();

        return view;
    }

    private void fetchProjects() {
        Query query = db.collection("projects");

        query.get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    if (queryDocumentSnapshots != null) {
                        projectsList.clear();

                        for (DocumentSnapshot document : queryDocumentSnapshots) {
                            Projects projects = retrieveFromDB(document);
                            projectsList.add(projects);

                        }
                        updateRecyclerView();
                    }
                })
                .addOnFailureListener(e -> {
                    Log.e("ProjectsFragment", "Error fetching projects data", e);
                    Toast.makeText(getContext(), "Error fetching data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });

    }

    private void updateRecyclerView() {
        if (adapter == null) {
            adapter = new ProjectsAdapter(projectsList, getContext());
            recyclerView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    private Projects retrieveFromDB(DocumentSnapshot document) {
        String projectsName = document.get("projectsName", String.class);
        String projectsDesc = document.get("projectsDesc", String.class);
        String projectsGoal = document.get("projectsGoals", String.class);
        String imageUrl = document.get("imageUrl", String.class);

        // Retrieve Timestamps
        com.google.firebase.Timestamp startTimestamp = document.getTimestamp("startDate");
        com.google.firebase.Timestamp endTimestamp = document.getTimestamp("endDate");

        // Convert Timestamps to Strings (if needed)
        String startDate = formatTimestamp(startTimestamp);
        String endDate = formatTimestamp(endTimestamp);

        Long progressLong = document.getLong("progressRate");
        int progress = progressLong != null ? progressLong.intValue() : 0;

        return new Projects(projectsName, projectsDesc, projectsGoal, startDate, endDate, progress, imageUrl);

    }

    private String formatTimestamp(com.google.firebase.Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("d MMM yyyy", Locale.getDefault());
        return sdf.format(timestamp.toDate());
    }

}