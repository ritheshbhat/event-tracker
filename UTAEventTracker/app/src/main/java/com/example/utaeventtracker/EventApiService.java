package com.example.utaeventtracker;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.GET;

public interface EventApiService {
    @GET("event-pass/apis/v1/event")
    Call<List<Event>> getEventsData();

    static EventApiService create() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://3.238.155.98:9095/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(EventApiService.class);
    }
}
