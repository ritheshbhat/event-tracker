package com.example.utaeventtracker;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button signUpButton;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // To set Action Title Bar color
        ActionBar titleBar;
        titleBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#0A7DD7"));
        titleBar.setBackgroundDrawable(colorDrawable);

        // Action for signUp button to navigate to signUp screen
        signUpButton = findViewById(R.id.signupButton);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                // starting background task to update product
                Intent signUp=new Intent(MainActivity.this,signupActivity.class);
                startActivity(signUp);
            }
        });

        // Action for login button to navigate to home page
        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                EditText emailTextField = findViewById(R.id.emailField);
                EditText passwordTextField = findViewById(R.id.passwordField);

                // Warning: The below mentioned logic is for testing purpose. Must be replaced with API Call

                boolean isAdminLogin = emailTextField.getText().toString().equalsIgnoreCase("admin") &&
                        passwordTextField.getText().toString().equalsIgnoreCase("admin");
                if (isAdminLogin) {
                    Intent adminEventPage=new Intent(MainActivity.this,adminEventActivity.class);
                    startActivity(adminEventPage);
                } else {
                    Intent homePage=new Intent(MainActivity.this,homepageActivity.class);
                    startActivity(homePage);
                }
            }
        });

    }
}