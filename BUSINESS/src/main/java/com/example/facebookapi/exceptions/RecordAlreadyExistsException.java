package com.example.facebookapi.exceptions;

public class RecordAlreadyExistsException extends RuntimeException {

    public RecordAlreadyExistsException(String userName) {
        super("Użytkownik o nazwie '" + userName + "' już istnieje");
    }
}
