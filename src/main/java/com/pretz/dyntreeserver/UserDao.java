package com.pretz.dyntreeserver;

import com.pretz.dyntreeserver.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<User, Long> {

    Optional<User> findByNameAndPassword(String name, String password);
    Optional<User> findByEmail(String email);
}
