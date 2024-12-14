package com.example.aidflow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DonationHistoryFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_donation_history, container, false);


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