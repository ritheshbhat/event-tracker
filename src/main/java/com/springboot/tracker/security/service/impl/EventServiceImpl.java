package com.springboot.tracker.security.service.impl;

import com.springboot.tracker.entity.Event;
import com.springboot.tracker.exception.ResourceNotFoundException;
import com.springboot.tracker.payload.EventDto;
import com.springboot.tracker.repository.EventRepo;
import com.springboot.tracker.security.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    private EventRepo eventRepo;

//    private DepartmentRepo departmentRepo;



    //    public EventServiceImpl(EventRepo eventRepo, DepartmentRepo departmentRepo) {
//        this.eventRepo = eventRepo;
//        this.departmentRepo= departmentRepo;
//    }
    @Autowired
    public EventServiceImpl(EventRepo eventRepo) {
        this.eventRepo = eventRepo;
    }


    @Override
    public EventDto createEvent(EventDto eventDto) {

        Event event=mapToEntity(eventDto);
        Event newEvent=eventRepo.save(event);
        return mapToDto(newEvent);
    }


    @Override
    public List<EventDto> getAllEvents() {

        List<Event> events=eventRepo.findAll();
        return events.stream().map(event -> mapToDto(event)).collect(Collectors.toList());

    }

    @Override
    public EventDto getEventById(long id){

        Event event=eventRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Event","name",id));
        return mapToDto(event);
    }

    @Override
    public EventDto updateEventById(EventDto eventDto, long id) {
        Event event=eventRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Event","name",id));

        event.setTitle(eventDto.getTitle());
        event.setDescription(eventDto.getDescription());
        event.setVenue(eventDto.getVenue());
        event.setEventDate(eventDto.getEventDate());
        event.setEventTime(eventDto.getEventTime());
        event.setDepartment(eventDto.getDepartment());

        Event updatedPost=eventRepo.save(event);

        EventDto eventResponse=mapToDto(updatedPost);

        return mapToDto(updatedPost);
    }

    @Override
    public List<EventDto> getEventsByDate() {
        return null;
    }



    @Override
    public void deleteEventById(long id) {

        Event event=eventRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Event","name",id));
        eventRepo.delete(event);
    }


    private EventDto mapToDto(Event event){
        EventDto eventDto=new EventDto();
        eventDto.setId(event.getEvent_id());
        eventDto.setTitle(event.getTitle());
        eventDto.setDescription(event.getDescription());
        eventDto.setVenue(event.getVenue());
        eventDto.setEventDate(event.getEventDate());
        eventDto.setEventTime(event.getEventTime());
        eventDto.setDepartment(event.getDepartment());
        return eventDto;
    }

    //converting DTO to Entity
    private Event mapToEntity(EventDto eventDto){
        Event event=new Event();
        event.setTitle(eventDto.getTitle());
        event.setDescription(eventDto.getDescription());
        event.setVenue(eventDto.getVenue());
        event.setEventDate(eventDto.getEventDate());
        event.setEventTime(eventDto.getEventTime());
        event.setDepartment(eventDto.getDepartment());
        return event;
    }
}
