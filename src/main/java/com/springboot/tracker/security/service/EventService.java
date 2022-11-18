package com.springboot.tracker.security.service;

import com.springboot.tracker.payload.EventDto;

import java.util.List;

public interface EventService {

    EventDto createEvent(EventDto eventDto );

    List<EventDto> getAllEvents();

    EventDto getEventById(long id);

    EventDto updateEventById(EventDto eventDto,long id);

    void deleteEventById(long id);

}

