package com.example.aidflow;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WaterRatingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WaterRatingFragment extends Fragment {

    // Fragment initialization parameters
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // Parameters
    private String mParam1;
    private String mParam2;

    public WaterRatingFragment() {
        // Required empty public constructor
    }

    /**
     * Factory method to create a new instance of this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WaterRatingFragment.
     */
    public static WaterRatingFragment newInstance(String param1, String param2) {
        WaterRatingFragment fragment = new WaterRatingFragment();
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

    private WaterViewModel waterViewModel;
    private ImageView IVRating;
    private SeekBar SBEffective, SBService, SBTime;
    private FirebaseFirestore db;
    private Button btnSubmit, btnBack;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db = FirebaseFirestore.getInstance();

        // Initialize views
        IVRating = view.findViewById(R.id.IVRating);
        SBEffective = view.findViewById(R.id.SBEffective);
        SBService = view.findViewById(R.id.SBService);
        SBTime = view.findViewById(R.id.SBTime);
        btnBack = view.findViewById(R.id.btnBack2);
        btnSubmit = view.findViewById(R.id.btnSubmit);

        // Set up back button click listener
        View.OnClickListener OCLBack = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.destWaterQuality);
            }
        };
        btnBack.setOnClickListener(OCLBack);

        // Initialize ViewModel
        waterViewModel = new ViewModelProvider(requireActivity()).get(WaterViewModel.class);

        // Observe selected report
        waterViewModel.getSelectedReport().observe(getViewLifecycleOwner(), waterReport -> {
            Log.d("WaterRatingFragment", "Selected report: " + waterReport.getComplaint());
            if (waterReport.getImageUrl() != null) {
                Glide.with(requireContext())
                        .load(waterReport.getImageUrl())
                        .placeholder(R.drawable.default_image_news)
                        .into(IVRating);
            } else {
                Log.e("ProjectsAdapter", "No image to display");
            }

            // Set up the submit button click listener
            btnSubmit.setOnClickListener(v -> {
                // Get the values from the seek bars
                int effectiveRating = SBEffective.getProgress();
                int serviceRating = SBService.getProgress();
                int timeRating = SBTime.getProgress();
                String reportID = waterReport.getReportID();

                // Log the values (optional)
                Log.d("WaterRatingFragment", "Effective Rating: " + effectiveRating);
                Log.d("WaterRatingFragment", "Service Rating: " + serviceRating);
                Log.d("WaterRatingFragment", "Time Rating: " + timeRating);

                // Create a map to store the ratings
                Map<String, Object> ratings = new HashMap<>();
                ratings.put("reportID", reportID);
                ratings.put("effectiveRating", effectiveRating);
                ratings.put("serviceRating", serviceRating);
                ratings.put("timeRating", timeRating);

                // Upload the ratings to Firestore
                db.collection("reportRate")
                        .add(ratings)
                        .addOnSuccessListener(documentReference -> {
                            Log.d("WaterRatingFragment", "Ratings successfully uploaded.");

                            // Update the `rate` field in the corresponding report document
                            db.collection("report")
                                    .document(reportID)
                                    .update("rate", true)
                                    .addOnSuccessListener(aVoid -> {
                                        Navigation.findNavController(view).navigate(R.id.destWaterQuality);
                                        Log.d("WaterRatingFragment", "Report rate field successfully updated.");
                                        Toast.makeText(requireContext(), "Ratings submitted successfully", Toast.LENGTH_SHORT).show();
                                    })
                                    .addOnFailureListener(e -> {
                                        Log.e("WaterRatingFragment", "Error updating report rate field", e);
                                        Toast.makeText(requireContext(), "Error updating report status", Toast.LENGTH_SHORT).show();
                                    });
                        })
                        .addOnFailureListener(e -> {
                            Log.e("WaterRatingFragment", "Error uploading ratings", e);
                            Toast.makeText(requireContext(), "Error submitting ratings", Toast.LENGTH_SHORT).show();
                        });
            });
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_water_rating, container, false);

        return view;
    }
}