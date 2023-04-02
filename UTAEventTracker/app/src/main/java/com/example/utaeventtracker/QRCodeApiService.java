package com.example.utaeventtracker;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface QRCodeApiService {

    @GET("/event-pass/apis/v1/barcode/")
    Call<ResponseBody> getBarcode(@Query("user_id") String user_id);

}
