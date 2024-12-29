package com.example.aidflow;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    private TextInputEditText TIETPassword;
    private EditText ETEmail;
    private Button btnSignin;
    private FirebaseAuth mAuth;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
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
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ETEmail = view.findViewById(R.id.ETEmail);
        TIETPassword = view.findViewById(R.id.TIETPassword);
        btnSignin = view.findViewById(R.id.BtnSignin);

        mAuth = FirebaseAuth.getInstance();

        btnSignin.setOnClickListener(v -> {
            String email = ETEmail.getText().toString().trim();
            String password = TIETPassword.getText().toString().trim();

            if (TextUtils.isEmpty(email)) {
                ETEmail.setError("Email is required");
                ETEmail.requestFocus();
                return;
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                ETEmail.setError("Enter a valid email");
                ETEmail.requestFocus();
                return;
            }

            if (TextUtils.isEmpty(password)) {
                TIETPassword.setError("Password is required");
                TIETPassword.requestFocus();
                return;
            }

            loginUser(email, password);
        });
    }

    private void loginUser(String email, String password) {
        // Sign in the user with Firebase Authentication
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity(), task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        // Check if the user's email is verified
                        if(user.isEmailVerified()){
                            Toast.makeText(requireContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(requireContext(), MainActivity.class));
                            requireActivity().finish();
                        }else{
                            Toast.makeText(requireContext(), "Please verify your email", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        String errorMessage = "Login failed: ";
                        try {
                            throw task.getException();
                        } catch (FirebaseAuthInvalidUserException invalidEmail) {
                            errorMessage += "Invalid email";
                        } catch (FirebaseAuthInvalidCredentialsException wrongPassword) {
                            errorMessage += "Wrong password";
                        } catch (Exception e) {
                            errorMessage += e.getMessage();
                        }
                        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}