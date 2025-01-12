package com.example.aidflow;

import android.media.Image;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
 * Use the {@link StoryFullFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StoryFullFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StoryFullFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StoryFullFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StoryFullFragment newInstance(String param1, String param2) {
        StoryFullFragment fragment = new StoryFullFragment();
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
        return inflater.inflate(R.layout.fragment_story_full, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Button btnBack = view.findViewById(R.id.btnBack);
        TextView username = view.findViewById(R.id.user_name);
        TextView description = view.findViewById(R.id.story_desc);
        ImageView profileIV = view.findViewById(R.id.user_image);
        ImageView storyIV = view.findViewById(R.id.story_image);

        Bundle bundle = getArguments();
        if (bundle != null) {
            String storyUsername = bundle.getString("username");
            String storyDesc = bundle.getString("description");
            String imageUrl = bundle.getString("imageUrl");
            String userImageUrl = bundle.getString("userImageUrl");

            Glide.with(this)
                    .load(imageUrl)
                    .placeholder(R.drawable.default_image_news)
                    .into(storyIV);

            Glide.with(this)
                    .load(userImageUrl)
                    .placeholder(R.drawable.default_image_news)
                    .transform(new CircleCrop())
                    .into(profileIV);

            username.setText("  " + storyUsername);
            description.setText(storyDesc);
        } else {
            Log.d("StoryFullFragment", "Bundle is null");
        }


        View.OnClickListener OCLBack = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.destHome);
            }
        };
        btnBack.setOnClickListener(OCLBack);
    }
}
