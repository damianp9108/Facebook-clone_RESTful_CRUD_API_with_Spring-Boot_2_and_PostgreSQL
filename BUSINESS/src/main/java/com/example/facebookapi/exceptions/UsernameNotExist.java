package com.example.facebookapi.exceptions;

public class UsernameNotExist extends RuntimeException{
    public UsernameNotExist(String userName){
        super("Uzytkownik o podanej nazwie uzytkownika: '" + userName + "' nie istnieje");
    }
}
