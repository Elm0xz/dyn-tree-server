package com.pretz.dyntreeserver.service;

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

/**
 * Service used for all user authorization and authentication tasks.
 */
@Service
@PropertySource("application.properties")
public class AuthUserService {

    private static final long TOKEN_VALID_TIME = 3600 * 1000; //1 hour in ms

    private static String seed;

    private UserRepo userRepo;
    private PasswordEncoder passwordEncoder;
    private UserMapper userMapper;

    @Autowired
    public AuthUserService(UserRepo userRepo, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    //TODO Using string as secret is considered an antipattern, check here: http://www.baeldung.com/java-json-web-tokens-jjwt (chapter 4: Building JWTs with JJWT)
    @Value("${dyntreeserver.seed}")
    public void setSeed(String seed) {
        AuthUserService.seed = seed;
    }

    /**
     * Method used for validation of user.
     * @param userDTO - UserDTO containing info about user to be validated.
     * @return - Token created for that user if successful or AuthException thrown if validation failed.
     */
    public String loginUser(UserDTO userDTO) {
        User valUser = userMapper.fromUserDTO(userDTO);
        Optional<User> foundUser = userRepo.findByName(valUser.getName());
        if (!foundUser.isPresent()
                || !passwordEncoder.matches(valUser.getPassword(), foundUser.get().getPassword())) {
            throw new AuthException(userDTO.getName());
        }
        return createToken(valUser);
    }

    /**
     * Method used for creation of user in database if not present. Else if user already exists in database,
     * UserAlreadyCreatedException is thrown.
     * @param userDTO - UserDTO containing info about user to be created.
     */
    public void createUser(UserDTO userDTO) {
        User createdUser = userMapper.fromUserDTO(userDTO);
        if (userRepo.findByName(createdUser.getName()).isPresent()) {
            throw new UserAlreadyCreatedException(userDTO.getName());
        }
        User newUser = new User(createdUser.getName(), createdUser.getEmail());
        newUser.setPassword(encryptPassword(createdUser));
        userRepo.save(newUser);
    }

    /**
     * Method used for preparing an encrypted password for user.
     * @param user - User for whom new encrypted password is to be set.
     * @return - String with encrypted password.
     */
    private String encryptPassword(User user) {
        return passwordEncoder.encode(user.getPassword());
    }

    /**
     * Method used for providing auth token for user.
     * @param user - User for whom new token is to be provided.
     * @return - String with created token.
     */
    private String createToken(User user) {
        return Jwts.builder()
                .setSubject(user.getName())
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALID_TIME))
                .signWith(SignatureAlgorithm.HS512, seed.getBytes())
                .compact();
    }
}
