package com.sample.rest.Repo;

import com.sample.rest.Models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepo extends JpaRepository<Event,Long> {
}
