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

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;


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
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#0A7DD7"));
        titleBar.setBackgroundDrawable(colorDrawable);

        // Action for signUp button to navigate to signUp screen
        signUpButton = findViewById(R.id.signupButton);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // starting background task to update product
                Intent signUp = new Intent(MainActivity.this, signupActivity.class);
                startActivity(signUp);
            }
        });


        // Action for login button to navigate to home page
        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText userTextField = findViewById(R.id.emailField);
                EditText passwordTextField = findViewById(R.id.passwordField);

                String username = userTextField.getText().toString();
                String password = passwordTextField.getText().toString();
                String url = "http://3.235.99.5:9095/event-pass/apis/v1/user/?email=" + username + "&password=" + password;

//                String url = "http://3.235.99.5:9095/";


                // Make a network call to check if the user credentials are valid
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(url)
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .build();

                Login apiInterface = retrofit.create(Login.class);

                Call<String> call = apiInterface.yourApiMethod(username,password);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            String responseBody = response.body();
                            System.out.println("response is"+ responseBody);
                            int userId = getUserId(responseBody);
                            System.out.println(userId);
                            if (responseBody.equals("admin")) {
                                Intent adminEventPage = new Intent(MainActivity.this, adminEventActivity.class);
                                startActivity(adminEventPage);
                            }

                            else {
                                Intent homePage = new Intent(MainActivity.this, userEventList.class);
                                homePage.putExtra("userId",userId);
                                startActivity(homePage);

                            }
                        } else {
                            System.out.println("error is"+response.toString());
                            // Handle error
                        }
                    }


                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        System.out.println("asdasdads"+t);
                        // Handle error
                    }
                });
            }
        });
    }

    private int getUserId(String responseBody) {
        try {
            JSONObject jsonObject = new JSONObject(responseBody);
            int id = jsonObject.getInt("id");
            return id;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}