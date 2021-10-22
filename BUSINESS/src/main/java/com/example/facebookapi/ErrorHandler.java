package com.example.facebookapi;

import com.example.facebookapi.exceptions.LoginErrorException;
import com.example.facebookapi.exceptions.RecordAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(RecordAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public String recordAlreadyExistsHandler(RecordAlreadyExistsException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(LoginErrorException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String loginErrorExceptionHandler(LoginErrorException ex){
        return ex.getMessage();
    }
}
