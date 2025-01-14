package com.example.aidflow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewsStoryView#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsStoryView extends Fragment {

    // Fragment initialization parameters
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // Parameters to store fragment arguments
    private String mParam1;
    private String mParam2;

    public NewsStoryView() {
        // Required empty public constructor
    }

    /**
     * Factory method to create a new instance of this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewsStoryView.
     */
    public static NewsStoryView newInstance(String param1, String param2) {
        NewsStoryView fragment = new NewsStoryView();
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

    // RecyclerView to display the list of news stories
    private RecyclerView recyclerView;
    // Adapter for the RecyclerView
    private NewsStoryAdapter adapter;
    // ViewModel to manage UI-related data
    private NewsStoryViewModel storyViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news_story_view, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.storyList);

        // Initialize ViewModel
        storyViewModel = new ViewModelProvider(requireActivity()).get(NewsStoryViewModel.class);

        // Fetch the list of stories
        storyViewModel.fetchStoryList();

        // Observe the story list and update the RecyclerView
        storyViewModel.getStoryList().observe(getViewLifecycleOwner(), stories -> {
            adapter = new NewsStoryAdapter(getContext(), stories, storyViewModel);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            recyclerView.setAdapter(adapter);
        });
    }
}