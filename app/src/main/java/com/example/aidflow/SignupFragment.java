package com.example.aidflow;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.common.util.IOUtils;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignupFragment extends Fragment {
    // UI elements
    private EditText ETEmail, ETFirstName, ETLastName, ETPhone, ETUsername;
    private TextInputEditText TIETPassword, TIETConfirmPass;
    private Button btnSignup;
    private FirebaseAuth mAuth;
    private ImageView IVProfileChoose;
    private ImageView imageButton;
    private Uri selectedImageUri = null;

    // Fragment initialization parameters
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // Parameters for fragment instance
    private String mParam1;
    private String mParam2;

    public SignupFragment() {
        // Required empty public constructor
    }

    /**
     * Factory method to create a new instance of this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignupFragment.
     */
    public static SignupFragment newInstance(String param1, String param2) {
        SignupFragment fragment = new SignupFragment();
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
        return inflater.inflate(R.layout.fragment_signup, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize UI elements
        ETEmail = view.findViewById(R.id.ETEmail);
        ETFirstName = view.findViewById(R.id.ETFirstName);
        ETLastName = view.findViewById(R.id.ETLastName);
        ETPhone = view.findViewById(R.id.ETPhone);
        ETUsername = view.findViewById(R.id.ETUsername);
        TIETPassword = view.findViewById(R.id.TIETPassword);
        TIETConfirmPass = view.findViewById(R.id.TIETConfirmPass);
        btnSignup = view.findViewById(R.id.BtnSignup);
        mAuth = FirebaseAuth.getInstance();
        IVProfileChoose = view.findViewById(R.id.IVProfile);
        imageButton = view.findViewById(R.id.imageButton);

        // Set click listener for image button
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(SignupFragment.this)
                        .crop()         // Crop image
                        .compress(1024)  // Final image size will be less than 1 MB
                        .maxResultSize(1080, 1080)       // Final image resolution will be less than 1080 x 1080
                        .start();
            }
        });

        // Set click listener for signup button
        btnSignup.setOnClickListener(v -> {
            String email = ETEmail.getText().toString().trim(); // Trim for whitespace
            String firstName = ETFirstName.getText().toString().trim();
            String lastName = ETLastName.getText().toString().trim();
            String phone = ETPhone.getText().toString().trim();
            String username = ETUsername.getText().toString().trim();
            String password = TIETPassword.getText().toString().trim(); // Get text from EditText inside TextInputLayout
            String confirmPass = TIETConfirmPass.getText().toString().trim(); // Get text from EditText inside TextInputLayout

            // Validate input fields
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(requireContext(), "Enter valid email address", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(firstName)) {
                Toast.makeText(requireContext(), "Enter first name", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(lastName)) {
                Toast.makeText(requireContext(), "Enter last name", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(phone)) {
                Toast.makeText(requireContext(), "Enter phone number", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(username)) {
                Toast.makeText(requireContext(), "Enter username", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(confirmPass)) {
                Toast.makeText(requireContext(), "Password does not match", Toast.LENGTH_SHORT).show();
                TIETPassword.setText("");
                TIETConfirmPass.setText("");
                return;
            }

            // Check if an image is selected
            if (selectedImageUri != null) {
                // If an image is selected, upload it to Cloudinary
                uploadImageToCloudinaryAndRegister(email, firstName, lastName, phone, username, password);
            } else {
                // If no image is selected, register the user with a null profile image
                registerUser(email, firstName, lastName, phone, username, password, null);
            }

        });
    }

    /**
     * Uploads the selected image to Cloudinary and registers the user.
     *
     * @param email The user's email.
     * @param firstName The user's first name.
     * @param lastName The user's last name.
     * @param phone The user's phone number.
     * @param username The user's username.
     * @param password The user's password.
     */
    private void uploadImageToCloudinaryAndRegister(String email, String firstName, String lastName, String phone, String username, String password) {
        try {
            InputStream inputStream = requireContext().getContentResolver().openInputStream(selectedImageUri);
            byte[] inputData = IOUtils.toByteArray(inputStream);

            Cloudinary cloudinary = new Cloudinary(CloudinaryConfig.getCloudinaryConfig());

            new Thread(() -> {
                try {
                    Map uploadResult = cloudinary.uploader().upload(inputData, ObjectUtils.emptyMap());
                    String imageUrl = (String) uploadResult.get("secure_url");

                    requireActivity().runOnUiThread(() -> registerUser(email, firstName, lastName, phone, username, password, imageUrl));
                } catch (Exception e) {
                    requireActivity().runOnUiThread(() ->
                            Toast.makeText(requireContext(), "Image upload failed: " + e.getMessage(), Toast.LENGTH_LONG).show()
                    );
                }
            }).start();
        } catch (Exception e) {
            Toast.makeText(requireContext(), "Error processing image: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Get URI of the image
        selectedImageUri = data.getData();
        IVProfileChoose.setImageURI(selectedImageUri);
    }

    /**
     * Registers the user with Firebase Authentication.
     *
     * @param email The user's email.
     * @param firstName The user's first name.
     * @param lastName The user's last name.
     * @param phone The user's phone number.
     * @param username The user's username.
     * @param password The user's password.
     * @param imageUrl The URL of the user's profile image.
     */
    private void registerUser(String email, String firstName, String lastName, String phone, String username, String password, @Nullable String imageUrl) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity(), task -> {
                    if (task.isSuccessful()) {
                        // Registration successful
                        FirebaseUser firebaseUser = mAuth.getCurrentUser();
                        if (firebaseUser != null) {
                            // Send verification email
                            firebaseUser.sendEmailVerification()
                                    .addOnCompleteListener(verificationTask -> {
                                        if (verificationTask.isSuccessful()) {
                                            // Email verification sent successfully, now store user data
                                            storeUserData(firebaseUser.getUid(), email, firstName, lastName, phone, username, imageUrl);

                                            Toast.makeText(requireContext(), "Registration successful. Please check your email for verification.", Toast.LENGTH_LONG).show();

                                            // Create a new instance of the LoginFragment
                                            Fragment loginFragment = new LoginFragment();

                                            // Perform the fragment transaction
                                            if (getActivity() != null) {
                                                Fragment fragmentToReplace = new LoginFragment();
                                                getActivity().getSupportFragmentManager().beginTransaction()
                                                        .replace(R.id.FCVLogin, fragmentToReplace)  // Specify the fragment
                                                        .addToBackStack("login") // Add to back stack (optional)
                                                        .commit();
                                            }

                                        } else {
                                            // Handle verification email sending failure
                                            Toast.makeText(requireContext(), "Failed to send verification email.", Toast.LENGTH_SHORT).show();
                                            // Optionally delete the user if verification fails to prevent orphaned accounts.
                                            firebaseUser.delete();
                                        }
                                    });
                        }
                    } else {
                        // Handle registration errors
                        Exception e = task.getException();
                        String errorMessage = "Registration failed: ";
                        if (e instanceof FirebaseAuthUserCollisionException) {
                            errorMessage += "Email already exists.";
                        } else if (e instanceof FirebaseAuthWeakPasswordException) {
                            errorMessage += "The password is too weak.";
                        } else if (e != null) {
                            errorMessage += e.getMessage();
                        } else {
                            errorMessage = "Registration Failed";
                        }
                        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_LONG).show();
                    }
                });
    }

    /**
     * Stores the user data in Firestore.
     *
     * @param uid The user's UID.
     * @param email The user's email.
     * @param firstName The user's first name.
     * @param lastName The user's last name.
     * @param phone The user's phone number.
     * @param username The user's username.
     * @param imageUrl The URL of the user's profile image.
     */
    private void storeUserData(String uid, String email, String firstName, String lastName, String phone, String username, @Nullable String imageUrl) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        // Map user data
        Map<String, Object> user = new HashMap<>();
        user.put("userID", uid);
        user.put("email", email);
        user.put("firstName", firstName);
        user.put("lastName", lastName);
        user.put("phone", phone);
        user.put("username", username);
        user.put("imageUrl", imageUrl);
        user.put("totalDonate", 0);
        user.put("volunteerHours", 0);
        user.put("reportSubmitted", 0);

        // Store the mapping of the user data into users collection
        db.collection("users").document(uid)
                .set(user)
                .addOnSuccessListener(aVoid -> {
                    Log.d("Firestore", "User data stored successfully.");
                })
                .addOnFailureListener(e -> {
                    Log.e("Firestore Error", "Failed to store user data", e);
                    Toast.makeText(requireContext(), "Failed to store user data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
}
