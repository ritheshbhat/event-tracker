package com.example.utaeventtracker;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface VerifyEventRegistration {
    @GET("/event-pass/apis/v1/verify")
    Call<String> verifyUser(@Query("user_id") int userId, @Query("event_id") int eventId);

    //Call<String> yourApiMethod();
}
