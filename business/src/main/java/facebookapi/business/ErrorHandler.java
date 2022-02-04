package facebookapi.business;

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

    @ExceptionHandler(UsernameAlreadyExistException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public String usernameErrorHandler(UsernameAlreadyExistException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(EmailAlreadyExistException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public String emailErrorHandler(EmailAlreadyExistException ex) {
        return ex.getMessage();
    }


    @ExceptionHandler(UserNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String userErrorHandler(UserNotExistException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(UsernameNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String usernameNotExistErrorHandler(UsernameNotExistException ex) {
        return ex.getMessage();
    }


    @ExceptionHandler(RoleNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String roleNotFoundExceptionHandler(RoleNotFoundException ex){
        return ex.getMessage();
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse validationErrorHandler(MethodArgumentNotValidException exception) {
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
    public ValidationErrorResponse validationErrorHandler(ConstraintViolationException exception) {
        ValidationErrorResponse response = new ValidationErrorResponse();

        for (ConstraintViolation error : exception.getConstraintViolations()) {
            response.addFieldError(
                    new ValidationFieldError(
                            error.getPropertyPath().toString(),
                            error.getMessage()));
        }

        return response;
    }

    @ExceptionHandler(PostNotExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String postErrorHandler(PostNotExistException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(CommentNotExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String commentErrorHandler(CommentNotExistException ex) {
        return ex.getMessage();
    }
}
