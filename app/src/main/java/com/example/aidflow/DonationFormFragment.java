package com.example.aidflow;

import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.appcompat.widget.PopupMenu;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.WriteBatch;

import java.util.HashMap;
import java.util.Map;


public class DonationFormFragment extends Fragment {
    private boolean isAnonymous = false;
    private String selectedMethod;
    private EditText ETAmount, additionalText;
    private TextView TVTitleDonForm, TVCategory, TVDesc, TVPICDonation, TVUrgency, TVDate, TVMoneyCollected;
    private ProgressBar PBMoney;
    private DonationViewModel donationViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_donation_form, container, false);
        TVTitleDonForm = view.findViewById(R.id.TVTitleDonForm);
        TVCategory = view.findViewById(R.id.TVCategory);
        TVDesc = view.findViewById(R.id.TVDesc);
        TVPICDonation = view.findViewById(R.id.TVPICDonation);
        TVUrgency = view.findViewById(R.id.TVUrgency);
        TVDate = view.findViewById(R.id.TVDate);
        TVMoneyCollected = view.findViewById(R.id.TVMoneyCollected);
        additionalText = view.findViewById(R.id.ETMeaningful);
        PBMoney = view.findViewById(R.id.PBMoney);

        donationViewModel = new ViewModelProvider(requireActivity()).get(DonationViewModel.class);

        donationViewModel.getSelectedDonation().observe(getViewLifecycleOwner(), donation -> {
            if (donation != null){
                double progress = donation.getCurrentAmount()/donation.getTargetAmount()*100;

                TVTitleDonForm.setText(donation.getDonationTitle());
                TVCategory.setText(donation.getCategory());
                TVDesc.setText(donation.getDescription());
                TVDate.setText(donation.getDueDate());
                TVUrgency.setText(donation.getUrgency());
                TVPICDonation.setText(donation.getPIC());
                PBMoney.setProgress((int) Math.round(progress));
                TVMoneyCollected.setText("This charity has raised RM " + String.valueOf(donation.getCurrentAmount()) + " out of RM " + String.valueOf(donation.getTargetAmount()));

                switch (donation.getUrgency()) {
                    case "High Priority":
                        TVUrgency.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getContext(), R.color.red)));
                        break;
                    case "Moderate":
                        TVUrgency.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getContext(), R.color.yellow)));
                        break;
                    case "Low Priority":
                        TVUrgency.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getContext(), R.color.green)));
                        break;
                }

                // Donation Button Setup
                Button donationButton_donate = view.findViewById(R.id.BtnDonate);
                donationButton_donate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (validateDonationForm()) {
                            FirebaseFirestore db = FirebaseFirestore.getInstance();

                            // Parse donation amount
                            double donationAmount = Double.parseDouble(ETAmount.getText().toString());

                            // Map payment data
                            Map<String, Object> paymentData = new HashMap<>();
                            paymentData.put("donationID", donation.getDonationID());
                            paymentData.put("userID", FirebaseAuth.getInstance().getCurrentUser().getUid());
                            paymentData.put("amount", donationAmount);
                            paymentData.put("paymentMethod", selectedMethod);
                            paymentData.put("extraNote", additionalText.getText().toString());
                            paymentData.put("anonymous", isAnonymous);
                            paymentData.put("donatedDate", Timestamp.now());

                            // Start a Firestore batch
                            WriteBatch batch = db.batch();

                            // Reference to the new document in "donation_payment"
                            DocumentReference paymentDocRef = db.collection("donation_payment").document();
                            batch.set(paymentDocRef, paymentData);

                            // Reference to the donation document to increment the currentAmount
                            DocumentReference donationDocRef = db.collection("donation").document(donation.getDonationID());
                            batch.update(donationDocRef, "currentAmount", FieldValue.increment(donationAmount));

                            // Commit the batch
                            batch.commit()
                                    .addOnSuccessListener(aVoid -> {
                                        Log.d("Firestore", "User donated successfully");
                                        Toast.makeText(getContext(), "Thank you for donating, wish you all the best!", Toast.LENGTH_LONG).show();
                                        donationViewModel.getSelectedDonation().setValue(null);
                                        if (getFragmentManager() != null) {
                                            getFragmentManager().popBackStack(); // Go back in the fragment transaction stack
                                        }
                                    })
                                    .addOnFailureListener(e -> {
                                        Log.e("Firestore Error", "Failed to donate", e);
                                        Toast.makeText(requireContext(), "Failed to donate: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                    });
                        }
                    }
                });
            }
        });

        // Donation amount Button Setup
        Button amount_5 = view.findViewById(R.id.BtnRM5);
        Button amount_10 = view.findViewById(R.id.BtnRM10);
        Button amount_20 = view.findViewById(R.id.BtnRM20);
        Button amount_50 = view.findViewById(R.id.BtnRM50);


        ETAmount = view.findViewById(R.id.ETAmount);

        amount_5.setOnClickListener(v -> updateAmountText(ETAmount, 5));
        amount_10.setOnClickListener(v -> updateAmountText(ETAmount, 10));
        amount_20.setOnClickListener(v -> updateAmountText(ETAmount, 20));
        amount_50.setOnClickListener(v -> updateAmountText(ETAmount, 50));


        ETAmount.setOnEditorActionListener((v, actionId, event) -> {
            String input = ETAmount.getText().toString().trim();
            if (!input.isEmpty()) {
                try {
                    int customValue = Integer.parseInt(input);
                    updateAmountText(ETAmount, customValue);
                } catch (NumberFormatException e) {
                    Toast.makeText(getContext(), "Please enter a valid number", Toast.LENGTH_SHORT).show();
                }
            }
            return true;
        });

        // Back Button Setup
        ImageView back_button = view.findViewById(R.id.IVBack);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager() != null) {
                    getFragmentManager().popBackStack(); // Go back in the fragment transaction stack
                }
            }
        });


        // Method Payment Dropbox Setup
        TextView paymentMethodTextView = view.findViewById(R.id.SpPayMethod);
        String[] paymentMethods = {"Credit Card", "PayPal", "Bank Transfer"};

        paymentMethodTextView.setOnClickListener(v -> {
            // Create a PopupMenu to show the payment methods
            PopupMenu popupMenu = new PopupMenu(getContext(), v);
            MenuInflater infl = popupMenu.getMenuInflater();
            Menu menu = popupMenu.getMenu();

            // Add the items from the payment methods array to the menu
            for (String method : paymentMethods) {
                menu.add(method);
            }

            // Set a listener for item selection
            popupMenu.setOnMenuItemClickListener(item -> {
                selectedMethod = item.getTitle().toString();
                Log.d("PaymentMethod", "Selected method: " + selectedMethod);
                paymentMethodTextView.setText(selectedMethod); // Set the selected method in the TextView
                return true;
            });

            // Show the popup menu
            popupMenu.show();
        });



        // Anonymous Radio Button Setup
        RadioButton yesAnon = view.findViewById(R.id.RBYes);
        RadioButton noAnon = view.findViewById(R.id.RBNo);
        noAnon.setChecked(true);

        View.OnClickListener anonClickListener = v -> {
            if (v.getId() == R.id.RBYes) {
                isAnonymous = true;
                yesAnon.setChecked(true);
                noAnon.setChecked(false);
                Log.d("DonationForm", "User selected anonymous donation");
            } else if (v.getId() == R.id.RBNo) {
                isAnonymous = false;
                yesAnon.setChecked(false);
                noAnon.setChecked(true);
                Log.d("DonationForm", "User selected non-anonymous donation");
            }
        };

        yesAnon.setOnClickListener(anonClickListener);
        noAnon.setOnClickListener(anonClickListener);

        return view;
    }

    private void updateAmountText(EditText amountText, int amount) {
        amountText.setText(String.valueOf(amount));
    }

//    private void submitDonation(String donationID) {
//
//        String donationAmount = ETAmount.getText().toString().trim();
//        String additionalMessage = additionalText.getText().toString().trim();
//
//
//        long parsedAmount;
//        try {
//            parsedAmount = Long.parseLong(donationAmount);
//            if (parsedAmount <= 0) {
//                ETAmount.setError("Donation amount must be greater than zero.");
//                ETAmount.requestFocus();
//                return;
//            }
//        } catch (NumberFormatException e) {
//            ETAmount.setError("Please enter a valid number.");
//            ETAmount.requestFocus();
//            return;
//        }
//
//        if (donationID == null) {
//            Toast.makeText(getContext(), "Donation ID is missing!", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
//
//
//
//        // Use Firestore's Timestamp for the current date and time
//        Timestamp transactionDate = Timestamp.now();
//
//        // Prepare data for submission
//        Map<String, Object> donationSubmit = new HashMap<>();
//
//        donationSubmit.put("organizerName", organizationName);
//        donationSubmit.put("projectName", projectName);
//        donationSubmit.put("donationName", name);
//        donationSubmit.put("donationID", donationID);
//        donationSubmit.put("donationAmount", parsedAmount);
//        donationSubmit.put("donateAnonymously", isAnonymous);
//        donationSubmit.put("additionalMessage", additionalMessage.isEmpty() ? "-" : additionalMessage);
//        donationSubmit.put("selectedMethod", selectedMethod);
//        donationSubmit.put("userId", userId);
//        donationSubmit.put("transactionDate", transactionDate);
//
//        // Submit to Firestore
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        db.collection("donationSubmit")
//                .add(donationSubmit)
//                .addOnSuccessListener(documentReference -> {
//
//                    addDonationAmountToDatabase(parsedAmount);
//                    Toast.makeText(getContext(), "Donation submitted successfully!", Toast.LENGTH_SHORT).show();
//                    clearFields();
//                })
//                .addOnFailureListener(e -> {
//                    Log.e("FirestoreError", "Failed to submit donation: " + e.getMessage(), e);
//                    Toast.makeText(getContext(), "Failed to submit donation: " + e.getMessage(), Toast.LENGTH_SHORT).show();
//                });
//    }

//    private void addDonationAmountToDatabase(long parsedAmount) {
//
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        DocumentReference donationRef = db.collection("donations").document(donationID);
//
//        // Fetch the current amount from Firestore
//        donationRef.get()
//                .addOnSuccessListener(documentSnapshot -> {
//                    if (documentSnapshot.exists()) {
//                        // Get the current donation amount
//                        Long currentAmount = documentSnapshot.getLong("currentDonationAmount");
//                        Long targetAmount = documentSnapshot.getLong("targetDonationAmount");
//                        if (currentAmount == null) {
//                            currentAmount = 0L; // Default to 0 if null
//                        }
//
//                        long updatedAmount = currentAmount + parsedAmount;
//
//                        // Fix progress calculation using double precision
//                        double progressDouble = ((double) updatedAmount / targetAmount) * 100;
//                        long progress = Math.round(progressDouble);
//
//
//                        donationRef.update(
//                                        "currentDonationAmount", updatedAmount,
//                                        "progress", progress
//                                )
//                                .addOnSuccessListener(aVoid -> {
//                                    Log.d("Firestore", "Donation amount successfully updated.");
//                                })
//                                .addOnFailureListener(e -> {
//                                    Log.e("Firestore", "Error updating donation amount", e);
//                                });
//                    } else {
//                        Log.e("Firestore", "Donation document does not exist.");
//                    }
//                })
//                .addOnFailureListener(e -> {
//                    Log.e("Firestore", "Error fetching donation document", e);
//                });
//
//    }

    private boolean validateDonationForm() {


        String donationAmount = ETAmount.getText().toString().trim();
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
        ETAmount.setText("");
        additionalText.setText("");
    }


}
