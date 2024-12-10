package com.example.aidflow;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class HistoryFilterFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history_filter, container, false);

        ImageView close_button = view.findViewById(R.id.close_icon);
        close_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FragmentTransaction fr = requireActivity().getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.FragmentViewMain,new HistoryFragment());
                fr.addToBackStack(null);
                fr.commit();

            }
        });

        Button applyFilter_button = view.findViewById(R.id.apply_filter_button);
        applyFilter_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Filter Applied", Toast.LENGTH_LONG).show();
                FragmentTransaction fr = requireActivity().getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.FragmentViewMain,new HistoryFragment());
                fr.addToBackStack(null);
                fr.commit();
            }
        });

        return view;
    }
}