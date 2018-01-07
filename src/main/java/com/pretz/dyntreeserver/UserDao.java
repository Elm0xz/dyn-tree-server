package com.pretz.dyntreeserver;

import com.pretz.dyntreeserver.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User, Long> {

    public User findByEmail(String email);
}
