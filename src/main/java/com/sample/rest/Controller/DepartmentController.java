package com.sample.rest.Controller;

import com.sample.rest.Payload.DepartmentDto;
import com.sample.rest.Service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tracker/departments")
public class DepartmentController {

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    private DepartmentService departmentService;

    //Creating an event
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

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDto> updateDepartmentById(@RequestBody DepartmentDto departmentDto,@PathVariable(name = "id") long id){

        DepartmentDto departmentResponse= departmentService.updateDepartmentById(departmentDto,id);
        return new ResponseEntity<>(departmentResponse,HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartmentById(@PathVariable(name = "id") long id){

        departmentService.deleteDepartmentById(id);
        return new ResponseEntity<>("Department deleted successfully",HttpStatus.OK);
    }
}
