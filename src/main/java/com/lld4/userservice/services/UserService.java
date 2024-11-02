package com.lld4.userservice.services;

import com.lld4.userservice.models.Token;
import com.lld4.userservice.models.User;
import com.lld4.userservice.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {


    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User registerUser(String email, String password, String name) {
        // validate of the use alrady exist of not
        Optional<User> userOptional = userRepository.findByEmail(email);
        User user = null;
        if (userOptional.isPresent()) {
            logger.info("User already exists"); // we can throw user already exist message to user
            user = userOptional.get();

        } else {
            logger.info("User not found");
            user = new User();
            user.setEmail(email);
            // password needs to be encripted before saving into database
            user.setPassword(bCryptPasswordEncoder.encode(password));
            user.setName(name);
            user = userRepository.save(user);
        }
        return user;
    }

    @Override
    public Token login(String email, String password) {
        return null;
    }

    @Override
    public Void logout(String token) {
        return null;
    }
}
