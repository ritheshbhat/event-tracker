package com.springboot.tracker.security.service;


import com.springboot.tracker.payload.DepartmentDto;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

public interface DepartmentService {
    DepartmentDto createDepartment(DepartmentDto DepartmentDto);

    List<DepartmentDto> getAllDepartments();

    DepartmentDto getDepartmentById(long id);

    DepartmentDto updateDepartmentById(DepartmentDto DepartmentDto,long id);

    void deleteDepartmentById(long id) throws IOException, TimeoutException;


}

