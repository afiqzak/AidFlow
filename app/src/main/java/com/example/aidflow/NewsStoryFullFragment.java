package com.example.aidflow;

import android.annotation.SuppressLint;
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
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewsStoryFullFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsStoryFullFragment extends Fragment {

    private NewsStoryViewModel newsStoryViewModel;

    // Fragment initialization parameters
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // Parameters for the fragment
    private String mParam1;
    private String mParam2;

    public NewsStoryFullFragment() {
        // Required empty public constructor
    }

    /**
     * Factory method to create a new instance of this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewsStoryFullFragment.
     */
    public static NewsStoryFullFragment newInstance(String param1, String param2) {
        NewsStoryFullFragment fragment = new NewsStoryFullFragment();
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
        return inflater.inflate(R.layout.fragment_news_story_full, container, false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        // Initialize UI components
        Button btnBack = view.findViewById(R.id.btnBackNews);
        TextView username = view.findViewById(R.id.user_name);
        TextView description = view.findViewById(R.id.story_desc);
        ImageView profileIV = view.findViewById(R.id.user_image);
        ImageView storyIV = view.findViewById(R.id.story_image);

        // Initialize ViewModel
        newsStoryViewModel = new ViewModelProvider(requireActivity()).get(NewsStoryViewModel.class);

        // Observe the selected story and update UI
        newsStoryViewModel.getSelectedStory().observe(getViewLifecycleOwner(), story -> {
            if (story != null) {
                // Load story image
                Glide.with(this)
                        .load(story.getImageUrl())
                        .placeholder(R.drawable.default_image_news)
                        .into(storyIV);

                // Load user profile image
                Glide.with(this)
                        .load(story.getUserImageUrl())
                        .placeholder(R.drawable.default_image_news)
                        .transform(new CircleCrop())
                        .into(profileIV);

                // Set username and description
                username.setText("  " + story.getUsername());
                description.setText(story.getDescription());
            } else {
                Log.d("StoryFullFragment", "Story is null");
            }
        });

        // Set up back button click listener
        View.OnClickListener OCLBack = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.destHome);
                newsStoryViewModel.getSelectedStory().setValue(null);
            }
        };
        btnBack.setOnClickListener(OCLBack);
    }
}
