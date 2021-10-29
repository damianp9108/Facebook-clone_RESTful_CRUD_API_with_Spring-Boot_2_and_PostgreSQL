package com.example.facebookapi.exceptions;

public class PostException extends RuntimeException {
    public PostException() {
        super("Nie dodano ani zdjęcia, ani opisu");
    }
}
