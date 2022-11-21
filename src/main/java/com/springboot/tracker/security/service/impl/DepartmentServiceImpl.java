package com.springboot.tracker.security.service.impl;

import com.springboot.tracker.entity.Department;
import com.springboot.tracker.exception.ResourceNotFoundException;
import com.springboot.tracker.payload.DepartmentDto;
import com.springboot.tracker.repository.EventRepo;
import com.springboot.tracker.repository.UserEventRepo;
import com.springboot.tracker.security.service.DepartmentService;
import com.springboot.tracker.security.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springboot.tracker.repository.DepartmentRepo;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentRepo DepartmentRepo;

    @Autowired
    private EventService eventService;
   @Autowired
    private EventRepo eventRepo;

    @Autowired
    private UserEventRepo userEventRepo;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepo DepartmentRepo) {
        this.DepartmentRepo = DepartmentRepo;
    }

    @Override
    public DepartmentDto createDepartment(DepartmentDto DepartmentDto) {

        Department department=mapToEntity(DepartmentDto);

        Department newDepartment=DepartmentRepo.save(department);

        DepartmentDto departmentResponse=mapToDto(newDepartment);

        return departmentResponse;
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {

        List<Department> departments=DepartmentRepo.findAll();
        return departments.stream().map(department -> mapToDto(department)).collect(Collectors.toList());

    }

    @Override
    public DepartmentDto getDepartmentById(long id){

        Department department=DepartmentRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Department","name",id));
        return mapToDto(department);
    }

    @Override
    public DepartmentDto updateDepartmentById(DepartmentDto DepartmentDto, long id) {
        Department department=DepartmentRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Department","name",id));

        department.setName(DepartmentDto.getName());


        Department updatedDepartment=DepartmentRepo.save(department);

        DepartmentDto departmentResponse=mapToDto(updatedDepartment);

        return mapToDto(updatedDepartment);
    }

    @Override
    public void deleteDepartmentById(long id) throws IOException, TimeoutException {

        Department department=DepartmentRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Department","name",id));
        eventService.getUserIdsForDeptAndSendNotification(department.getId());
        DepartmentRepo.delete(department);
    }
    //
//
    private DepartmentDto mapToDto(Department department){
        DepartmentDto DepartmentDto=new DepartmentDto();
        DepartmentDto.setId(department.getId());
        DepartmentDto.setName(department.getName());
        return DepartmentDto;
    }

    //converting DTO to Entity
    private Department mapToEntity(DepartmentDto DepartmentDto){
        Department department=new Department();
        department.setName(DepartmentDto.getName());
        return department;
    }

}

