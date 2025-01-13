package com.example.aidflow;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.google.android.gms.common.util.IOUtils;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.WriteBatch;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class NewsCamFragment extends Fragment {
    private Cloudinary cloudinary;
    private Button postButton;
    private ImageView capturedImageView;
    private EditText userCaption;
    private String uploadedImageUrl;
    private Uri imageUri;

    public NewsCamFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dwyryjcgp",
                "api_key", "889922453814139",
                "api_secret", "zcnECw7TY13pcd0Q2bLYtMrBrec"
        ));
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news_cam, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btnBack = view.findViewById(R.id.btnBack);
        View.OnClickListener OCLBack = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.destHome);
            }
        };
        btnBack.setOnClickListener(OCLBack);

        postButton = view.findViewById(R.id.post_button);
        userCaption = view.findViewById(R.id.userCaption);
        capturedImageView = view.findViewById(R.id.click_image);

        // Retrieve the image from the Bundle
        if (getArguments() != null) {
            Bitmap photo = getArguments().getParcelable("capturedImage");
            if (photo != null) {
                capturedImageView.setImageBitmap(photo);

                // Save image to temp file and get Uri
                imageUri = getImageUriFromBitmap(photo);
                if (imageUri != null) {
                    new UploadImageTask().execute(imageUri);
                }
            }
        }

        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageUri != null && uploadedImageUrl == null) {
                    new UploadImageTask().execute(imageUri); // Ensure image uploads before submitting report
                } else {
                    submitStory();
                }
            }
        });
    }

    private Uri getImageUriFromBitmap(Bitmap bitmap) {
        File tempFile = new File(getContext().getCacheDir(), "image.jpg");
        try (FileOutputStream out = new FileOutputStream(tempFile)) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            return Uri.fromFile(tempFile);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
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
                NewsCamFragment.this.uploadedImageUrl = uploadedImageUrl;
                Toast.makeText(getContext(), "Upload Successful: " + uploadedImageUrl, Toast.LENGTH_SHORT).show();
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

    private void submitStory() {
        if (uploadedImageUrl == null) {
            Toast.makeText(getContext(), "Image is still uploading. Please wait.", Toast.LENGTH_SHORT).show();
            return;
        }

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Map story data
        Map<String, Object> storyData = new HashMap<>();
        storyData.put("userID", FirebaseAuth.getInstance().getCurrentUser().getUid());
        storyData.put("imageUrl", uploadedImageUrl);
        storyData.put("description", userCaption.getText().toString());
        storyData.put("dateTime", Timestamp.now());

        // Start a Firestore batch
        WriteBatch batch = db.batch();

        // Reference to the new document in "story"
        DocumentReference storyDocRef = db.collection("story").document();
        batch.set(storyDocRef, storyData);

        // Commit the batch
        batch.commit()
                .addOnSuccessListener(aVoid -> {
                    Log.d("Firestore", "Story successfully posted");
                    Toast.makeText(getContext(), "Story successfully posted!", Toast.LENGTH_LONG).show();
                    if (getFragmentManager() != null) {
                        getFragmentManager().popBackStack(); // Go back in the fragment transaction stack
                    }
                })
                .addOnFailureListener(e -> {
                    Log.e("Firestore Error", "Failed to post story", e);
                    Toast.makeText(requireContext(), "Failed to post story: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });

        // Navigate to the NewsMain fragment
        Navigation.findNavController(requireView()).navigate(R.id.action_newsCamFragment_to_newsMainPageFragment);
    }
}