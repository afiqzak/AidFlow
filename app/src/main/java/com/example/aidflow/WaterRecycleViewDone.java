package com.example.aidflow;

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
 * Use the {@link WaterRecycleViewDone#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WaterRecycleViewDone extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public WaterRecycleViewDone() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecycleViewDone.
     */
    // TODO: Rename and change types and number of parameters
    public static WaterRecycleViewDone newInstance(String param1, String param2) {
        WaterRecycleViewDone fragment = new WaterRecycleViewDone();
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

    //ni method utk isi rating recycle view tu
    //sama gak ni placeholder sementara je lu
    private RecyclerView recyclerView;
    private WaterDoneAdapter adapter;
    private List<String> pendingTitles;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_water_recycle_view_done, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewDone);

        // ni tempat isi data for cards
        pendingTitles = new ArrayList<>();
        pendingTitles.add("Resolution Effectiveness");
        pendingTitles.add("Service Quality");
        pendingTitles.add("Delivery Time");

        // ni utk adapter class java
        adapter = new WaterDoneAdapter(getContext(), pendingTitles);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        return view;
    }

}