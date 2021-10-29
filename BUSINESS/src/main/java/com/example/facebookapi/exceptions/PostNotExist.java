package com.example.facebookapi.exceptions;

import java.util.UUID;

public class PostNotExist extends RuntimeException{
    public PostNotExist(UUID postID) {
        super("Post o podanym ID: " + postID + " nie istnieje");
    }
}
