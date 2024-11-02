package com.lld4.userservice.controllers;

import com.lld4.userservice.dtos.LoginUserRequestDto;
import com.lld4.userservice.dtos.RegisterUserRequestDto;
import com.lld4.userservice.dtos.UserDto;
import com.lld4.userservice.models.Token;
import com.lld4.userservice.models.User;
import com.lld4.userservice.services.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setUsername(user.getName());
        return userDto;
    }

    @PostMapping("/login") // localhost:8080/users/login
    public Token login(@RequestBody LoginUserRequestDto loginUserRequestDto) {
        return userService.login(loginUserRequestDto.getEmail(), loginUserRequestDto.getPassword());

    }

    @PostMapping("/logout") // localhost:8080/users/logout
    public ResponseEntity<Void> logout(@RequestBody Token token) {
        userService.logout(token.getToken());
        return ResponseEntity.noContent().build();
    }
}
