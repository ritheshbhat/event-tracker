package com.example.utaeventtracker;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class QRCodeActivity extends AppCompatActivity {

    private ImageView QRCodeImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_barcode);

        // Initialize the ImageView
        QRCodeImageView = findViewById(R.id.barcode);
        int userId=getIntent().getIntExtra("userId",0);
        System.out.println("ASasASassa");
        // Get the barcode image URL from the intent extras
        //String barcodeImageUrl = getIntent().getStringExtra("barcodeImageUrl");
        String userId1=String.valueOf(userId);
        // Load the barcode image into the ImageView using Glide
        String url="http://3.238.155.98:9095/event-pass/apis/v1/barcode/?user_id="+userId1;
        // Call<String> yourApiMethod(@Query("username") String username, @Query("password") String password);
        System.out.println(url);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(ScalarsConverterFactory.create())
                 .build();


        QRCodeApiService barcodeApiService1 = retrofit.create(QRCodeApiService.class);

        Call<ResponseBody> call = barcodeApiService1.getBarcode(userId1);


        call.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    System.out.println("hi image");
                    InputStream inputStream = response.body().byteStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                    ImageView imageView = findViewById(R.id.barcode);
                    // Display the bitmap in an ImageView
                    imageView.setImageBitmap(bitmap);

                    // Display the bitmap in an ImageView



                } else {
                    System.out.println(response);
                    // Handle error
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println("hi here");
                if (t instanceof IOException) {
                    System.out.println("Network failure occurred: " + t.getMessage());
                } else {
                    System.out.println("Unexpected error occurred: " + t.getMessage());
                    t.printStackTrace();
                }
            }


        });
    }
}
