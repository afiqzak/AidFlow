//package com.example.donationmodulefrontend;
//
//import android.os.Bundle;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.example.aidflow.DonationFragment;
//import com.google.android.material.bottomnavigation.BottomNavigationView;
//
//
//public class MainActivity extends AppCompatActivity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_main);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
//
//        // Set default fragment
//        if (savedInstanceState == null) {
//            // Load DonationFragment as the default fragment
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .replace(R.id.fragment_container, new DonationFragment())
//                    .commit();
//        }


//         Handle navigation item clicks
//        bottomNavigationView.setOnItemSelectedListener(item -> {
//            Fragment selectedFragment = null;
//
//            switch (item.getItemId()) {
//                case R.id.home:
//                    selectedFragment = new DonationFragment();
//                    break;
//                case R.id.donations:
//                    selectedFragment = new DonationFragment();
//                    break;
//                case R.id.profile:
//                    selectedFragment = new DonationFragment();
//                    break;
//            }
//
//            if (selectedFragment != null) {
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.fragmentContainer, selectedFragment)
//                        .commit();
//            }
//
//            return true;
//        });
// }
//}