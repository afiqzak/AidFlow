package com.example.aidflow;

import java.util.HashMap;
import java.util.Map;

// Cloudinary configuration class
public class CloudinaryConfig {
    // Cloudinary cloud name
    public static final String CLOUD_NAME = "dwyryjcgp";
    // Cloudinary API key
    public static final String API_KEY = "889922453814139";
    // Cloudinary API secret
    public static final String API_SECRET = "zcnECw7TY13pcd0Q2bLYtMrBrec";

    // Method to get Cloudinary configuration
    public static Map<String, String> getCloudinaryConfig() {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", CLOUD_NAME);
        config.put("api_key", API_KEY);
        config.put("api_secret", API_SECRET);
        return config;
    }
}
