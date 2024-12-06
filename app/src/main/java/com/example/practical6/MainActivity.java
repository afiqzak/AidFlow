package com.example.practical6;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        bottomNavMenu = findViewById(R.id.bottomNavigationView);
//
//        NavController navControl = Navigation.findNavController(this, R.id.NHFMain);
//        NavigationUI.setupWithNavController(bottomNavMenu, navControl);
//
//        navControl.addOnDestinationChangedListener((controller, destination, arguments) -> {
//            if (destination.getId() == R.id.DestRating || destination.getId() == R.id.DestReport) {
//                bottomNavMenu.setVisibility(View.GONE);
//            } else {
//                bottomNavMenu.setVisibility(View.VISIBLE);
//            }
//        });
    }
}
