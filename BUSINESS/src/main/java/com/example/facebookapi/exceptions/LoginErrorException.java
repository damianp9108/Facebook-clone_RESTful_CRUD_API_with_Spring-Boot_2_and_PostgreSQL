package com.example.facebookapi.exceptions;

public class LoginErrorException extends RuntimeException {
    public LoginErrorException() {
        super("bledna nazwa uzytkownika lub haslo");
    }
}
