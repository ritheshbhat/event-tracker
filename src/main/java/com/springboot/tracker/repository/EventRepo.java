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

    @Query("SELECT e from Event e where e.department.id  <> 2")
    List<Event> getEventsForUsers();

    @Query("SELECT e from Event e where e.department.id =:dept_id")
    List<Event> getEventByDepartment_Id(long dept_id);
   @Query("select e.event_id from Event e where e.event_id =:event_id")
   Long existsByEvent_id(Long event_id);

    @Query("SELECT e.title from Event e where e.title=:title")
    String existsByEventTitle(String title);

    @Query("SELECT e,e.department.name from Event e where e.department.id in (:dept_id)")
    List<Event> getAllEventsForUsersAccordingToPreference(List dept_id);

}
