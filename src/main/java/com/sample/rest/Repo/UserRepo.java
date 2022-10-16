package com.sample.rest.Repo;

import com.sample.rest.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    Optional<User> findByUtaIdOrEmail(String utaId,String email);
    Optional<User> findByUtaId(String utaId);

    Boolean existsByUtaId(String utaId);
    Boolean existsByEmail(String email);
}
