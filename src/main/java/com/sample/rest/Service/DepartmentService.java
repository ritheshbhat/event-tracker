package com.sample.rest.Service;

import com.sample.rest.Payload.DepartmentDto;

import java.util.List;

public interface DepartmentService {
    DepartmentDto createDepartment(DepartmentDto DepartmentDto);

    List<DepartmentDto> getAllDepartments();

    DepartmentDto getDepartmentById(long id);

    DepartmentDto updateDepartmentById(DepartmentDto DepartmentDto,long id);

    void deleteDepartmentById(long id);
}
