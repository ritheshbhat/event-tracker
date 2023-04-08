package com.example.utaeventtracker;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class AdminScannerActivity extends AppCompatActivity {
    private static final String TAG = "ScanActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.admin_add_event);
        setContentView(R.layout.admin_scanner);


        // Initiate the scanning process
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
        integrator.setPrompt("Scan a QR code");
        integrator.setCameraId(0);
        integrator.setBeepEnabled(false);
        integrator.setBarcodeImageEnabled(true);
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        String eventId=getIntent().getStringExtra("eventId");
        super.onActivityResult(requestCode, resultCode, data);

        // Handle the scanning result
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Log.d(TAG, "Scan cancelled");
            } else {

                String message = result.getContents();
                Log.d(TAG, "Scanned message: " + message);
                Integer user_id=Integer.parseInt(message);
                int event_id=Integer.parseInt(eventId);
                String url = "http://3.238.155.98:9095/event-pass/apis/v1/verify/?user_id=" + user_id + "&event_id=" + event_id;

//                String url = "http://3.238.155.98:9095/";


                // Make a network call to check if the user credentials are valid
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(url)
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .build();

                VerifyEventRegistration apiInterface = retrofit.create(VerifyEventRegistration.class);

                Call<String> call = apiInterface.verifyUser(user_id,event_id);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            String responseBody = response.body();
                            System.out.println("response is"+ responseBody);

                AlertDialog.Builder alertSuccess = new AlertDialog.Builder(AdminScannerActivity.this);
                LayoutInflater factory = LayoutInflater.from(AdminScannerActivity.this);
                final View successView = factory.inflate(R.layout.register_success, null);
                alertSuccess.setView(successView);
                TextView successMessage = successView.findViewById(R.id.failureMessage);
                successMessage.setTextSize(20);
                successMessage.setText("User is registered!");
                final AlertDialog deptCreateSuccessDialog = alertSuccess.show();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        deptCreateSuccessDialog.cancel();
                        Intent adminEventPage=new Intent(AdminScannerActivity.this, adminHomePage.class);
                        startActivity(adminEventPage);
                    }
                }, 1500);



                        } else {
                            System.out.println("error is"+response.toString());

                            String responseBody = response.body();
                            System.out.println("response is"+ responseBody);

                            AlertDialog.Builder alertFailure = new AlertDialog.Builder(AdminScannerActivity.this);
                            LayoutInflater factory = LayoutInflater.from(AdminScannerActivity.this);
                            final View successView = factory.inflate(R.layout.register_failure, null);
                            alertFailure.setView(successView);
                            TextView successMessage = successView.findViewById(R.id.failureMessage);
                            successMessage.setTextSize(20);
                            successMessage.setText("User is not registered!");
                            final AlertDialog deptCreateSuccessDialog = alertFailure.show();
                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    deptCreateSuccessDialog.cancel();
                                    Intent adminEventPage=new Intent(AdminScannerActivity.this, adminHomePage.class);
                                    startActivity(adminEventPage);
                                }
                            }, 1500);
                            // Handle error
                        }
                    }


                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        System.out.println("asdasdads"+t);
                        // Handle error
                    }
                });












//                System.out.println(result.getContents());

//                AlertDialog.Builder alertSuccess = new AlertDialog.Builder(ScanActivity.this);
//                LayoutInflater factory = LayoutInflater.from(ScanActivity.this);
//                final View successView = factory.inflate(R.layout.register_success, null);
//                alertSuccess.setView(successView);
//                TextView successMessage = successView.findViewById(R.id.successMessage);
//                successMessage.setTextSize(20);
//                successMessage.setText("User is Registered!");
//                final AlertDialog deptCreateSuccessDialog = alertSuccess.show();
//                final Handler handler = new Handler();
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        deptCreateSuccessDialog.cancel();
//                        Intent adminEventPage=new Intent(ScanActivity.this, adminHomePage.class);
//                        startActivity(adminEventPage);
//                    }
//                }, 1500);
//                // Display the scanned message in a TextView or any other way you like
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
