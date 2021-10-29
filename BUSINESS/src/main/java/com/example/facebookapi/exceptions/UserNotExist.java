package com.example.facebookapi.exceptions;

import java.util.UUID;

public class UserNotExist extends RuntimeException {
    public UserNotExist(UUID id) {
        super("Uzytkownik o podanym ID: " + id + " nie istnieje");
    }
}
