package com.example.aidflow;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WaterRecycleViewPending#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WaterRecycleViewPending extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public WaterRecycleViewPending() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecycleViewPending.
     */
    // TODO: Rename and change types and number of parameters
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

    //ni method utk isi rating recycle view tu
    //sama gak ni placeholder sementara je lu
    private RecyclerView recyclerView;
    private WaterPendingAdapter adapter;
    private List<String> pendingTitles;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_water_recycle_view_pending, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewPending);

        // Sample data
        pendingTitles = new ArrayList<>();
        pendingTitles.add("Resolution Effectiveness");
        pendingTitles.add("Service Quality");
        pendingTitles.add("Delivery Time");

        // takyah kacau
        adapter = new WaterPendingAdapter(getContext(), pendingTitles);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        return view;
    }

    public static class main_donation_Fragment extends Fragment {


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment


            View view =  inflater.inflate(R.layout.fragment_donation, container, false);

            Button history_button = view.findViewById(R.id.History_button);
            Button donation_button = (Button) view.findViewById(R.id.Donation_button);
            history_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    FloatingActionButton fab_donate;
                    FloatingActionButton fab_history;
                    donation_button.setAlpha(0.5F);
                    history_button.setAlpha(1.0F);


                    fab_donate = view.findViewById(R.id.fab_donatefilter);
                    fab_history = view.findViewById(R.id.fab_history);

                    // Make FAB2 visible, FAB1 invisible
                    fab_donate.setVisibility(View.GONE);
                    fab_history.setVisibility(View.VISIBLE);

                    FragmentTransaction fr = requireActivity().getSupportFragmentManager().beginTransaction();
                    fr.replace(R.id.FCVDonation,new DonationHistoryFragment());
                    fr.addToBackStack(null);
                    fr.commit();
                }
            });

            FloatingActionButton fab = view.findViewById(R.id.fab_donatefilter);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DonationDonateFilterFragment floatingLayout = new DonationDonateFilterFragment();
                    floatingLayout.show(requireActivity().getSupportFragmentManager().beginTransaction(), "FloatingLayout");


                }
            });


            donation_button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    FloatingActionButton fab_donate;
                    FloatingActionButton fab_history;
                    history_button.setAlpha(0.5F);
                    donation_button.setAlpha(1.0F);


                    fab_donate = view.findViewById(R.id.fab_donatefilter);
                    fab_history = view.findViewById(R.id.fab_history);

                    // Make FAB2 visible, FAB1 invisible
                    fab_history.setVisibility(View.GONE);
                    fab_donate.setVisibility(View.VISIBLE);

                    FragmentTransaction fr = requireActivity().getSupportFragmentManager().beginTransaction();
                    fr.replace(R.id.FCVDonation,new DonationDonateFragment());
                    fr.addToBackStack(null);
                    fr.commit();
                }
            });

            FloatingActionButton fab1 = view.findViewById(R.id.fab_history);
            fab1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentTransaction fr = requireActivity().getSupportFragmentManager().beginTransaction();
                    fr.replace(R.id.FCVDonation,new DonationHistFilterFragment());
                    fr.addToBackStack(null);
                    fr.commit();            }
            });


            if (savedInstanceState == null) {
                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.FCVDonation, new DonationDonateFragment());
                transaction.commit();

                FloatingActionButton fab_donate;
                FloatingActionButton fab_history;
                history_button.setAlpha(0.5F);
                donation_button.setAlpha(1.0F);

                fab_donate = view.findViewById(R.id.fab_donatefilter);
                fab_history = view.findViewById(R.id.fab_history);

                // Make FAB2 visible, FAB1 invisible
                fab_donate.setVisibility(View.VISIBLE);
                fab_history.setVisibility(View.GONE);

            }

            return view;
        }



    }
}