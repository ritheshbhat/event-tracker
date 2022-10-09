package com.sample.rest.Payload;

import com.sample.rest.Models.Department;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.sql.Time;

@Data
public class EventDto {
    private long id;

    @NotEmpty
    @Size(min = 2,message = "Event Title should have at least 2 characters")
    private String title;

    private String description;

    private java.sql.Date eventDate;

    @NotEmpty
    private String venue;

    private Time eventTime;
    private Department department;


}

