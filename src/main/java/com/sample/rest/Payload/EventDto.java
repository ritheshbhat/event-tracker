package com.sample.rest.Payload;

import com.sample.rest.Models.Department;
import lombok.Data;

import java.sql.Time;

@Data
public class EventDto {
    private long id;

    private String title;

    private String description;

    private java.sql.Date eventDate;

    private String venue;

    private Time eventTime;
    private Department department;


}

