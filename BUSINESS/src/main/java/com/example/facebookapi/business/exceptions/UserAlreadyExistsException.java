package com.example.facebookapi.business.exceptions;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String userName) {
        super("Uzytkownik o nazwie '" + userName + "' juz istnieje");
    }
}
