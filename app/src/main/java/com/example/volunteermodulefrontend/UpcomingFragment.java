package com.example.volunteermodulefrontend;

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

public class UpcomingFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_upcoming, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_upcoming);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

//        // Sample data
        List<Upcoming> upcomingList = new ArrayList<>();
//
        upcomingList.add(new Upcoming("Beach Cleaning","2 May",
                "The Agathians Shelter, No. 22, Jalan Kelah 8/6, Seksyen 8, 46050 Petaling Jaya, Selangor, Malaysia","03-1234-5678","Abu Siddiq"));
        upcomingList.add(new Upcoming("Beach Cleaning","2 May",
                "The Agathians Shelter, No. 22, Jalan Kelah 8/6, Seksyen 8, 46050 Petaling Jaya, Selangor, Malaysia","03-1234-5678","Abu Siddiq"));
        upcomingList.add(new Upcoming("Beach Cleaning","2 May",
                "The Agathians Shelter, No. 22, Jalan Kelah 8/6, Seksyen 8, 46050 Petaling Jaya, Selangor, Malaysia","03-1234-5678","Abu Siddiq"));
        upcomingList.add(new Upcoming("Beach Cleaning","2 May",
                "The Agathians Shelter, No. 22, Jalan Kelah 8/6, Seksyen 8, 46050 Petaling Jaya, Selangor, Malaysia","03-1234-5678","Abu Siddiq"));
        upcomingList.add(new Upcoming("Beach Cleaning","2 May",
                "The Agathians Shelter, No. 22, Jalan Kelah 8/6, Seksyen 8, 46050 Petaling Jaya, Selangor, Malaysia","03-1234-5678","Abu Siddiq"));
        upcomingList.add(new Upcoming("Beach Cleaning","2 May",
                "The Agathians Shelter, No. 22, Jalan Kelah 8/6, Seksyen 8, 46050 Petaling Jaya, Selangor, Malaysia","03-1234-5678","Abu Siddiq"));
        upcomingList.add(new Upcoming("Beach Cleaning","2 May",
                "The Agathians Shelter, No. 22, Jalan Kelah 8/6, Seksyen 8, 46050 Petaling Jaya, Selangor, Malaysia","03-1234-5678","Abu Siddiq"));
        upcomingList.add(new Upcoming("Beach Cleaning","2 May",
                "The Agathians Shelter, No. 22, Jalan Kelah 8/6, Seksyen 8, 46050 Petaling Jaya, Selangor, Malaysia","03-1234-5678","Abu Siddiq"));
        upcomingList.add(new Upcoming("Beach Cleaning","2 May",
                "The Agathians Shelter, No. 22, Jalan Kelah 8/6, Seksyen 8, 46050 Petaling Jaya, Selangor, Malaysia","03-1234-5678","Abu Siddiq"));
        upcomingList.add(new Upcoming("Beach Cleaning","2 May",
                "The Agathians Shelter, No. 22, Jalan Kelah 8/6, Seksyen 8, 46050 Petaling Jaya, Selangor, Malaysia","03-1234-5678","Abu Siddiq"));
        upcomingList.add(new Upcoming("Beach Cleaning","2 May",
                "The Agathians Shelter, No. 22, Jalan Kelah 8/6, Seksyen 8, 46050 Petaling Jaya, Selangor, Malaysia","03-1234-5678","Abu Siddiq"));


//        // Attach adapter to RecyclerView
        UpcomingAdapter adapter = new UpcomingAdapter(upcomingList,getContext());
        recyclerView.setAdapter(adapter);



    }
}