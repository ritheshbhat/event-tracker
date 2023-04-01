package com.example.utaeventtracker;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SignUpService {
    @POST("event-pass/apis/v1/user")
    Call<Void> signUp(@Body User user);
}
