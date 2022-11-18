package com.springboot.tracker.controller;

import com.springboot.tracker.payload.UserEventsDto;
import com.springboot.tracker.security.service.UserEventsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tracker/")
public class UserEventsController {


    public UserEventsController(UserEventsService userEventsService){
        this.userEventService=userEventsService;
    }
    private UserEventsService userEventService;

    @GetMapping("registrationDetails")
    public List<UserEventsDto> getAllRegistrationDetails(){
        return userEventService.getAllEventsRegistrationDetails();
    }
    @PostMapping("registerEvent")
    public String registerEvent(@RequestBody UserEventsDto userEventsDto){
        ResponseEntity event= new ResponseEntity<>(userEventService.registerEvent(userEventsDto), HttpStatus.CREATED);
        return "Registered successfully";
    }

}

