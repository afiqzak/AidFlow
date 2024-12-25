package com.example.aidflow;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class DonationFormFragment extends Fragment {

    private String donationID;
    private boolean isAnonymous = false;
    private String selectedMethod;
    private EditText amountText, additionalText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_donation_form, container, false);

        BottomNavigationView bottomNav = getActivity().findViewById(R.id.bottomNavigationView);

        if (bottomNav != null) {
            bottomNav.setVisibility(View.GONE);
        }

        Bundle bundle = getArguments();
        if(bundle != null){
            donationID = bundle.getString("donationID");
            String name = bundle.getString("name");
            String projectName = bundle.getString("projectName");
            String dueDate = bundle.getString("dueDate");
            int progress = bundle.getInt("progress");
            String urgency = bundle.getString("urgency");
            String description = bundle.getString("description");
            String organizationName = bundle.getString("organizationName");
            long targetDonationAmount = bundle.getLong("targetDonationAmount");
            long currentDonationAmount = bundle.getLong("currentDonationAmount");

            TextView nameTV = view.findViewById(R.id.title_donate);
            TextView projectNameTV = view.findViewById(R.id.projectName_donate);
            TextView donationDescTV = view.findViewById(R.id.description);
            TextView donationNameTV = view.findViewById(R.id.donationName_donate);
            TextView organizerTV = view.findViewById(R.id.organizerName_donate);
            TextView urgencyTV = view.findViewById(R.id.urgencyIndicator_donate);
            TextView dueDateTV = view.findViewById(R.id.dueDate_donate);
            TextView progressTV = view.findViewById(R.id.progressText);
            additionalText = view.findViewById(R.id.additionalText);

            Log.d("DonationFormFragment", "Target: " + targetDonationAmount + ", Current: " + currentDonationAmount);

            // Set urgency color
            switch (urgency) {
                case "high":
                    view.findViewById(R.id.urgencyIndicator_donate).setBackgroundResource(R.drawable.red_urgency);
                    break;
                case "medium":
                    view.findViewById(R.id.urgencyIndicator_donate).setBackgroundResource(R.drawable.green_urgency);
                    break;
                case "low":
                    view.findViewById(R.id.urgencyIndicator_donate).setBackgroundResource(R.drawable.yellow_urgency);
                    break;
            }

            nameTV.setText(name);
            projectNameTV.setText(projectName);
            donationDescTV.setText(description);
            donationNameTV.setText(projectName);
            dueDateTV.setText(dueDate);
            urgencyTV.setText(urgency);
            organizerTV.setText(organizationName);
            progressTV.setText(String.format("This charity has raised RM%d out of RM%d", currentDonationAmount, targetDonationAmount));
        } else {
            Log.e("DonationFormFragment", "No arguments passed to fragment");
        }


        // Donation amount Button Setup
        Button amount_1 = view.findViewById(R.id.amount_1);
        Button amount_5 = view.findViewById(R.id.amount_5);
        Button amount_10 = view.findViewById(R.id.amount_10);
        Button amount_20 = view.findViewById(R.id.amount_20);
        Button amount_50 = view.findViewById(R.id.amount_50);
        Button amount_100 = view.findViewById(R.id.amount_100);

        amountText = view.findViewById(R.id.amountText);

        amount_1.setOnClickListener(v -> updateAmountText(amountText, 1));
        amount_5.setOnClickListener(v -> updateAmountText(amountText, 5));
        amount_10.setOnClickListener(v -> updateAmountText(amountText, 10));
        amount_20.setOnClickListener(v -> updateAmountText(amountText, 20));
        amount_50.setOnClickListener(v -> updateAmountText(amountText, 50));
        amount_100.setOnClickListener(v -> updateAmountText(amountText, 100));


        amountText.setOnEditorActionListener((v, actionId, event) -> {
            String input = amountText.getText().toString().trim();
            if (!input.isEmpty()) {
                try {
                    int customValue = Integer.parseInt(input);
                    updateAmountText(amountText, customValue);
                } catch (NumberFormatException e) {
                    Toast.makeText(getContext(), "Please enter a valid number", Toast.LENGTH_SHORT).show();
                }
            }
            return true;
        });

        // Back Button Setup
        ImageView back_button = view.findViewById(R.id.back_icon);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr = requireActivity().getSupportFragmentManager().beginTransaction();
//                fr.replace(R.id.FCVDonation,new DonationFragment());
                fr.replace(R.id.FragmentViewMain,new DonationFragment());
                fr.addToBackStack(null);
                fr.commit();
                BottomNavigationView bottomNav = getActivity().findViewById(R.id.bottomNavigationView);
                if (bottomNav != null) {
                    bottomNav.setVisibility(View.VISIBLE);
                }
            }
        });

        // Donation Method Radio Button Setup
        RadioButton fpxMethod = view.findViewById(R.id.fpx_method);
        RadioButton cardMethod = view.findViewById(R.id.card_method);
        RadioButton duitNowMethod = view.findViewById(R.id.DuitNow_method);
        RadioButton otherMethod = view.findViewById(R.id.other_method);

        View.OnClickListener radioButtonClickListener = v -> {

            fpxMethod.setChecked(false);
            cardMethod.setChecked(false);
            duitNowMethod.setChecked(false);
            otherMethod.setChecked(false);

            ((RadioButton) v).setChecked(true);

            selectedMethod = "";
            if (v.getId() == R.id.fpx_method) {
                selectedMethod = "FPX";
            } else if (v.getId() == R.id.card_method) {
                selectedMethod = "Card";
            } else if (v.getId() == R.id.DuitNow_method) {
                selectedMethod = "DuitNow QR";
            } else if (v.getId() == R.id.other_method) {
                selectedMethod = "Other";
            } else {
                Log.e("PaymentMethod", "Unknown RadioButton selected!");
                return;
            }

        };

        fpxMethod.setOnClickListener(radioButtonClickListener);
        cardMethod.setOnClickListener(radioButtonClickListener);
        duitNowMethod.setOnClickListener(radioButtonClickListener);
        otherMethod.setOnClickListener(radioButtonClickListener);

        // Anonymous Radio Button Setup
        RadioButton yesAnon = view.findViewById(R.id.yes_anonymous);
        RadioButton noAnon = view.findViewById(R.id.no_anonymous);
        noAnon.setChecked(true);

        View.OnClickListener anonClickListener = v -> {
            if (v.getId() == R.id.yes_anonymous) {
                isAnonymous = true;
                yesAnon.setChecked(true);
                noAnon.setChecked(false);
                Log.d("DonationForm", "User selected anonymous donation");
            } else if (v.getId() == R.id.no_anonymous) {
                isAnonymous = false;
                yesAnon.setChecked(false);
                noAnon.setChecked(true);
                Log.d("DonationForm", "User selected non-anonymous donation");
            }
        };

        yesAnon.setOnClickListener(anonClickListener);
        noAnon.setOnClickListener(anonClickListener);

        // Donation Button Setup
        LinearLayout donationButton_donate = view.findViewById(R.id.donationButton_donate);
        donationButton_donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateDonationForm()) {
                    submitDonation(donationID);
                    FragmentTransaction fr = requireActivity().getSupportFragmentManager().beginTransaction();
                    fr.replace(R.id.FragmentViewMain, new DonationFragment());
                    fr.addToBackStack(null);
                    fr.commit();
                    BottomNavigationView bottomNav = getActivity().findViewById(R.id.bottomNavigationView);
                    if (bottomNav != null) {
                        bottomNav.setVisibility(View.VISIBLE);
                    }
                }
            }
        });


        return view;
    }

    private void updateAmountText(EditText amountText, int amount) {
        amountText.setText(String.valueOf(amount));
    }

    private void submitDonation(String donationID) {
        // Retrieve inputs dynamically
        String donationAmount = amountText.getText().toString().trim();
        String additionalMessage = additionalText.getText().toString().trim();

        long parsedAmount;
        try {
            parsedAmount = Long.parseLong(donationAmount);
            if (parsedAmount <= 0) {
                amountText.setError("Donation amount must be greater than zero.");
                amountText.requestFocus();
                return;
            }
        } catch (NumberFormatException e) {
            amountText.setError("Please enter a valid number.");
            amountText.requestFocus();
            return;
        }

        if (donationID == null) {
            Toast.makeText(getContext(), "Donation ID is missing!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Prepare data for submission
        Map<String, Object> donationSubmit = new HashMap<>();
        donationSubmit.put("donationID", donationID);
        donationSubmit.put("donationAmount", parsedAmount);
        donationSubmit.put("donateAnonymously", isAnonymous);
        donationSubmit.put("additionalMessage", additionalMessage.isEmpty() ? "-" : additionalMessage);
        donationSubmit.put("selectedMethod", selectedMethod);

        // Submit to Firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("donationSubmit")
                .add(donationSubmit)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(getContext(), "Donation submitted successfully!", Toast.LENGTH_SHORT).show();
                    clearFields();
                })
                .addOnFailureListener(e -> {
                    Log.e("FirestoreError", "Failed to submit donation: " + e.getMessage(), e);
                    Toast.makeText(getContext(), "Failed to submit donation: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    private boolean validateDonationForm() {


        String donationAmount = amountText.getText().toString().trim();
        if (donationAmount.isEmpty()) {
            Toast.makeText(getContext(), "Please insert a valid amount.", Toast.LENGTH_SHORT).show();
            return false;
        }

        try {
            long parsedAmount = Long.parseLong(donationAmount);
            if (parsedAmount <= 0) {
                Toast.makeText(getContext(), "Donation amount must be greater than zero.", Toast.LENGTH_SHORT).show();
                return false;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(getContext(), "Please enter a valid number.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (selectedMethod == null || selectedMethod.isEmpty()) {
            Toast.makeText(getContext(), "Please select a payment method.", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }


    private void clearFields() {
        amountText.setText("");
        additionalText.setText("");
    }


}
