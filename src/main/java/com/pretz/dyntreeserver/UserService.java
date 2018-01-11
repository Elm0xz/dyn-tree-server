package com.pretz.dyntreeserver;

import com.pretz.dyntreeserver.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean validateUser(User user) {
        if (userDao.findByNameAndPassword(user.getName(), user.getPassword()).isPresent()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean createUser(User user) {
        if (!userDao.findByNameAndPassword(user.getName(), user.getPassword()).isPresent()) {
            userDao.save(user);
        }
        return validateUser(user);
    }
}
