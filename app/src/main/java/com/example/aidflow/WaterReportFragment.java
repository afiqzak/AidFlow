package com.example.aidflow;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

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

import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_water_report, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        firstName = view.findViewById(R.id.first_name);
        lastName = view.findViewById(R.id.last_name);
        phoneNum = view.findViewById(R.id.phone_number);
        email = view.findViewById(R.id.email);
        address = view.findViewById(R.id.address);
        date = view.findViewById(R.id.date);
        time = view.findViewById(R.id.time);
        complaint = view.findViewById(R.id.complaint);

        db = FirebaseFirestore.getInstance();

        Button btnUpload = view.findViewById(R.id.upload_photo_button);
        btnUpload.setOnClickListener(v -> openFileChooser());

        Button btnSubmit = view.findViewById(R.id.submit);
        btnSubmit.setOnClickListener(v -> {
            if (imageUri != null && uploadedImageUrl == null) {
                new UploadImageTask().execute(imageUri); // Ensure image uploads before submitting report
            } else {
                submitWaterReport(userId);
            }
        });

        View.OnClickListener OCLDate = v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    requireContext(),
                    (view1, selectedYear, selectedMonth, selectedDay) -> {
                        String dateSelected = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                        date.setText(dateSelected);
                    },
                    year, month, day
            );
            datePickerDialog.show();
        };
        date.setOnClickListener(OCLDate);

        View.OnClickListener OCLTime = v -> {
            Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(
                    requireContext(),
                    (view12, selectedHour, selectedMinute) -> {
                        String timeSelected = String.format("%02d:%02d", selectedHour, selectedMinute);
                        time.setText(timeSelected);
                    },
                    hour, minute, true
            );
            timePickerDialog.show();
        };
        time.setOnClickListener(OCLTime);

        Button btnBack = view.findViewById(R.id.btnBackNews);
        View.OnClickListener OCLBack = v -> Navigation.findNavController(view).navigate(R.id.destWaterQuality);
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

    private class UploadImageTask extends AsyncTask<Uri, Void, String> {
        @Override
        protected String doInBackground(Uri... params) {
            Uri imageUri = params[0];
            try {
                File file = createTempFileFromUri(imageUri);
                Map uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
                return (String) uploadResult.get("secure_url");
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("UploadError", "Error uploading image: " + e.getMessage(), e);
                return null;
            }
        }

        @Override
        protected void onPostExecute(String uploadedImageUrl) {
            if (uploadedImageUrl != null) {
                WaterReportFragment.this.uploadedImageUrl = uploadedImageUrl;
                Toast.makeText(getContext(), "Upload Successful: " + uploadedImageUrl, Toast.LENGTH_SHORT).show();
                submitWaterReport(FirebaseAuth.getInstance().getCurrentUser().getUid());
            } else {
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

    private void submitWaterReport(String userId) {
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

        // Parse the date string to a Date object
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Date parsedDate;
        try {
            parsedDate = dateFormat.parse(dateVal);
        } catch (ParseException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "Invalid date format", Toast.LENGTH_SHORT).show();
            return;
        }

        // Convert the Date object to a Firestore Timestamp
        Timestamp reportTimestamp = new Timestamp(parsedDate);

        Map<String, Object> report = new HashMap<>();
        report.put("status", false);
        report.put("userID", userId);
        report.put("firstName", fName);
        report.put("lastName", lName);
        report.put("email", emailVal);
        report.put("phoneNum", phoneNumVal);
        report.put("address", addressVal);
        report.put("reportDate", reportTimestamp); // Use Timestamp instead
        report.put("complaint", complaintVal);

        if (uploadedImageUrl != null) {
            report.put("imageUrl", uploadedImageUrl);
        }

        db.collection("report")
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