package com.example.aidflow;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UserViewModel userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        userViewModel.startListening("lKB3kbJstvfhz71Hs9YBM3T1Ddu1"); // Start listening for user data changes

        BottomNavigationView bottomNavMenu = findViewById(R.id.bottomNavigationView);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.FragmentViewMain);
        NavController navControl = navHostFragment.getNavController();

        NavigationUI.setupWithNavController(bottomNavMenu, navControl);

        navControl.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if (destination.getId() == R.id.waterRating || destination.getId() == R.id.waterReport || destination.getId() == R.id.donationHistFilter || destination.getId() == R.id.volunteerCancel || destination.getId() == R.id.volunteerJoin
            || destination.getId() == R.id.destNewsFull || destination.getId() == R.id.destCam || destination.getId() == R.id.destStoryFull || destination.getId() == R.id.destProjectFull || destination.getId() == R.id.donationForm) {
                bottomNavMenu.setVisibility(View.GONE);
            } else {
                bottomNavMenu.setVisibility(View.VISIBLE);
            }
        });
    }

    private void setupBottomNavMenu(NavController navController){
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);
        NavigationUI.setupWithNavController(bottomNav, navController);
    }
}