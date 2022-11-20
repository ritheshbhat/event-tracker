package com.springboot.tracker.security.service.impl;

import com.springboot.tracker.entity.UserEvents;
import com.springboot.tracker.payload.UserEventsDto;
import com.springboot.tracker.repository.UserEventRepo;
import com.springboot.tracker.security.service.UserEventsService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserEventsServiceImpl implements UserEventsService {
    private UserEventRepo userEventRepo;

    public UserEventsServiceImpl(UserEventRepo userEventRepo){
        this.userEventRepo=userEventRepo;
    }

    @Override
    public UserEventsDto registerEvent(UserEventsDto userEventsDto) {
        UserEvents userEvents=mapToEntity(userEventsDto);
        UserEvents registerEvent=userEventRepo.save(userEvents);
        return mapToDto(registerEvent);
    }


    private UserEventsDto mapToDto(UserEvents userEvents){
        UserEventsDto userEventsDto=new UserEventsDto();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp);
        userEventsDto.setId(userEvents.getId());
        userEventsDto.setCreated_On(timestamp);
        userEventsDto.setUser(userEvents.getUser());
//        userEventsDto.setUEvent(userEvents.getUEvent());
        userEventsDto.setUEvent(userEvents.getUEvent());

        return userEventsDto;
    }
    private UserEvents mapToEntity(UserEventsDto userEventsDto){
        UserEvents userEvents=new UserEvents();
//        userEvents.setId(userEventsDto.getId());
        userEvents.setCreated_On(userEventsDto.getCreated_On());
        userEvents.setUser(userEventsDto.getUser());
        userEvents.setUEvent(userEventsDto.getUEvent());
        return userEvents;
    }
}

