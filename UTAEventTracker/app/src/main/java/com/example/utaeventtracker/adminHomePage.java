package com.example.utaeventtracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class adminHomePage extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_add_event);

        // Find views by ID
         EditText eventId = findViewById(R.id.adminEventIdInput); //uncomment this line if needed
        Button scanQr = findViewById(R.id.scan_qr);

        // Set click listener for scanQr button
        scanQr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String eventIdText = eventId.getText().toString();
                System.out.println("eventId is "+eventIdText);

                Intent adminEventPage = new Intent(adminHomePage.this, AdminScannerActivity.class);
                adminEventPage.putExtra("eventId",eventIdText);
                startActivity(adminEventPage);

            }
        });
    }
}
