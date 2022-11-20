package com.springboot.tracker.repository;

import com.springboot.tracker.entity.Event;
import com.springboot.tracker.payload.EventDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface EventRepo extends JpaRepository<Event,Long> {


    @Query(value = "SELECT e.event_id FROM Event e WHERE e.eventDate<=:future_time")
    List<Long> getId(Date future_time);
    @Query(value = "SELECT e.title FROM Event e WHERE e.eventDate<=:future_time")
    List<String> getEventName(Date future_time);
    @Query(value = "SELECT e.venue FROM Event e WHERE e.eventDate<=:future_time")
    List<String> getVenue(Date future_time);
    @Query(value = "SELECT e.description FROM Event e WHERE e.eventDate<=:future_time")
    List<String> getDescription(Date future_time);

}
