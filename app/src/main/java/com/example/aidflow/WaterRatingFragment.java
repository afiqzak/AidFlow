package com.example.aidflow;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WaterRatingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WaterRatingFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public WaterRatingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DogFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WaterRatingFragment newInstance(String param1, String param2) {
        WaterRatingFragment fragment = new WaterRatingFragment();
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


    //button function semua
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Button btnBack = view.findViewById(R.id.btnBack2);
        View.OnClickListener OCLBack = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.destWaterQuality);
            }
        };
        btnBack.setOnClickListener(OCLBack);
    }


    //ni method utk isi rating recycle view tu
    //sama gak ni placeholder sementara je lu
    private RecyclerView recyclerView;
    private RatingAdapter adapter;
    private List<String> ratingTitles;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_water_rating, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewRate);

        // Sample data
        ratingTitles = new ArrayList<>();
        ratingTitles.add("Resolution Effectiveness");
        ratingTitles.add("Service Quality");
        ratingTitles.add("Delivery Time");

        // takyah kacau
        adapter = new RatingAdapter(getContext(), ratingTitles);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        return view;
    }
}