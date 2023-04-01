package com.example.utaeventtracker;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Login {
    @GET("/event-pass/apis/v1/user")
    Call<String> yourApiMethod(@Query("username") String username, @Query("password") String password);

    //Call<String> yourApiMethod();
}
