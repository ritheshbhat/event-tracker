package com.springboot.tracker.security.service;

import com.springboot.tracker.payload.UserEventsDto;

import java.util.List;

public interface UserEventsService {

    UserEventsDto registerEvent(UserEventsDto userEventsDto);
    List<UserEventsDto> getAllEventsRegistrationDetails();
}