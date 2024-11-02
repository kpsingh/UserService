package com.lld4.userservice.services;

import com.lld4.userservice.models.Token;
import com.lld4.userservice.models.User;
import com.lld4.userservice.repositories.UserRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    private UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(String email, String password, String name) {
        // validate of the use alrady exist of not

        return null;
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
