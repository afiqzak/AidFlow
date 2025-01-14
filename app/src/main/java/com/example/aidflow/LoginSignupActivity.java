package com.example.aidflow;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

public class LoginSignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_signup);

        // Apply window insets to the title view
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.TVTitle), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Toggle between login and signup views
        TextView TVToggle = (TextView) findViewById(R.id.TVToggle);
        TVToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView TVTitle = findViewById(R.id.TVTitle);
                TextView TVNoAcc = findViewById(R.id.TVNoAcc);

                String current = TVToggle.getText().toString();

                // Toggle between login and signup fragments
                if(current.equals("Sign up")){
                    TVToggle.setText("Sign in");
                    TVTitle.setText("Sign up");
                    TVNoAcc.setText("Have an \nAccount?");

                    Fragment fragment = new SignupFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.FCVLogin, fragment).commit();
                }
                else{
                    TVToggle.setText("Sign up");
                    TVTitle.setText("Sign in");
                    TVNoAcc.setText("No Account?");

                    Fragment fragment = new LoginFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.FCVLogin, fragment).commit();
                }
            }
        });
    }
}