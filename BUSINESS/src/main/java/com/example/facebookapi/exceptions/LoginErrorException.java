package com.example.facebookapi.exceptions;

public class LoginErrorException extends RuntimeException {
    public LoginErrorException() {
        super("Błędna nazwa użytkownika lub hasło");
    }
}
