package com.springboot.tracker.security.service;

import com.springboot.tracker.entity.User;
import com.springboot.tracker.payload.UserEventsDto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserEventsService {

    UserEventsDto registerEvent(UserEventsDto userEventsDto);



//    UserEventsDto cancelEventRegistration(UserEventsDto userEventsDto);
}