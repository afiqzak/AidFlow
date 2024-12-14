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

public class WaterNotificationsFragment extends Fragment {

    private RecyclerView recyclerView;
    private NotificationsAdapter adapter;
    private List<String> notificationTitles;

    public WaterNotificationsFragment() {
        // constructor
    }

    //ni method utk isi notifications recycle view tu
    //sbb saya buat frontend sebagai placeholder je
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_water_notifications, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewNoti);

        // placeholder sementara (tukar ah backend nanti)
        notificationTitles = new ArrayList<>();
        notificationTitles.add("Resolution Effectiveness");
        notificationTitles.add("Service Quality");
        notificationTitles.add("Delivery Time");

        // takyah kacau
        adapter = new NotificationsAdapter(getContext(), notificationTitles);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        return view;
    }

}
