package com.example.aidflow;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewsProjectsFullPage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsProjectsFullPage extends Fragment {

    private NewsProjectViewModel newsProjectViewModel;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NewsProjectsFullPage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProjectsFullPage.
     */
    // TODO: Rename and change types and number of parameters
    public static NewsProjectsFullPage newInstance(String param1, String param2) {
        NewsProjectsFullPage fragment = new NewsProjectsFullPage();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_projects_full_page, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        // Initialize views
        TextView projectsTitle = view.findViewById(R.id.projectFullTitle);
        TextView projectsDesc = view.findViewById(R.id.projectFullDesc);
        TextView TVProgress = view.findViewById(R.id.TVProgress);
        ProgressBar projectProgress = view.findViewById(R.id.projectFullProgressBar);
        ImageView projectIV = view.findViewById(R.id.projectFullImage);

        // Initialize ViewModel
        newsProjectViewModel = new ViewModelProvider(requireActivity()).get(NewsProjectViewModel.class);

        // Observe selected project and update UI
        newsProjectViewModel.getSelectedProject().observe(getViewLifecycleOwner(), project -> {
            Log.d("NewsProjectViewModel", "Selected Project: " + project.getProjectsName());
            Glide.with(this)
                    .load(project.getImageUrl())
                    .into(projectIV);

            projectsTitle.setText(project.getProjectsName());
            projectsDesc.setText(project.getProjectGoals().replaceAll("(\\d+\\.\\s)", "\n$1").trim());
            TVProgress.setText(project.getProgressRate() + "% to complete");
            projectProgress.setProgress(project.getProgressRate());
        });

        // Back Button Setup
        Button btnBack = view.findViewById(R.id.btnBackNews);
        View.OnClickListener OCLBack = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to home
                Navigation.findNavController(view).navigate(R.id.destHome);
            }
        };
        btnBack.setOnClickListener(OCLBack);
    }
}