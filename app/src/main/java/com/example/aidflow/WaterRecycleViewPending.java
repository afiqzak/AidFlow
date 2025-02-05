package com.example.aidflow;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WaterRecycleViewPending#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WaterRecycleViewPending extends Fragment {

    // Fragment initialization parameters
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // Parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerView;
    private WaterPendingAdapter adapter;
    private WaterViewModel waterViewModel;

    public WaterRecycleViewPending() {
        // Required empty public constructor
    }

    /**
     * Factory method to create a new instance of this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WaterRecycleViewPending.
     */
    public static WaterRecycleViewPending newInstance(String param1, String param2) {
        WaterRecycleViewPending fragment = new WaterRecycleViewPending();
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_water_recycle_view_pending, container, false);

        // Get current user id
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.recyclerViewPending);

        // Initialize ViewModel
        waterViewModel = new ViewModelProvider(this).get(WaterViewModel.class);

        // Fetch pending reports for the current user
        waterViewModel.fetchPendingReport(userId);

        // Observe changes in pending reports and update RecyclerView
        waterViewModel.getPendingReport().observe(getViewLifecycleOwner(), pendingReport -> {
            adapter = new WaterPendingAdapter(getContext(), pendingReport);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(adapter);
        });

        return view;
    }

}
