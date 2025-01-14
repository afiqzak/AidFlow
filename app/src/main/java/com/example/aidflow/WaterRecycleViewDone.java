package com.example.aidflow;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WaterRecycleViewDone#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WaterRecycleViewDone extends Fragment {

    // Fragment initialization parameters
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // Parameters
    private String mParam1;
    private String mParam2;

    public WaterRecycleViewDone() {
        // Required empty public constructor
    }

    /**
     * Factory method to create a new instance of this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WaterRecycleViewDone.
     */
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

    // RecyclerView and Adapter for displaying done reports
    private RecyclerView recyclerView;
    private WaterDoneAdapter adapter;
    private WaterViewModel waterViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_water_recycle_view_done, container, false);

        // Get current user id
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.recyclerViewDone);

        // Initialize ViewModel
        waterViewModel = new ViewModelProvider(requireActivity()).get(WaterViewModel.class);

        // Fetch done reports for the current user
        waterViewModel.fetchDoneReport(userId);

        // Observe changes in done reports and update RecyclerView
        waterViewModel.getDoneReport().observe(getViewLifecycleOwner(), doneReport -> {
            adapter = new WaterDoneAdapter(getContext(), doneReport, waterViewModel);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(adapter);
        });

        return view;
    }

}