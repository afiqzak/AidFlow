package com.example.aidflow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        Button history_button = (Button) view.findViewById(R.id.Donation_button);
        history_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FragmentTransaction fr = requireActivity().getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.FragmentViewMain,new DonationFragment());
                fr.addToBackStack(null);
                        fr.commit();
            }
        });

        FloatingActionButton fab_history = view.findViewById(R.id.fab_history);
        fab_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = requireActivity().getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.FragmentViewMain,new HistoryFilterFragment());
                fr.addToBackStack(null);
                fr.commit();            }
        });

        return view;

    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_history);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Sample data
        List<History> historyList = new ArrayList<>();
        historyList.add(new History("noy","bagi makan kucing","community project","21 Feb", 1000,"cash"))   ;
        historyList.add(new History("noy","bagi makan kucing","community project","21 Feb", 1000,"cash"))   ;
        historyList.add(new History("noy","bagi makan kucing","community project","21 Feb", 1000,"cash"))   ;
        historyList.add(new History("noy","bagi makan kucing","community project","21 Feb", 1000,"cash"))   ;
        historyList.add(new History("noy","bagi makan kucing","community project","21 Feb", 1000,"cash"))   ;
        historyList.add(new History("noy","bagi makan kucing","community project","21 Feb", 1000,"cash"))   ;
        historyList.add(new History("noy","bagi makan kucing","community project","21 Feb", 1000,"cash"))   ;
        historyList.add(new History("noy","bagi makan kucing","community project","21 Feb", 1000,"cash"))   ;
        historyList.add(new History("noy","bagi makan kucing","community project","21 Feb", 1000,"cash"))   ;


        // Attach adapter to RecyclerView
        HistoryAdapter adapter = new HistoryAdapter(historyList);
        recyclerView.setAdapter(adapter);



    }
}