package com.springboot.tracker.controller;

import com.springboot.tracker.payload.DepartmentDto;
import com.springboot.tracker.security.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments/")
public class DepartmentController {

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    private DepartmentService departmentService;

    //Creating an event
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody DepartmentDto departmentDto){
        return new ResponseEntity<>(departmentService.createDepartment(departmentDto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<DepartmentDto> getAllDepartments(){
        return departmentService.getAllDepartments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(departmentService.getDepartmentById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDto> updateDepartmentById(@RequestBody DepartmentDto departmentDto,@PathVariable(name = "id") long id){

        DepartmentDto departmentResponse= departmentService.updateDepartmentById(departmentDto,id);
        return new ResponseEntity<>(departmentResponse,HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteDepartmentById(@PathVariable(name = "id") long id){

        departmentService.deleteDepartmentById(id);
        return new ResponseEntity<>("Department deleted successfully",HttpStatus.OK);
    }
}

