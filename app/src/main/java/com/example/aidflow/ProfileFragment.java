package com.example.aidflow;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // UI elements
    private RadioGroup toggleProfile;
    private ShapeableImageView IVProfile;
    private Button btnLogout;

    // Fragment initialization parameters
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Factory method to create a new instance of this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize UI elements
        btnLogout = view.findViewById(R.id.BtnLogout);

        // Get current user id
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        // Observe ViewModel for real-time updates on user data
        UserViewModel userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
        userViewModel.fetchUserData(userId);

        userViewModel.getUser().observe(getViewLifecycleOwner(), user -> {
            if (user != null) {
                // Update UI with user data
                Log.d("Fragment", user.toString());
                bindData(view, user);
            } else {
                // Handle the case where user data is null (e.g., show an error message)
                Log.d("Fragment", "User is null");
                TextView TVUsername = view.findViewById(R.id.TVUsername);
                TVUsername.setText("user not found");
            }
        });

        toggleProfile = view.findViewById(R.id.toggleProfile);

        // Set up toggle profile radio group listener
        toggleProfile.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.RBOverview) {
                switchFragment(new ProfileOverviewFragment());
            } else if (checkedId == R.id.RBBadges) {
                switchFragment(new ProfileBadgesFragment());
            }
        });

        // Default fragment
        switchFragment(new ProfileOverviewFragment());

        // Set up logout button listener
        btnLogout.setOnClickListener(v -> {
            // Sign out from Firebase
            FirebaseAuth.getInstance().signOut();

            // Clear the saved user session or token
            SharedPreferences preferences = requireActivity().getSharedPreferences("user_session", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.apply();

            // Show a toast message to inform the user
            Toast.makeText(requireContext(), "Logged out successfully", Toast.LENGTH_SHORT).show();

            // Redirect to LoginActivity
            Intent intent = new Intent(requireActivity(), LoginSignupActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            requireActivity().finish();
        });
    }

    /**
     * Switches the current fragment to the specified fragment.
     *
     * @param fragment The fragment to switch to.
     */
    private void switchFragment(Fragment fragment) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.FCVProfile, fragment);
        transaction.commit();
    }

    /**
     * Binds user data to the UI elements.
     *
     * @param v The view containing the UI elements.
     * @param user The user data to bind.
     */
    private void bindData(View v, User user) {
        TextView TVUsername = v.findViewById(R.id.TVUsername);
        TextView TVFname = v.findViewById(R.id.TVFName);
        TextView TVLname = v.findViewById(R.id.TVLName);
        TextView TVEmail = v.findViewById(R.id.TVUserEmail);
        TextView TVPhone = v.findViewById(R.id.TVUserPhone);
        IVProfile = v.findViewById(R.id.IVProfile);

        // Set user data to UI elements
        TVUsername.setText(user.getUsername());
        TVFname.setText(user.getFirstName());
        TVLname.setText(user.getLastName());
        TVEmail.setText(user.getEmail());
        TVPhone.setText(user.getPhone());

        // Load user profile image
        if (user.getImageUrl() != null) {
            Glide.with(requireContext())
                    .load(user.getImageUrl())
                    .placeholder(R.drawable.default_image_news)
                    .into(IVProfile);
        } else {
            Log.e("ProfileFragment", "No image to display");
        }
    }
}