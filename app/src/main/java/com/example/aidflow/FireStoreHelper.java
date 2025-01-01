package com.example.aidflow;

import android.content.Context;
import android.util.Log;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public class FireStoreHelper {

    private static final String TAG = "FirestoreHelper";
    public static void addDataFromJsonToFirestore(Context context, String collectionName, String jsonFileName) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        try {
            String jsonString = loadJsonFromAssets(context, jsonFileName);
            Gson gson = new Gson();

            Type type = new TypeToken<List<Map<String, Object>>>() {
            }.getType();
            List<Map<String, Object>> dataList = gson.fromJson(jsonString, type);

            if (dataList != null) {
                for (Map<String, Object> data : dataList) {
                    db.collection(collectionName).add(data)
                            .addOnSuccessListener(documentReference -> {
                                Log.d(TAG, "Document added with ID: " + documentReference.getId());
                            })
                            .addOnFailureListener(e -> {
                                Log.w(TAG, "Error adding document", e);
                            });
                }
            } else {
                Log.w(TAG, "Data list is null after parsing JSON.");
            }

        } catch (IOException e) {
            Log.e(TAG, "Error reading JSON file", e);
        }
    }

    private static String loadJsonFromAssets(Context context, String filename) throws IOException {
        try (InputStream is = context.getAssets().open(filename)) {
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            return new String(buffer, StandardCharsets.UTF_8);
        }
    }
}
