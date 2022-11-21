package com.springboot.tracker.repository;

import com.springboot.tracker.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmentRepo extends JpaRepository<Department,Long>{

    @Query("SELECT d.name FROM Department d WHERE d.id=:id")
    String getDeptName(long id);

    @Query("SELECT d FROM Department d WHERE d.id=:id")
    Department getDeptById(long id);

    @Query("SELECT d.name from Department d where d.name=:name")
    String existsDepartmentByName(String name);

    @Query("SELECT d.id from Department d where d.id=:id")
    Long existsDepartmentByName(long id);

}
