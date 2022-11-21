package com.springboot.tracker.security.service.impl;

import com.springboot.tracker.Scheduler;
import com.springboot.tracker.entity.Department;
import com.springboot.tracker.entity.Event;
import com.springboot.tracker.exception.ResourceNotFoundException;
import com.springboot.tracker.payload.EventDto;
import com.springboot.tracker.repository.DepartmentRepo;
import com.springboot.tracker.repository.EventRepo;
import com.springboot.tracker.repository.UserEventRepo;
import com.springboot.tracker.security.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    private EventRepo eventRepo;
   @Autowired
    private DepartmentRepo departmentRepo;

   @Autowired
   private UserEventRepo userEventRepo;

    @Autowired
    public EventServiceImpl(EventRepo eventRepo) {
        this.eventRepo = eventRepo;
    }


    @Override
    public EventDto createEvent(EventDto eventDto) throws IOException, TimeoutException {

        Event event=mapToEntity(eventDto);
        System.out.println("saving in db.....");
        Event newEvent=eventRepo.save(event);
        Long deptId = event.getDepartment().getId();
        String deptName = departmentRepo.getDeptName(deptId);
        System.out.println("dept is"+deptName);
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("title", event.getTitle().toString());
        data.put("description", event.getDescription().toString());
        data.put("venue", event.getVenue().toString());
        data.put("event_id", event.getEvent_id().toString());
        data.put("action", "new event");

        if(Objects.equals(deptName, "EMERGENCY")){
            System.out.println("this is emergency elert");

            System.out.println("sending emergency notiication...");
            try {
            Scheduler.sendMessageToBroker("public",data.toString(), null);
                System.out.println("notification sent.....");
        } catch (IOException | TimeoutException e) {
            throw new RuntimeException(e);
        }

        }
        else{
            System.out.println("generic notification"+event.getDepartment().getName());
            Scheduler.sendGenericMessagetoBroker(event.getDepartment().getName(),data.toString(), event.getDepartment().getName().toString());

        }
        return mapToDto(newEvent);
    }


    @Override
    public List<EventDto> getAllEvents() {

        List<Event> events=eventRepo.findAll();
        return events.stream().map(event -> mapToDto(event)).collect(Collectors.toList());

    }
    @Override
    public List<EventDto> getAllEventsForUsers() {
        List<Event> events=eventRepo.getEventsForUsers();
        return events.stream().map(event -> mapToDto(event)).collect(Collectors.toList());
    }


    @Override
    public List<Long> getUserIdsForDeptAndSendNotification(long dept_id) throws IOException, TimeoutException {
        List<Event> event=eventRepo.getEventByDepartment_Id(dept_id);
        List<Long> user_ids = null;
        for (int i = 0; i < event.size(); i++) {
            List<Long> temp_user = userEventRepo.getUserForEvent(event.get(i).getEvent_id());
            for (int j = 0; j < temp_user.size(); j++) {
                HashMap<String, String> data = new HashMap<String, String>();

                user_ids.add(temp_user.get(j));
                data.put("title", event.get(i).getTitle());
                data.put("description", event.get(i).getDescription().toString());
                data.put("venue", event.get(i).getVenue().toString());
                data.put("event_id", event.get(i).getEvent_id().toString());
                data.put("action", "department deleted.");
                Scheduler.sendMessageToBroker(user_ids.get(j).toString(),data.toString(), user_ids.get(j).toString());
            }


        }
        return user_ids;
    }
    @Override
    public EventDto getEventById(long id){

        Event event=eventRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Event","name",id));
        return mapToDto(event);
    }

    @Override
    public EventDto updateEventById(EventDto eventDto, long id) throws IOException, TimeoutException {
        Event event=eventRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Event","name",id));

        event.setTitle(eventDto.getTitle());
        event.setDescription(eventDto.getDescription());
        event.setVenue(eventDto.getVenue());
        event.setEventDate(eventDto.getEventDate());
        event.setEventTime(eventDto.getEventTime());
        event.setDepartment(eventDto.getDepartment());
        HashMap<String, String> data = new HashMap<String, String>();

        data.put("title", event.getTitle().toString());
        data.put("description", event.getDescription().toString());
        data.put("venue", event.getVenue().toString());
        data.put("event_id", event.getEvent_id().toString());
        data.put("action", "update");
        Event updatedPost=eventRepo.save(event);
        Department dept = departmentRepo.getDeptById(event.getDepartment().getId());
        Scheduler.sendGenericMessagetoBroker(dept.getName(),data.toString(), dept.getName().toString());

        EventDto eventResponse=mapToDto(updatedPost);

        return mapToDto(updatedPost);
    }

    @Override
    public List<EventDto> getEventsByDate() {
        return null;
    }



    @Override
    public void deleteEventById(long id) throws IOException, TimeoutException {

        Event event=eventRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Event","name",id));
        eventRepo.delete(event);
        HashMap<String, String> data = new HashMap<String, String>();

        data.put("title", event.getTitle().toString());
        data.put("description", event.getDescription().toString());
        data.put("venue", event.getVenue().toString());
        data.put("event_id", event.getEvent_id().toString());
        data.put("action", "delete");
        Department dept = departmentRepo.getDeptById(event.getDepartment().getId());
        Scheduler.sendGenericMessagetoBroker(dept.getName(),data.toString(), dept.getName().toString());
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
