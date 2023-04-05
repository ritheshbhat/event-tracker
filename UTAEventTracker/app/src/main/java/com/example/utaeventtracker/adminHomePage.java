package com.example.utaeventtracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class adminHomePage extends AppCompatActivity {

    private EditText eventId;
    private Button scanQr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_add_event);

        // Find views by ID
         eventId = findViewById(R.id.event_id); //uncomment this line if needed
        scanQr = findViewById(R.id.scan_qr);

        // Set click listener for scanQr button
        scanQr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent adminEventPage = new Intent(adminHomePage.this, adminScannerActivity.class);
                startActivity(adminEventPage);

            }
        });
    }
}
