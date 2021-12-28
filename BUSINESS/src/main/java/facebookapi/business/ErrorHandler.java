package facebookapi.business;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import facebookapi.business.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;


@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(UserAlreadyExistException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public String recordAlreadyExistsHandler(UserAlreadyExistException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(LoginErrorException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String loginErrorExceptionHandler(LoginErrorException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(UserNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String userNotExistHandler(UserNotExistException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(UsernameNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String usernameNotExistHandler(UsernameNotExistException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(InvalidFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse invalidFormatExceptionHandler(InvalidFormatException ex) {
        ValidationErrorResponse response = new ValidationErrorResponse();

        for (JsonMappingException.Reference r : ex.getPath()) {
            response.addFieldError(
                    new ValidationFieldError(
                            r.getFieldName(),
                            "Nieprawidlowy format danych"

                    ));
        }
        return response;
    }


    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String illegalArgumentExceptionHandler(IllegalArgumentException ex) {
        return "Nieprawidlowy format danych";
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse validationError(MethodArgumentNotValidException exception) {
        ValidationErrorResponse response = new ValidationErrorResponse();

        if (exception.hasFieldErrors()) {
            for (FieldError error : exception.getBindingResult().getFieldErrors()) {
                response.addFieldError(
                        new ValidationFieldError(
                                error.getField(),
                                error.getDefaultMessage()));
            }
            return response;
        } else {

            for (ObjectError error : exception.getBindingResult().getAllErrors()) {
                response.addObjectError(
                        new ValidationObjectError(
                                error.getDefaultMessage()));
            }
            return response;
        }
    }


    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse validationError(ConstraintViolationException exception) {
        ValidationErrorResponse response = new ValidationErrorResponse();

        for (ConstraintViolation error : exception.getConstraintViolations()) {
            response.addFieldError(
                    new ValidationFieldError(
                            error.getPropertyPath().toString(),
                            error.getMessage()));
        }

        return response;
    }
/*
    @ExceptionHandler(PostException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String postExceptionHandler(PostException ex) {
        return ex.getMessage();
    }
*/
    @ExceptionHandler(PostNotExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String postNotExistHandler(PostNotExistException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(CommentNotExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String commentNotExistExceptionHandler(CommentNotExistException ex) {
        return ex.getMessage();
    }
}
