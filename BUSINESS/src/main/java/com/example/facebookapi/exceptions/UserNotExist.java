package com.example.facebookapi.exceptions;

public class UserNotExist extends RuntimeException {
    public UserNotExist(int id) {
        super("Uzytkownik o podanym ID: " + id + " nie istnieje");
    }
}
