package com.sample.rest.Service;

import com.sample.rest.Payload.EventDto;

import java.util.List;

public interface EventService {

    EventDto createEvent(EventDto eventDto );

    List<EventDto> getAllEvents();

    EventDto getEventById(long id);

    EventDto updateEventById(EventDto eventDto,long id);

    void deleteEventById(long id);

}
