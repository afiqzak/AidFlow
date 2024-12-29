package com.example.aidflow;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WaterReportFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WaterReportFragment extends Fragment {

    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private EditText phoneNum;
    private EditText address;
    private EditText date;
    private EditText time;
    private EditText complaint;

    private FirebaseFirestore db;
    private Cloudinary cloudinary;


    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri imageUri;
    private String uploadedImageUrl;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public WaterReportFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CatFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WaterReportFragment newInstance(String param1, String param2) {
        WaterReportFragment fragment = new WaterReportFragment();
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

        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dwyryjcgp",
                "api_key", "889922453814139",
                "api_secret", "zcnECw7TY13pcd0Q2bLYtMrBrec"
        ));


    }


    //ni utk fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_water_report, container, false);
    }

    //ni function utk bila tekan butang2
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        firstName = view.findViewById(R.id.first_name);
        lastName = view.findViewById(R.id.last_name);
        phoneNum = view.findViewById(R.id.phone_number);
        email = view.findViewById(R.id.email);
        address = view.findViewById(R.id.address);
        date = view.findViewById(R.id.date);
        time = view.findViewById(R.id.time);
        complaint = view.findViewById(R.id.complaint);

        db = FirebaseFirestore.getInstance();

        // Upload Button setup
        Button btnUpload = view.findViewById(R.id.upload_photo_button);
        btnUpload.setOnClickListener(v -> openFileChooser());

        // Submit Button setup
        Button btnSubmit = view.findViewById(R.id.submit);
        btnSubmit.setOnClickListener(v -> {
            if (imageUri != null && uploadedImageUrl == null) {
                uploadImage(); // Ensure image uploads before submitting report
            } else {
                submitWaterReport();
            }
        });

        // Date Picker setup
        View.OnClickListener OCLDate = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        requireContext(),
                        (view, selectedYear, selectedMonth, selectedDay) -> {
                            String dateSelected = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                            date.setText(dateSelected);
                        },
                        year, month, day
                );
                datePickerDialog.show();
            }
        };
        date.setOnClickListener(OCLDate);

        // Time Picker setup
        View.OnClickListener OCLTime = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        requireContext(),
                        (view, selectedHour, selectedMinute) -> {
                            String timeSelected = String.format("%02d:%02d", selectedHour, selectedMinute);
                            time.setText(timeSelected);
                        },
                        hour, minute, true
                );
                timePickerDialog.show();
            }
        };
        time.setOnClickListener(OCLTime);


        // Back Button setup
        Button btnBack = view.findViewById(R.id.btnBack);
        View.OnClickListener OCLBack = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.destWaterQuality);
            }
        };
        btnBack.setOnClickListener(OCLBack);

    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == getActivity().RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            Toast.makeText(getContext(), "Image Selected Successfully", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * private void uploadImage() {
     * if (imageUri == null) {
     * Toast.makeText(getContext(), "Please select an image first", Toast.LENGTH_SHORT).show();
     * return;
     * }
     * <p>
     * try {
     * // Convert URI to File
     * File file = createTempFileFromUri(imageUri);
     * <p>
     * // Upload to Cloudinary
     * cloudinary.uploader().upload(file, ObjectUtils.emptyMap())
     * .thenAccept(response -> {
     * uploadedImageUrl = response.get("secure_url").toString();
     * Toast.makeText(getContext(), "Upload Successful: " + uploadedImageUrl, Toast.LENGTH_SHORT).show();
     * })
     * .exceptionally(throwable -> {
     * Toast.makeText(getContext(), "Upload Failed", Toast.LENGTH_SHORT).show();
     * return null;
     * });
     * <p>
     * } catch (Exception e) {
     * e.printStackTrace();
     * Toast.makeText(getContext(), "Error preparing image", Toast.LENGTH_SHORT).show();
     * }
     * }
     */

    private void uploadImage() {
        if (imageUri != null) {
            try {
                File file = createTempFileFromUri(imageUri);

                Map uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
                uploadedImageUrl = (String) uploadResult.get("secure_url");
                Toast.makeText(getContext(), "Upload Successful: " + uploadedImageUrl, Toast.LENGTH_SHORT).show();

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getContext(), "Upload Failed", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private File createTempFileFromUri(Uri uri) throws Exception {
        InputStream inputStream = getContext().getContentResolver().openInputStream(uri);
        File tempFile = File.createTempFile("upload", ".jpg", getContext().getCacheDir());
        FileOutputStream outStream = new FileOutputStream(tempFile);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) > 0) {
            outStream.write(buffer, 0, length);
        }

        outStream.close();
        inputStream.close();
        return tempFile;
    }

    private void submitWaterReport() {
        String fName = firstName.getText().toString().trim();
        String lName = lastName.getText().toString().trim();
        String emailVal = email.getText().toString().trim();
        String phoneNumVal = phoneNum.getText().toString().trim();
        String addressVal = address.getText().toString().trim();
        String dateVal = date.getText().toString().trim();
        String timeVal = time.getText().toString().trim();
        String complaintVal = complaint.getText().toString().trim();

        if (emailVal.isEmpty() || phoneNumVal.isEmpty() || addressVal.isEmpty() || dateVal.isEmpty() || timeVal.isEmpty() || complaintVal.isEmpty()) {
            Toast.makeText(getContext(), "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String, Object> report = new HashMap<>();
        report.put("firstName", fName);
        report.put("lastName", lName);
        report.put("email", emailVal);
        report.put("phoneNum", phoneNumVal);
        report.put("address", addressVal);
        report.put("date", dateVal);
        report.put("time", timeVal);
        report.put("complaint", complaintVal);

        if (uploadedImageUrl != null) {
            report.put("imageUrl", uploadedImageUrl);
        }

        db.collection("reports")
                .add(report).addOnSuccessListener(documentReference -> {
                    Toast.makeText(getContext(), "Report submitted successfully!", Toast.LENGTH_SHORT).show();
                    clearFields();
                })
                .addOnFailureListener(e -> {
                    Log.e("FirestoreError", "Failed to submit report: " + e.getMessage(), e);
                    Toast.makeText(getContext(), "Failed to submit report: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    private void clearFields() {
        firstName.setText("");
        lastName.setText("");
        email.setText("");
        phoneNum.setText("");
        address.setText("");
        date.setText("");
        time.setText("");
        complaint.setText("");
        uploadedImageUrl = null;
        imageUri = null;
    }
}
