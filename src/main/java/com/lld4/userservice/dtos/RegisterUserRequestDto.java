package com.lld4.userservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserRequestDto {
    private String email;
    private String password;
    private String name;
}
