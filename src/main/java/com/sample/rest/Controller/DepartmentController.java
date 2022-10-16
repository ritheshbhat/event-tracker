package com.sample.rest.Controller;

import com.sample.rest.Payload.DepartmentDto;
import com.sample.rest.Service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tracker/departments")
public class DepartmentController {

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    private DepartmentService departmentService;

    //Creating an event
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<DepartmentDto> createDepartment(@Valid @RequestBody DepartmentDto departmentDto){
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

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DepartmentDto> updateDepartmentById(@Valid @RequestBody DepartmentDto departmentDto,@PathVariable(name = "id") long id){

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
