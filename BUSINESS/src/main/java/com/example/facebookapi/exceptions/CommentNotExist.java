package com.example.facebookapi.exceptions;

import java.util.UUID;

public class CommentNotExist extends RuntimeException{
    public CommentNotExist(UUID commentID) {
        super("Komentarz o podanym ID: " + commentID + " nie istnieje");
    }
}
