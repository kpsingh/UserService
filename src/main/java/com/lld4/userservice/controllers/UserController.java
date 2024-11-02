package com.lld4.userservice.controllers;

import com.lld4.userservice.dtos.LoginUserRequestDto;
import com.lld4.userservice.dtos.RegisterUserRequestDto;
import com.lld4.userservice.dtos.UserDto;
import com.lld4.userservice.models.Token;
import com.lld4.userservice.models.User;
import com.lld4.userservice.services.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/users")
public class UserController {

    private IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register") // localhost:8080/users/register
    public UserDto register(@RequestBody RegisterUserRequestDto registerUserRequestDto) {
        User user = userService.registerUser(
                registerUserRequestDto.getEmail(),
                registerUserRequestDto.getPassword(),
                registerUserRequestDto.getName());

        return UserDto.from(user); // best practice to put mapping logic in dto class itself
    }

    @PostMapping("/login") // localhost:8080/users/login
    public Token login(@RequestBody LoginUserRequestDto loginUserRequestDto) {
        Token token = userService.login(loginUserRequestDto.getEmail(), loginUserRequestDto.getPassword());
        if (token == null) {
            throw new BadCredentialsException("Invalid email");
        }
        return  token;

    }

    @PostMapping("/logout") // localhost:8080/users/logout
    public ResponseEntity<Void> logout(@RequestBody Token token) {
        userService.logout(token.getValue());
        return ResponseEntity.noContent().build();
    }
}
