package com.springboot.tracker.repository;

import com.springboot.tracker.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepo extends JpaRepository<Event,Long> {
}
