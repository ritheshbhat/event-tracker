package com.springboot.tracker.repository;


import com.springboot.tracker.entity.UserEvents;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEventRepo extends JpaRepository<UserEvents,Long> {
}

