package com.pretz.dyntreeserver.service;

import com.pretz.dyntreeserver.domain.User;
import com.pretz.dyntreeserver.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public boolean validateUser(User user) {
        if (userRepo.findByName(user.getName()).isPresent()) {
            return true;
        } else {
            throw new UserNotFoundException(user);
        }
    }

    public boolean createUser(User user) {
        if (!userRepo.findByName(user.getName()).isPresent()) {
            userRepo.save(user);
        }
        if (userRepo.findByName(user.getName()).isPresent()) {
            return true;
        } else {
            throw new UserCreationFailException(user);
        }
    }
}
