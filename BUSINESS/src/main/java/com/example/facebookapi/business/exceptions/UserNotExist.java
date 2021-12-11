package com.example.facebookapi.business.exceptions;

public class UserNotExist extends RuntimeException {
    public UserNotExist(int id) {
        super("Uzytkownik o podanym ID: " + id + " nie istnieje");
    }
}
