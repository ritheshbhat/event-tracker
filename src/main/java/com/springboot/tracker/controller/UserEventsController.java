package com.springboot.tracker.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.springboot.tracker.entity.Event;
import com.springboot.tracker.payload.UserEventsDto;
import com.springboot.tracker.repository.EventRepo;
import com.springboot.tracker.repository.UserEventRepo;
import com.springboot.tracker.repository.UserRepository;
import com.springboot.tracker.security.service.UserEventsService;
import lombok.var;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@PreAuthorize()
@RequestMapping("/tracker/")
public class UserEventsController {
@Autowired
    private UserEventRepo userEventRepo;
@Autowired
    private UserRepository userRepository;
@Autowired
private EventRepo eventRepo;
    public UserEventsController(UserEventRepo userEventRepo, UserEventsService userEventsService){
        this.userEventRepo = userEventRepo;
        this.userEventService=userEventsService;
    }
    private UserEventsService userEventService;


    @PostMapping("registerEvent")
    public String registerEvent(@RequestBody UserEventsDto userEventsDto) throws JsonProcessingException {
        boolean userExist=userRepository.existsById(userEventsDto.getUser().getId());
        Long eventExist=eventRepo.existsByEvent_id(userEventsDto.getUEvent().getEvent_id());
        if(userExist && (eventExist!=null)){
            Long res =userEventRepo.checkRegistrationDetails(userEventsDto.getUser().getId(),userEventsDto.getUEvent().getEvent_id());
            if(res==null){
                //System.out.println(false);
                ResponseEntity event= new ResponseEntity<>(userEventService.registerEvent(userEventsDto), HttpStatus.CREATED);
                return "Registered successfully";
            }
            else {
                return "Already Registered";
            }

        } else if (!userExist && (eventExist==null)) {
            return "User and Event does not exist";

        } else{
            if(!userExist) {
                return "User does not exist";
            }
            else {
                return "Event does not exist";
            }
        }




    }

    @DeleteMapping("cancelRegistration")
    public String cancelRegistration(@RequestBody UserEventsDto userEventsDto ){

        Long res =userEventRepo.checkRegistrationDetails(userEventsDto.getUser().getId(),userEventsDto.getUEvent().getEvent_id());
        if(res==null){
            return "Registered already cancelled or does not exist";
        }
        else {
            userEventRepo.cancelEventRegistration(userEventsDto.getUser().getId(),userEventsDto.getUEvent().getEvent_id());
            return "Registration cancelled successfully";
        }

    }
    @GetMapping("checkRegistrationDetails")
    public boolean checkRegistrationDetails(@RequestBody UserEventsDto userEventsDto){
        Long res =userEventRepo.checkRegistrationDetails(userEventsDto.getUser().getId(),userEventsDto.getUEvent().getEvent_id());
        if(res==null){
            return false;
        }
        else {
            return true;
        }
    }

    @GetMapping("userEvents/{id}")
    public List<Event> getRegisteredEventsForAUser(@PathVariable(name = "id") long id) {
    List<Event> events= userEventRepo.getAllEventsForUsers(id);
    return events;
    }

}

