package com.springboot.tracker;

import com.springboot.tracker.entity.Event;
import com.springboot.tracker.payload.DepartmentDto;
import com.springboot.tracker.payload.EventDto;
import com.springboot.tracker.repository.DepartmentRepo;
import com.springboot.tracker.repository.EventRepo;
import com.springboot.tracker.repository.UserEventRepo;
import com.springboot.tracker.security.service.DepartmentService;
import com.springboot.tracker.security.service.UserEventsService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Array;
import java.sql.Date;
import java.time.LocalDateTime;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Component
    public class Scheduler {
    public Scheduler(DepartmentRepo departmentRepo, EventRepo eventRepo, DepartmentService departmentService, UserEventRepo UserEventRepo ) {
        this.departmentRepo = departmentRepo;
        this.eventRepo = eventRepo;
        this.departmentService = departmentService;
        this.userEventRepo = UserEventRepo;
    }
    @Autowired
    private DepartmentRepo departmentRepo;
    @Autowired
    private EventRepo eventRepo;
    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private UserEventRepo userEventRepo;

    @Scheduled(initialDelay = 1000, fixedRate = 10000)
    public void cronJobSch() {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, +2);
        java.util.Date now = calendar.getTime();
        List<Long> event_ids = eventRepo.getId(now);
        List<String> titles = eventRepo.getEventName(now);
        List<String> venue = eventRepo.getVenue(now);
        List<String> description = eventRepo.getDescription(now);
        System.out.println(titles);
        System.out.println(event_ids);
        System.out.println(venue);
        System.out.println(description);

        for (int i = 0; i < event_ids.size(); i++) {

            List<Long> user_ids = userEventRepo.getUsers(event_ids.get(i));
            System.out.println(user_ids);

        }



    List<DepartmentDto> departments=departmentService.getAllDepartments();

        System.out.println(departments);

    }


}