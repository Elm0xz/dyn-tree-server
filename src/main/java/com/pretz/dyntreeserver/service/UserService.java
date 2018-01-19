package com.pretz.dyntreeserver.service;

import com.pretz.dyntreeserver.domain.User;
import com.pretz.dyntreeserver.repository.UserRepo;
import com.pretz.dyntreeserver.service.exceptions.AuthException;
import com.pretz.dyntreeserver.service.exceptions.UserAlreadyCreatedException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@PropertySource("application.properties")
public class UserService {

    private static final long TOKEN_VALID_TIME = 3600 * 1000; //1 hour in ms

    private static String seed;

    private UserRepo userRepo;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Value("${dyntreeserver.seed}")
    public void setSeed(String seed) {
        UserService.seed = seed;
    }

    public String validateUser(User user) {
        if (!userRepo.findByName(user.getName()).isPresent()
                || !passwordEncoder.matches(user.getPassword(), userRepo.findByName(user.getName()).get().getPassword())) {
            throw new AuthException(user.getName());
        }
        return createToken(user);
    }

    public void createUser(User user) {
        if (userRepo.findByName(user.getName()).isPresent()) {
            throw new UserAlreadyCreatedException(user.getName());
        }
        user.setPassword(encryptPassword(user));
        userRepo.save(user);
    }

    private String encryptPassword(User user) {
        return passwordEncoder.encode(user.getPassword());
    }

    private String createToken(User user) {
        return Jwts.builder()
                .setSubject(user.getName())
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALID_TIME))
                .signWith(SignatureAlgorithm.HS512, seed.getBytes())
                .compact();
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
}
