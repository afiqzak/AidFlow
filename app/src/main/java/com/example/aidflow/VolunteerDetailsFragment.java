package com.example.aidflow;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.WriteBatch;

import java.util.HashMap;
import java.util.Map;


public class VolunteerDetailsFragment extends Fragment {

    private TextView TVEventTitle,TVEventDate,TVEventLocation,TVEventContact,TVPIC,TVEventDesc;
    private VolunteerViewModel volunteerViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_volunteer_details, container, false);

        //get current user id
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        volunteerViewModel = new ViewModelProvider(requireActivity()).get(VolunteerViewModel.class);

        Button joinButton = view.findViewById(R.id.BtnJoinCancel);

        TVEventTitle = view.findViewById(R.id.TVEventTitle);
        TVEventDate = view.findViewById(R.id.TVEventDate);
        TVEventLocation = view.findViewById(R.id.TVEventLocation);
        TVEventContact = view.findViewById(R.id.TVEventContact);
        TVPIC = view.findViewById(R.id.TVPIC);
        TVEventDesc = view.findViewById(R.id.TVEventDesc);

        volunteerViewModel.getSelectedVolunteer().observe(getViewLifecycleOwner(), volunteer -> {
            if (volunteer != null) {
                boolean isJoined = volunteerViewModel.getJoinedID().contains(volunteer.getVolunteerID());
                joinButton.setText(isJoined ? "CANCEL" : "JOIN");
                joinButton.setBackgroundTintList(isJoined ? ColorStateList.valueOf(Color.parseColor("#FF0000")) : ColorStateList.valueOf(Color.parseColor("#00E74D")));

                TVEventTitle.setText(volunteer.getEventTitle());
                TVEventDate.setText(volunteer.getEventDate());
                TVEventLocation.setText(volunteer.getLocation());
                TVEventContact.setText(volunteer.getContactPIC());
                TVPIC.setText(volunteer.getPIC());
                TVEventDesc.setText(volunteer.getDescription());

                joinButton.setOnClickListener(v1 -> {
                    FirebaseFirestore db = FirebaseFirestore.getInstance();

                    // Check if user has already joined
                    db.collection("volunteer_joined")
                            .whereEqualTo("userID", userId)
                            .whereEqualTo("eventID", volunteer.getVolunteerID())
                            .get()
                            .addOnSuccessListener(querySnapshot -> {
                                if (!querySnapshot.isEmpty()) {
                                    // User already joined, so cancel the join
                                    WriteBatch batch = db.batch();

                                    // Reference to the document to delete
                                    DocumentReference joinedDocRef = querySnapshot.getDocuments().get(0).getReference();
                                    batch.delete(joinedDocRef);

                                    // Reference to the volunteer document to decrement the count
                                    DocumentReference volunteerDocRef = db.collection("volunteer").document(volunteer.getVolunteerID());
                                    batch.update(volunteerDocRef, "numOfVolunteersApplied", FieldValue.increment(-1));

                                    // Commit the batch
                                    batch.commit()
                                            .addOnSuccessListener(aVoid -> {
                                                Log.d("Firestore", "User unjoined event successfully and volunteer count updated.");
                                                Toast.makeText(getContext(), "You unjoined the Volunteer", Toast.LENGTH_LONG).show();
                                                volunteerViewModel.getSelectedVolunteer().setValue(null);
                                                if (getFragmentManager() != null) {
                                                    getFragmentManager().popBackStack(); // Go back in the fragment transaction stack
                                                }
                                            })
                                            .addOnFailureListener(e -> {
                                                Log.e("Firestore Error", "Failed to unjoin event or update volunteer count", e);
                                                Toast.makeText(requireContext(), "Failed to unjoin: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                            });
                                } else {
                                    // User has not joined, so join the event
                                    // Map user data
                                    Map<String, Object> volunteerJoined = new HashMap<>();
                                    volunteerJoined.put("userID", userId);
                                    volunteerJoined.put("eventID", volunteer.getVolunteerID());
                                    volunteerJoined.put("status", false);

                                    // Start a Firestore batch
                                    WriteBatch batch = db.batch();

                                    // Reference to the new document in "volunteer_joined"
                                    DocumentReference joinedDocRef = db.collection("volunteer_joined").document();
                                    batch.set(joinedDocRef, volunteerJoined);

                                    // Reference to the volunteer document to increment the count
                                    DocumentReference volunteerDocRef = db.collection("volunteer").document(volunteer.getVolunteerID());
                                    batch.update(volunteerDocRef, "numOfVolunteersApplied", FieldValue.increment(1));

                                    // Commit the batch
                                    batch.commit()
                                            .addOnSuccessListener(aVoid -> {
                                                Log.d("Firestore", "User joined event successfully and volunteer count updated.");
                                                Toast.makeText(getContext(), "You joined the Volunteer", Toast.LENGTH_LONG).show();
                                                volunteerViewModel.getSelectedVolunteer().setValue(null);
                                                if (getFragmentManager() != null) {
                                                    getFragmentManager().popBackStack(); // Go back in the fragment transaction stack
                                                }
                                            })
                                            .addOnFailureListener(e -> {
                                                Log.e("Firestore Error", "Failed to join event or update volunteer count", e);
                                                Toast.makeText(requireContext(), "Failed to join: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                            });
                                }
                            })
                            .addOnFailureListener(e -> {
                                Log.e("Firestore Error", "Error checking if user already joined", e);
                                Toast.makeText(requireContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            });
                });
            }
        });

        ImageView back_button = view.findViewById(R.id.back_icon_volunteer);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager() != null) {
                    getFragmentManager().popBackStack(); // Go back in the fragment transaction stack
                }
            }
        });

        return view;
    }
}