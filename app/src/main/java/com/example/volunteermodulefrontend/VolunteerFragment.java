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

public class VolunteerFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_volunteer, container, false);



        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_volunteer);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

//        // Sample data
        List<Volunteer> volunteerList = new ArrayList<>();
//
        volunteerList.add(new Volunteer("Beach Cleaning","Sabak Bernam","5 July",20));
        volunteerList.add(new Volunteer("Beach Cleaning","Sabak Bernam","5 July",40));
        volunteerList.add(new Volunteer("Beach Cleaning","Sabak Bernam","5 July",40));
        volunteerList.add(new Volunteer("Beach Cleaning","Sabak Bernam","5 July",20));
        volunteerList.add(new Volunteer("Beach Cleaning","Sabak Bernam","5 July",40));
        volunteerList.add(new Volunteer("Beach Cleaning","Sabak Bernam","5 July",40));
        volunteerList.add(new Volunteer("Beach Cleaning","Sabak Bernam","5 July",20));
        volunteerList.add(new Volunteer("Beach Cleaning","Sabak Bernam","5 July",40));
        volunteerList.add(new Volunteer("Beach Cleaning","Sabak Bernam","5 July",40));
        volunteerList.add(new Volunteer("Beach Cleaning","Sabak Bernam","5 July",20));
        volunteerList.add(new Volunteer("Beach Cleaning","Sabak Bernam","5 July",40));
        volunteerList.add(new Volunteer("Beach Cleaning","Sabak Bernam","5 July",40));

//        // Attach adapter to RecyclerView
        VolunteerAdapter adapter = new VolunteerAdapter(volunteerList,getContext());
        recyclerView.setAdapter(adapter);



    }

}