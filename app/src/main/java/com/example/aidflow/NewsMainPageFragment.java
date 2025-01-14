package com.example.aidflow;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.Manifest;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewsMainPageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsMainPageFragment extends Fragment {

    // Fragment initialization parameters
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public NewsMainPageFragment() {
        // Required empty public constructor
    }

    /**
     * Factory method to create a new instance of this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewsMainPageFragment.
     */
    public static NewsMainPageFragment newInstance(String param1, String param2) {
        NewsMainPageFragment fragment = new NewsMainPageFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_main_page, container, false);
    }

    private RadioGroup toggleGroup;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize camera button
        Button cameraButton = view.findViewById(R.id.cameraButton);

        // Register a launcher for the camera intent
        ActivityResultLauncher<Intent> cameraLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == requireActivity().RESULT_OK && result.getData() != null) {
                        Bitmap photo = (Bitmap) result.getData().getExtras().get("data");

                        // Pass the image as a Bundle to the next fragment
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("capturedImage", photo); // Bitmap is Parcelable
                        Navigation.findNavController(view).navigate(R.id.action_newsMainPageFragment_to_camFragment, bundle);
                    }
                }
        );

        // Set camera button click listener
        cameraButton.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_GRANTED) {
                // Permission is already granted; launch the camera
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraLauncher.launch(cameraIntent);
            } else {
                // Request Camera Permission
                requestPermissionLauncher.launch(Manifest.permission.CAMERA);
            }
        });

        // Initialize toggle group
        toggleGroup = view.findViewById(R.id.toggleNews);

        // Set toggle group checked change listener
        toggleGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.projectButton) {
                switchFragment(new NewsProjectsFragment());
            } else if (checkedId == R.id.newsButton) {
                switchFragment(new NewsView());
            }
        });

        // Default fragment to display
        switchFragment(new NewsProjectsFragment());
    }

    // Switch to the specified fragment
    private void switchFragment(Fragment fragment) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainerViewNews, fragment);
        transaction.commit();
    }

    // Request permission launcher for camera
    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    // Permission granted; notify the user
                    Toast.makeText(requireContext(), "Camera permission granted", Toast.LENGTH_SHORT).show();
                } else {
                    // Permission denied; notify the user
                    Toast.makeText(requireContext(), "Camera permission denied", Toast.LENGTH_SHORT).show();
                }
            });
}