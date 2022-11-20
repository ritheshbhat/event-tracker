package com.springboot.tracker.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.springboot.tracker.payload.UserEventsDto;
import com.springboot.tracker.repository.UserEventRepo;
import com.springboot.tracker.security.service.UserEventsService;
import lombok.var;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@PreAuthorize()
@RequestMapping("/tracker/")
public class UserEventsController {

    private UserEventRepo userEventRepo;
    public UserEventsController(UserEventRepo userEventRepo, UserEventsService userEventsService){
        this.userEventRepo = userEventRepo;
        this.userEventService=userEventsService;
    }
    private UserEventsService userEventService;

//    @GetMapping("registrationDetails")
//    public List<UserEventsDto> getAllRegistrationDetails(){
//        return userEventService.getAllEventsRegistrationDetails();
//    }
    @PostMapping("registerEvent")
    public String registerEvent(@RequestBody UserEventsDto userEventsDto) throws JsonProcessingException {
        ResponseEntity event= new ResponseEntity<>(userEventService.registerEvent(userEventsDto), HttpStatus.CREATED);
        return "Registered successfully";


    }

    @DeleteMapping("cancelRegistration")
    public String cancelRegistration(@RequestBody UserEventsDto userEventsDto ){
        var userEventJson=new JSONObject(userEventsDto);
//        System.out.println(userEventJson);
        var userJson= new JSONObject(userEventJson.get("user").toString());
        String userId1=userJson.get("id").toString();
//        System.out.println(userJson.get("id"));
        var eventJson= new JSONObject(userEventJson.get("UEvent").toString());
        String eventId1=eventJson.get("event_id").toString();
        long userId= Long.parseLong(userId1);
        long eventId=Long.parseLong(eventId1);
//        System.out.println(userId+"  " +eventId);
//        System.out.println(eventJson.get("event_id"));
        userEventRepo.cancelEventRegistration(userId,eventId);
        return "Registration cancelled successfully";
    }

}

