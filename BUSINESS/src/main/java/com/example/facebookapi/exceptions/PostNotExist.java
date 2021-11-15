package com.example.facebookapi.exceptions;


public class PostNotExist extends RuntimeException{
    public PostNotExist(int postID) {
        super("Post o podanym ID: " + postID + " nie istnieje");
    }
}
