package com.springboot.tracker.security.service;


import com.springboot.tracker.payload.DepartmentDto;

import java.util.List;

public interface DepartmentService {
    DepartmentDto createDepartment(DepartmentDto DepartmentDto);

    List<DepartmentDto> getAllDepartments();

    DepartmentDto getDepartmentById(long id);

    DepartmentDto updateDepartmentById(DepartmentDto DepartmentDto,long id);

    void deleteDepartmentById(long id);
}

