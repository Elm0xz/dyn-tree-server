package com.pretz.dyntreeserver.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pretz.dyntreeserver.domain.User;
import com.pretz.dyntreeserver.repository.UserRepo;
import com.pretz.dyntreeserver.service.dto.UserDTO;
import com.pretz.dyntreeserver.service.dto.UserMapper;
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
import java.util.Optional;

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

    public String validateUser(UserDTO userDTO) {
        Optional<User> foundUser = userRepo.findByName(userDTO.getName());
        if (!foundUser.isPresent()
                || !passwordEncoder.matches(userDTO.getPassword(), foundUser.get().getPassword())) {
            throw new AuthException(userDTO.getName());
        }
        return createToken(userDTO);
    }

    public void createUser(UserDTO userDTO) {
        if (userRepo.findByName(userDTO.getName()).isPresent()) {
            throw new UserAlreadyCreatedException(userDTO.getName());
        }
        User newUser = new User(userDTO.getName(), userDTO.getEmail());
        newUser.setPassword(encryptPassword(userDTO));
        userRepo.save(newUser);
    }

    private String encryptPassword(UserDTO userDTO) {
        return passwordEncoder.encode(userDTO.getPassword());
    }

    private String createToken(UserDTO userDTO) {
        return Jwts.builder()
                .setSubject(userDTO.getName())
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALID_TIME))
                .signWith(SignatureAlgorithm.HS512, seed.getBytes())
                .compact();
    }
}
