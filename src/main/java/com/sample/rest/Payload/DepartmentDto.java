package com.sample.rest.Payload;

import com.sample.rest.Models.Event;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
public class DepartmentDto {
    private long id;

    @NotEmpty(message = "Name should not be null/empty")
    private String name;

}
