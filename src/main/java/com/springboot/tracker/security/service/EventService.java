package com.springboot.tracker.security.service;

import com.springboot.tracker.entity.Event;
import com.springboot.tracker.payload.EventDto;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

public interface EventService {

    EventDto createEvent(EventDto eventDto);

    List<EventDto> getAllEvents();

    EventDto getEventById(long id);

    EventDto updateEventById(EventDto eventDto, long id);

    List<EventDto> getEventsByDate();

    void deleteEventById(long id);


}

