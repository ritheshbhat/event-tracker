package com.springboot.tracker.repository;

import com.springboot.tracker.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepo extends JpaRepository<Department,Long>{
}
