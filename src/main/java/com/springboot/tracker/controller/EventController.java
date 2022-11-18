package com.springboot.tracker.controller;

import com.springboot.tracker.helpers.ZXingHelper;
import com.springboot.tracker.payload.EventDto;
import com.springboot.tracker.security.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
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
    public ResponseEntity<EventDto> createEvent(@RequestBody EventDto eventDto){
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
    public ResponseEntity<EventDto> updateEvent( @RequestBody EventDto eventDto,@PathVariable(name = "id") long id){

        EventDto eventResponse=eventService.updateEventById(eventDto,id);
        return new ResponseEntity<>(eventResponse,HttpStatus.CREATED);
    }

    @DeleteMapping("events/{id}")
    public ResponseEntity<String> deleteEventById(@PathVariable(name = "id") long id){

        eventService.deleteEventById(id);
        return new ResponseEntity<>("Event deleted successfully",HttpStatus.OK);
    }
    @RequestMapping(value = "barcode/{id}", method = RequestMethod.GET)
    public void barcode(@PathVariable("id") String id, HttpServletResponse response) throws Exception {
        response.setContentType("image/png");
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(ZXingHelper.createBarCodeImage(id, 200, 100));
        outputStream.write(id.getBytes());
        outputStream.flush();
        outputStream.close();
    }

//    @PostMapping("events")
//    public ResponseEntity<EventDto> createEvent(@Valid @RequestBody EventDto eventDto){
//        return new ResponseEntity<>(eventService.createEvent(eventDto), HttpStatus.CREATED);
//    }

}
