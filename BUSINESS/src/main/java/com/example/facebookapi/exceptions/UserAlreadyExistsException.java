package com.example.facebookapi.exceptions;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String userName) {
        super("Uzytkownik o nazwie '" + userName + "' juz istnieje");
    }
}
