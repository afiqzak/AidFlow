package com.example.aidflow;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewsFullPage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsFullPage extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NewsFullPage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewsFullPage.
     */
    // TODO: Rename and change types and number of parameters
    public static NewsFullPage newInstance(String param1, String param2) {
        NewsFullPage fragment = new NewsFullPage();
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
        return inflater.inflate(R.layout.fragment_news_full_page, container, false);


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Button btnBack = view.findViewById(R.id.btnBack);
        ImageView newsImage = view.findViewById(R.id.IVNewsFull);
        //ImageView publisherImage = view.findViewById(R.id.IvPublisherPic);
        TextView newsTitle = view.findViewById(R.id.TVTitleNews);
        TextView newsDescription = view.findViewById(R.id.TVNewsDesc);
        TextView publisherName = view.findViewById(R.id.TVPublisherName);

        // Retrieve the Bundle data
        Bundle bundle = getArguments();
        if (bundle != null) {
            String title = bundle.getString("title");
            String description = bundle.getString("description");
            String imageUrl = bundle.getString("imageUrl");
            String pubName = bundle.getString("publisherName");

            newsTitle.setText(title);
            newsDescription.setText(description);
            publisherName.setText(pubName);

            Glide.with(this)
                    .load(imageUrl)
                    .placeholder(R.drawable.default_image_news)
                    .into(newsImage);
//
//            Glide.with(this)
//                    .load(publisherPicUrl)
//                    .placeholder(R.drawable.placeholder_image)  // Optional placeholder for publisher image
//                    .into(publisherImage);
        }




        View.OnClickListener OCLBack = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.destHome);
            }
        };
        btnBack.setOnClickListener(OCLBack);

    }
}