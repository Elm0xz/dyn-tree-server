package com.pretz.dyntreeserver.service;

import com.pretz.dyntreeserver.domain.User;
import com.pretz.dyntreeserver.repository.UserRepo;
import com.pretz.dyntreeserver.service.exceptions.IncorrectPasswordException;
import com.pretz.dyntreeserver.service.exceptions.UserCreationFailException;
import com.pretz.dyntreeserver.service.exceptions.UserNotFoundException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.pretz.dyntreeserver.service.Seed.SEED;

@Service
public class UserService {

    private static final long TOKEN_VALID_TIME = 3600 * 1000; //1 hour in ms

    private UserRepo userRepo;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public String validateUser(User user) {
        if (userRepo.findByName(user.getName()).isPresent()) {
            if (passwordEncoder.matches(user.getPassword(), userRepo.getOne(user.getId()).getPassword())) {
                return createToken(user);
            } else {
                throw new IncorrectPasswordException(user);
            }
        } else {
            throw new UserNotFoundException(user);
        }
    }

    public boolean createUser(User user) {
        if (!userRepo.findByName(user.getName()).isPresent()) {
            user.setPassword(encryptPassword(user));
            userRepo.save(user);
        }
        if (userRepo.findByName(user.getName()).isPresent()) {
            return true;
        } else {
            throw new UserCreationFailException(user);
        }
    }

    private String encryptPassword(User user) {
        String password = passwordEncoder.encode(user.getPassword());
        return password;
    }

    private String createToken(User user) {
        String token = Jwts.builder()
                .setSubject(user.getName())
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALID_TIME))
                .signWith(SignatureAlgorithm.HS512, SEED.getBytes())
                .compact();
        return token;
    }
}
