package com.lld4.userservice.advices;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionAdvices {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> invalidCredentials(BadCredentialsException badCredentialsException) {

        return new ResponseEntity<>(badCredentialsException.getMessage(),
                HttpStatus.BAD_REQUEST);

    }
}
