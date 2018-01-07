package com.pretz.dyntreeserver;

import com.pretz.dyntreeserver.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    private UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean checkIfUserExists(User user) {
        if (userDao.exists(user.getId())) {
            return true;
        } else {
            return false;
        }
    }

    public boolean createUser(User user) {
        userDao.save(user);
        return checkIfUserExists(user);
    }
}
