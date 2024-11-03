package com.lld4.userservice.controllers;

import com.lld4.userservice.dtos.LoginResponseDto;
import com.lld4.userservice.dtos.LoginUserRequestDto;
import com.lld4.userservice.dtos.RegisterUserRequestDto;
import com.lld4.userservice.dtos.UserDto;
import com.lld4.userservice.models.Token;
import com.lld4.userservice.models.User;
import com.lld4.userservice.services.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
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
        User user = userService.registerUser(registerUserRequestDto.getEmail(), registerUserRequestDto.getPassword(), registerUserRequestDto.getName());

        return UserDto.from(user); // best practice to put mapping logic in dto class itself
    }

    @PostMapping("/login") // localhost:8080/users/login
    public LoginResponseDto login(@RequestBody LoginUserRequestDto loginRequest) {
        Token token = userService.login(loginRequest.getEmail(), loginRequest.getPassword());
        if (token == null) {
            throw new BadCredentialsException("Invalid email");
        }
        return LoginResponseDto.from(token);
    }

    @PostMapping("/logout") // localhost:8080/users/logout
    public ResponseEntity<String> logout(@RequestBody Token token) {
        userService.logout(token.getValue());
        return new ResponseEntity<>("Logout successful", HttpStatus.OK);
    }

    /*
        If validate toekn simply return true/false that may not be much useful, instead if token valid fetch the user detials
        so that you know what all roles that use have and based on the you can authorise the resources after the validation
     */

    @PostMapping("/validateToken")
    public UserDto validateToken(@RequestBody Token token) {
        User user = userService.validateToken(token.getValue());
        return UserDto.from(user);
    }
}
