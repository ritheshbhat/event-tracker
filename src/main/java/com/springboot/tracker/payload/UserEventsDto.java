package com.springboot.tracker.payload;

import com.springboot.tracker.entity.Event;
import com.springboot.tracker.entity.User;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserEventsDto {
    private long id;
    private Timestamp created_On;
    private Event uEvent;
    private User user;
    private boolean delivered ;
}

