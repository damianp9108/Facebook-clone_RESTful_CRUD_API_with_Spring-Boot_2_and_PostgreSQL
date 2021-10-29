package com.example.facebookapi;

import com.example.facebookapi.exceptions.*;


import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;


@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public String recordAlreadyExistsHandler(UserAlreadyExistsException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(LoginErrorException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String loginErrorExceptionHandler(LoginErrorException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(UserNotExist.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String userNotExistHandler(UserNotExist ex){
        return ex.getMessage();
    }

    @ExceptionHandler(InvalidFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse invalidFormatExceptionHandler(InvalidFormatException ex) {
       ValidationErrorResponse response = new ValidationErrorResponse();

        for (JsonMappingException.Reference r : ex.getPath()) {
            response.addError(
                    new ValidationError(
                            r.getFieldName(),
                            "Nieprawidlowy format danych"

                        ));
        }
        return response;
    }


    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String illegalArgumentExceptionHandler(IllegalArgumentException ex){
        return "Nieprawidlowy format danych";
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse validationError(MethodArgumentNotValidException exception) {
        ValidationErrorResponse response = new ValidationErrorResponse();

        for(FieldError error: exception.getBindingResult().getFieldErrors()) {
            response.addError(
                    new ValidationError(
                            error.getField(),
                            error.getDefaultMessage()));
        }

        return response;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse validationError(ConstraintViolationException exception) {
        ValidationErrorResponse response = new ValidationErrorResponse();

        for(ConstraintViolation error: exception.getConstraintViolations()) {
            response.addError(
                    new ValidationError(
                            error.getPropertyPath().toString(),
                            error.getMessage()));
        }

        return response;
    }

    @ExceptionHandler(PostException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String postExceptionHandler(PostException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(PostNotExist.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String postNotExistHandler(PostNotExist ex){
        return ex.getMessage();
    }

    @ExceptionHandler(CommentNotExist.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String commentNotExistHandler(CommentNotExist ex){
        return ex.getMessage();
    }
}
