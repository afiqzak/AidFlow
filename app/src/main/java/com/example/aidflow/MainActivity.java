package com.example.aidflow;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

// MainActivity class to handle main application logic and navigation
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Uncomment the following line to add data from JSON to Firestore
        // FireStoreHelper.addDataFromJsonToFirestore(this, "report", "water.json");

        // Initialize bottom navigation menu
        BottomNavigationView bottomNavMenu = findViewById(R.id.bottomNavigationView);

        // Setup NavHostFragment and NavController
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.FragmentViewMain);
        NavController navControl = navHostFragment.getNavController();

        // Setup bottom navigation menu with NavController
        NavigationUI.setupWithNavController(bottomNavMenu, navControl);

        // Hide bottom navigation menu for specific destinations
        navControl.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if (destination.getId() == R.id.waterRating || destination.getId() == R.id.waterReport || destination.getId() == R.id.donationHistFilter || destination.getId() == R.id.volunteerDetails || destination.getId() == R.id.volunteerDetails
            || destination.getId() == R.id.destCam || destination.getId() == R.id.destStoryFull || destination.getId() == R.id.destProjectFull || destination.getId() == R.id.donationForm) {
                bottomNavMenu.setVisibility(View.GONE);
            } else {
                bottomNavMenu.setVisibility(View.VISIBLE);
            }
        });
    }

    // Method to setup bottom navigation menu with NavController
    private void setupBottomNavMenu(NavController navController){
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);
        NavigationUI.setupWithNavController(bottomNav, navController);
    }
}