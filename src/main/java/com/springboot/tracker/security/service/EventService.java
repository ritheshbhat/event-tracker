package com.springboot.tracker.security.service;

import com.springboot.tracker.payload.EventDto;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

public interface EventService {

    EventDto createEvent(EventDto eventDto) throws IOException, TimeoutException;

    List<EventDto> getAllEvents();

    List<Long> getUserIdsForDeptAndSendNotification(long dept_id) throws IOException, TimeoutException;

    EventDto getEventById(long id);

    EventDto updateEventById(EventDto eventDto, long id) throws IOException, TimeoutException;

    List<EventDto> getEventsByDate();

    void deleteEventById(long id) throws IOException, TimeoutException;

    List<EventDto> getAllEventsForUsers();


}

