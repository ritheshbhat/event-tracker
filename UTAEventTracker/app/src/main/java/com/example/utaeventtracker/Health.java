package com.example.utaeventtracker;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Health {
    @GET("/event-pass/apis/health/")
    Call<String> yourApiMethod();
}
