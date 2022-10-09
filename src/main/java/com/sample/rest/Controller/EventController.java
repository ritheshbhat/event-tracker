package com.sample.rest.Controller;

import com.sample.rest.Service.EventService;
import com.sample.rest.Payload.EventDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tracker/")
public class EventController {

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }
    private EventService eventService;

   // Creating an event
    @PostMapping("events")
    public ResponseEntity<EventDto> createEvent(@Valid @RequestBody EventDto eventDto){
        return new ResponseEntity<>(eventService.createEvent(eventDto), HttpStatus.CREATED);
    }

    @GetMapping("events")
    public List<EventDto> getAllEvents(){
        return eventService.getAllEvents();
    }

    @GetMapping("events/{id}")
    public ResponseEntity<EventDto> getEventByID(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(eventService.getEventById(id));
    }

    @PutMapping("events/{id}")
    public ResponseEntity<EventDto> updateEvent(@Valid @RequestBody EventDto eventDto,@PathVariable(name = "id") long id){

        EventDto eventResponse=eventService.updateEventById(eventDto,id);
        return new ResponseEntity<>(eventResponse,HttpStatus.CREATED);
    }

    @DeleteMapping("events/{id}")
    public ResponseEntity<String> deleteEventById(@PathVariable(name = "id") long id){

        eventService.deleteEventById(id);
        return new ResponseEntity<>("Event deleted successfully",HttpStatus.OK);
    }


}
