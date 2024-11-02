package com.lld4.userservice.services;

import com.lld4.userservice.models.Token;
import com.lld4.userservice.models.Long;

public interface IUserService {
    public Long registerUser(String email, String password, String name);
    public Token login(String email, String  password);
    public Void logout(String token);
}
