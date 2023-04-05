package com.example.utaeventtracker;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class signupActivity extends AppCompatActivity {
    private EditText emailEditText, phoneEditText, addressEditText, usernameEditText, zipEditText, passwordEditText;
    private Button signupButton;

    //private SignUpService signUpService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // To set Action Title Bar color
        ActionBar titleBar;
        titleBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#0A7DD7"));
        titleBar.setBackgroundDrawable(colorDrawable);

        emailEditText = findViewById(R.id.editTextTextEmailAddress2);
        phoneEditText = findViewById(R.id.editTextPhone);
        addressEditText = findViewById(R.id.editTextTextPostalAddress);
        usernameEditText = findViewById(R.id.editUsername);
        zipEditText = findViewById(R.id.editTextNumberDecimal2);
        passwordEditText = findViewById(R.id.editTextTextPassword2);
        signupButton = findViewById(R.id.signupButton_2);

        // Implement signup button click
        signupButton.setOnClickListener(new View.OnClickListener() {

            public void onClick (View v){
                String email = emailEditText.getText().toString();
                String phone = phoneEditText.getText().toString();
                String address = addressEditText.getText().toString();
                String username = usernameEditText.getText().toString();
                String zip = zipEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                //  });

                // Create an instance of Retrofit
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://3.238.155.98:9095/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                //Login apiInterface = retrofit.create(Login.class);
// Create an instance of the API interface
                // SignUpService signUpService1 = retrofit.create(signUpService.class);
                SignUpService signUpService = retrofit.create(SignUpService.class);
// Create the user object with the provided payload
                User user = new User(username, password, phone, email, address, zip);

// Call the signUp method and handle the response
                signUpService.signUp(user).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.isSuccessful()) {
                            System.out.println("Hi signed");
                            Toast.makeText(getApplicationContext(), "Signup Successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(signupActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "Signup Failed, Please provide a different username", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(signupActivity.this, signupActivity.class);
                            startActivity(intent);

                        }

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        // Handle failure
                    }
                });

            }
        });
    }
}