package com.example.facebookapi.exceptions;


public class CommentNotExist extends RuntimeException{
    public CommentNotExist(int commentID) {
        super("Komentarz o podanym ID: " + commentID + " nie istnieje");
    }
}
