package com.lld4.userservice.dtos;

import com.lld4.userservice.models.Token;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDto {
    private Token token;

    public static LoginResponseDto from(Token token) {
        LoginResponseDto dto = new LoginResponseDto();
        dto.setToken(token);
        return dto;
    }
}
