package com.example.utaeventtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Gravity;
import android.widget.TableRow;
import android.widget.ScrollView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class userEventList extends AppCompatActivity {

    private Button filterDateButton;
    private Button filterDeptButton;
    private TextView textView10;
    private ScrollView regTable2;
    private TextView textView11;
    private Button registerEventButton;
    private Button removeFilterButton;
    private TableLayout event_table;
    private EventApiService eventApiService;

    private  MainActivity mainActivity;

    private Button eventPassButton;
    private QRCodeApiService barcodeApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_list);

        filterDateButton = findViewById(R.id.filterDateButton);
        filterDeptButton = findViewById(R.id.filterDeptButton);
        textView10 = findViewById(R.id.textView10);
        regTable2 = findViewById(R.id.regTable2);
        textView11 = findViewById(R.id.textView11);
        registerEventButton = findViewById(R.id.registerEvent);
        removeFilterButton = findViewById(R.id.removeFilterButton);
        event_table = findViewById(R.id.event_table);
        eventPassButton=findViewById(R.id.eventPass);



//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://3.238.155.98:9095/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//         eventApiService = retrofit.create(EventApiService.class);
//        populateTable();

        eventApiService = EventApiService.create();
        populateTable();


        registerEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText eventIdField= findViewById(R.id.eventInput);

                String eventId= eventIdField.getText().toString();
                int eventIdNum=Integer.parseInt(eventId);
                int userId=getIntent().getIntExtra("userId",0);



                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://3.238.155.98:9095/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                RegisterEventApiService registerEventApiService=retrofit.create(RegisterEventApiService.class);
                RegisterEvent registerEvent= new RegisterEvent(userId,eventIdNum);
                System.out.println("UserId is "+userId+"Event Id is :"+eventIdNum);

                registerEventApiService.registerEvent(registerEvent).enqueue(new Callback<Void>() {

                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        System.out.println(response);
                        if(response.isSuccessful()) {
                            //System.out.println("Hi signed");
                            Toast.makeText(getApplicationContext(), "Registered Successfully !! ", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(userEventList.this, userEventList.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(userEventList.this, userEventList.class);
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



        eventPassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                int userId=getIntent().getIntExtra("userId",0);

                Intent intent = new Intent(userEventList.this, QRCodeActivity.class);
                intent.putExtra("userId",userId);
                startActivity(intent);

//int userIdNum=mainActivity.getU
                // Code to handle adding a new event


            }
        });

        // Set up remove filter button
//        removeFilterButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Code to handle removing the current filter
//            }
//        });
//
//        // Populate the table with data
//
    }



        private void populateTable() {


            Call<List<Event>> call = eventApiService.getEventsData();
            System.out.println(call);
            System.out.println("table");
            System.out.println();
            call.enqueue(new Callback<List<Event>>() {


                @Override
                public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                    if (response.isSuccessful()) {
                        System.out.println(response);
                        List<Event> eventList = response.body();
                        // Process the eventList and populate the table
                        for (Event event : eventList) {
                            // Create a new row and set its properties
                            TableRow row = new TableRow(userEventList.this);
                            row.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                            row.setGravity(Gravity.CENTER_VERTICAL);

                            // Create text views for each column and set their properties
                            TextView idView = new TextView(userEventList.this);
                            idView.setLayoutParams(new TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 2f));
                            idView.setGravity(Gravity.CENTER);
                            idView.setText(String.valueOf(event.getId()));

                            TextView nameView = new TextView(userEventList.this);
                            nameView.setLayoutParams(new TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 2f));
                            nameView.setGravity(Gravity.CENTER);
                            nameView.setText(event.getName());

                            TextView descriptionView = new TextView(userEventList.this);
                            descriptionView.setLayoutParams(new TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 3f));
                            descriptionView.setGravity(Gravity.CENTER);
                            descriptionView.setText(event.getDescription());

                            TextView venueView = new TextView(userEventList.this);
                            venueView.setLayoutParams(new TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 2f));
                            venueView.setGravity(Gravity.CENTER);
                            venueView.setText(event.getVenue());


                            TextView dateView = new TextView(userEventList.this);
                            dateView.setLayoutParams(new TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
                            dateView.setGravity(Gravity.CENTER);

                            dateView.setText(event.getDate());

                            // Add the text views to the row
                            row.addView(idView);
                            row.addView(nameView);
                            row.addView(descriptionView);
                            row.addView(venueView);
                            row.addView(dateView);

                            // Add the row to the table
                            event_table.addView(row);
                        }

                    } else {
                        System.out.println(response);
                        // Handle the error
                    }
                }

                @Override
                public void onFailure(Call<List<Event>> call, Throwable t) {
                    // Handle the failure

                    if (t instanceof IOException) {
                        System.out.println("Network failure occurred: " + t.getMessage());
                    } else {
                        System.out.println("Unexpected error occurred: " + t.getMessage());
                        t.printStackTrace();
                    }
                }

            });


        }
            // Get the data from the API or local storage
           // with data
    }


