package com.example.aidflow;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavMenu = findViewById(R.id.bottomNavigationView);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.FragmentViewMain);
        NavController navControl = navHostFragment.getNavController();

        NavigationUI.setupWithNavController(bottomNavMenu, navControl);

        navControl.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if (destination.getId() == R.id.DestRating || destination.getId() == R.id.DestReport) {
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
