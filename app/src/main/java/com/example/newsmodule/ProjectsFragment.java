package com.example.newsmodule;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

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
    private List<String> projectTitles, projectDesc, projectDate;
    private List<Integer> projectImages;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_projects, container, false);

        recyclerView = view.findViewById(R.id.projectList);

        // Sample data
        projectTitles = new ArrayList<>();
        projectTitles.add("Project 1");
        projectTitles.add("Project 2");
        projectTitles.add("Project 3");
        projectTitles.add("Project 4");


        projectDesc = new ArrayList<>();
        projectDesc.add("Description 1");
        projectDesc.add("Description 2");
        projectDesc.add("Description 3");
        projectDesc.add("Description 4");

        projectDate = new ArrayList<>();
        projectDate.add("March 7 - March 10");
        projectDate.add("March 7 - March 10");
        projectDate.add("March 7 - March 10");
        projectDate.add("March 7 - March 10");

        projectImages = new ArrayList<>();
        projectImages.add(R.drawable.story1);
        projectImages.add(R.drawable.story2);
        projectImages.add(R.drawable.story3);
        projectImages.add(R.drawable.story1);


        // takyah kacau
        adapter = new ProjectsAdapter(getContext(), projectTitles, projectDesc, projectDate, projectImages);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        return view;
    }
}