package com.example.aidflow;

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
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    private RadioGroup toggleProfile;
    private FirebaseFirestore db;
    private User user;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
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

        //observe view model for real time updates on user data
        UserViewModel userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
        userViewModel.getUser().observe(getViewLifecycleOwner(), user -> {
                    if (user != null) {
                        this.user = user;
                        bindData(view);
                    }
                });



        toggleProfile = view.findViewById(R.id.toggleProfile);

        toggleProfile.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.RBOverview) {
                // tukar ke overview
                switchFragment(new ProfileOverviewFragment());
            } else if (checkedId == R.id.RBBadges) {
                // tukar ke badges
                switchFragment(new ProfileBadgesFragment());
            }
        });

        switchFragment(new ProfileOverviewFragment());
    }

    private void switchFragment(Fragment fragment) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.FCVProfile, fragment);
        transaction.commit();
    }

    //bind all view in fragment_profile.xml
    private void bindData(View v){
        TextView TVUsername = v.findViewById(R.id.TVUsername);
        TextView TVFname = v.findViewById(R.id.TVFName);
        TextView TVLname = v.findViewById(R.id.TVLName);
        TextView TVEmail = v.findViewById(R.id.TVUserEmail);
        TextView TVPhone = v.findViewById(R.id.TVUserPhone);

        TVUsername.setText(user.getUsername());
        TVFname.setText(user.getFirstName());
        TVLname.setText(user.getLastName());
        TVEmail.setText(user.getEmail());
        TVPhone.setText(user.getPhone());
    }

}