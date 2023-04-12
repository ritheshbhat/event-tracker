package com.example.utaeventtracker;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface RegisterEventApiService {

    @PUT("event-pass/apis/v1/register-event/")
    Call<Void> registerEvent(@Body RegisterEvent registerEvent);
}
