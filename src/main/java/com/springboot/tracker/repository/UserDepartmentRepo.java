package com.springboot.tracker.repository;


import com.springboot.tracker.entity.Department;
import com.springboot.tracker.entity.Event;
import com.springboot.tracker.entity.UserDepartments;
import com.springboot.tracker.entity.UserEvents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserDepartmentRepo extends JpaRepository<UserDepartments,Long> {

    @Query("SELECT e.uDepartment from UserDepartments e where e.user.id =:x")
    List<Department> getUserDepartmentsByUserId(long x);


    @Query("SELECT e.uDepartment.id from UserDepartments e where e.user.id =:x")
    List<Long> getUserDepartmentsIdByUserId(long x);

    @Transactional
    @Modifying
    @Query("DELETE FROM UserDepartments where user.id =:x and uDepartment.id=:y")
    void getUserDepartmentsIdByUserId(long x, long y);


}

