package com.springboot.tracker.repository;


import com.springboot.tracker.entity.UserEvents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface UserEventRepo extends JpaRepository<UserEvents,Long> {
    
    boolean existsById(Long aLong);

    @Transactional
    @Modifying
    @Query("DELETE from UserEvents WHERE user.id=:user_id and uEvent.event_id=:event_id")
    void cancelEventRegistration(long user_id,long event_id);

    @Query(value = "SELECT e.user.id FROM UserEvents e WHERE e.uEvent.event_id<=:event_id")
    List<Long> getUsers(long event_id);


}

