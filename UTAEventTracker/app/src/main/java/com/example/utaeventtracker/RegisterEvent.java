package com.example.utaeventtracker;

import com.google.gson.annotations.SerializedName;

public class RegisterEvent {

    @SerializedName("user_id")
    private int user_id;

    @SerializedName("event_id")
    private int event_id;

    public RegisterEvent(int user_id,int event_id){
        this.event_id=event_id;
        this.user_id=user_id;
    }
}
