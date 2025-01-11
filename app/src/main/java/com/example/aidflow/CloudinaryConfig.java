package com.example.aidflow;

import java.util.HashMap;
import java.util.Map;

public class CloudinaryConfig {
    public static final String CLOUD_NAME = "dwyryjcgp";
    public static final String API_KEY = "889922453814139";
    public static final String API_SECRET = "zcnECw7TY13pcd0Q2bLYtMrBrec";

    public static Map<String, String> getCloudinaryConfig() {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", CLOUD_NAME);
        config.put("api_key", API_KEY);
        config.put("api_secret", API_SECRET);
        return config;
    }
}
