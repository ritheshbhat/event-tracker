package com.example.utaeventtracker;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class adminEventActivity extends AppCompatActivity {

    int hour = 0, min = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_event);

        // To set Action Title Bar color
        ActionBar titleBar;
        titleBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#0A7DD7"));
        titleBar.setBackgroundDrawable(colorDrawable);

        // Populating Category Spinner/Drop-Down menu
        List<String> categoryList =  new ArrayList<String>();
        categoryList.add("Sports");
        categoryList.add("Concerts");
        categoryList.add("Classes");
        categoryList.add("Events");
        categoryList.add("News");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, categoryList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinnerView = (Spinner) findViewById(R.id.spinner);
        spinnerView.setAdapter(adapter);
        spinnerView.setPrompt("Select Category");

        // Calendar Dialog
        Button dateButton = findViewById(R.id.dateButton);
        Calendar cal = Calendar.getInstance();
        int yyyy = cal.get(Calendar.YEAR);
        int mm = cal.get(Calendar.MONTH);
        int dd = cal.get(Calendar.DATE);
        dateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DatePickerDialog calendar = new DatePickerDialog(adminEventActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int yyyy, int mm, int dd) {
                        dateButton.setText((mm+1)+"/"+dd+"/"+yyyy);
                    }
                },yyyy, mm, dd);
                calendar.show();
            }
        });

        // Time Dialog
        Button timeButton = findViewById(R.id.timeButton);
        timeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int pickedHour, int pickedMin) {
                        hour = pickedHour;
                        min = pickedMin;
                        timeButton.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, min));
                    }
                };
                TimePickerDialog clock = new TimePickerDialog(adminEventActivity.this, onTimeSetListener, hour, min, true);
                clock.setTitle("Select Event Time");
                clock.show();
            }
        });

    }
}