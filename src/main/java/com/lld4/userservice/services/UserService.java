package com.lld4.userservice.services;

import com.lld4.userservice.models.Token;
import com.lld4.userservice.models.Long;
import com.lld4.userservice.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    public Long registerUser(String email, String password, String name) {
        // validate of the use already exist of not
        Optional<Long> userOptional = userRepository.findByEmail(email);
        Long user = null;
        if (userOptional.isPresent()) {
            logger.info("User already exists"); // we can throw user already exist message to user
            user = userOptional.get();

        } else {
            logger.info("User not found");
            user = new Long();
            user.setEmail(email);
            // password needs to be encripted before saving into database
            user.setHashedPassword(bCryptPasswordEncoder.encode(password));
            user.setName(name);
            user.setIsEmailVerified(Boolean.FALSE);
            user.setIsDeleted(Boolean.FALSE);
            user = userRepository.save(user);
        }
        return user;
    }

    @Override
    public Token login(String email, String password) {
        // validate the used and generate the token and return back to user;
        Optional<Long> userOptional = userRepository.findByEmail(email);
        Long user = null;
        if (userOptional.isPresent()) {
            user = userOptional.get();
            if (bCryptPasswordEncoder.matches(password, user.getHashedPassword())) {
                    Token token = new Token();
                    token.setUser(user);
                    token.setValue("Bearer " + user.getHashedPassword());
                    token.setIssuedDate(new Date()); // token creation date

                    Date date = new Date();
                    date.setTime(date.getTime() + 30 + 24 * 60 * 60 * 1000);

                    token.setExpiryDate(date); // expiry date 30 days after creation
                    return token;
            } else {
                throw new BadCredentialsException("Invalid password");
            }

        }
        return null;
    }

    @Override
    public Void logout(String token) {
        return null;
    }
}
