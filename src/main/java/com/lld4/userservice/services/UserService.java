package com.lld4.userservice.services;

import com.lld4.userservice.exceptions.InvalidTokenException;
import com.lld4.userservice.models.Token;
import com.lld4.userservice.models.User;
import com.lld4.userservice.repositories.TokenRepository;
import com.lld4.userservice.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    private final Logger logger = LoggerFactory.getLogger(UserService.class);
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private TokenRepository tokenRepository;


    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenRepository = tokenRepository;
    }

    @Override
    public User registerUser(String email, String password, String name) {
        // validate of the use already exist of not
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
        /* check if user exist with the given email or not. If not throw an exception or redirect the use to signup
         * If user exist then compare the incoming password with the hashed password stored into the database using match algorithms
         * If password matched then login sucefull and return token otherwise throw the invalid credential exception
         * */
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("User not found with given email");
        }
        User user = userOptional.get();
        if (!bCryptPasswordEncoder.matches(password, user.getHashedPassword())) {
            throw new BadCredentialsException("Invalid password");
        }
        Token token = Token.create(user);
        // once we got the token then save that token into the token table and then return
        return tokenRepository.save(token);
    }

    @Override
    public Void logout(String tokenValue) {
        /*
            check if the given token is valid and not expired and is deleted is false
         */
        Optional<Token> optionalToken = tokenRepository.findByValueAndIsDeleted(tokenValue, Boolean.FALSE);

        if (optionalToken.isEmpty()) {
            throw new InvalidTokenException("Not a valid token");
        }

        Token token = optionalToken.get();
        token.setIsDeleted(Boolean.TRUE);
        tokenRepository.save(token);
        return null;
    }

    @Override
    public User validateToken(String token) {
        Optional<Token> optionalToken = tokenRepository.findByValueAndIsDeleted(token, Boolean.FALSE);
        if (optionalToken.isEmpty()) {
            logger.info("Token not found");
            throw new InvalidTokenException("Not a valid token");
        }
        return optionalToken.get().getUser();
    }
}
