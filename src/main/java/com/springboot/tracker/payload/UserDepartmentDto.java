package com.springboot.tracker.payload;

import com.springboot.tracker.entity.Department;
import com.springboot.tracker.entity.User;
import lombok.Data;

import java.sql.Time;
import java.util.List;

@Data
public class UserDepartmentDto {
    private User user;
    private List<Department> deparments;

}
