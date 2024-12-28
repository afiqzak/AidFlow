//package com.example.ai;
//
//import android.os.Bundle;
//import android.view.View;
//
//import androidx.activity.EdgeToEdge;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//import androidx.navigation.NavController;
//import androidx.navigation.fragment.NavHostFragment;
//import androidx.navigation.ui.NavigationUI;
//
//import com.google.android.material.bottomnavigation.BottomNavigationView;
//
//public class MainActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_main);
//
//        BottomNavigationView bottomNavMenu = findViewById(R.id.bottomNavigationView);
//
//        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.FragmentViewMain);
//        NavController navControl = navHostFragment.getNavController();
//
//        NavigationUI.setupWithNavController(bottomNavMenu, navControl);
//
//        navControl.addOnDestinationChangedListener((controller, destination, arguments) -> {
//            if (destination.getId() == R.id.destNewsFull || destination.getId() == R.id.destCam || destination.getId() == R.id.destStoryFull || destination.getId() == R.id.destProjectFull) {
//                bottomNavMenu.setVisibility(View.GONE);
//            } else {
//                bottomNavMenu.setVisibility(View.VISIBLE);
//            }
//        });
//    }
//}