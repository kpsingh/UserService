package com.lld4.userservice.services;

import com.lld4.userservice.models.Token;
import com.lld4.userservice.models.User;

public interface IUserService {
    public User registerUser(String email, String password, String name);
    public Token login(String email, String  password);
    public Void logout(String token);
    public User validateToken(String token);
}
