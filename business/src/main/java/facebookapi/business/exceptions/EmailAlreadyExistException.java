package facebookapi.business.exceptions;

public class EmailAlreadyExistException extends RuntimeException{

    public EmailAlreadyExistException(String email) {
        super("Error: Email " + email + " jest juz uzywany!");
    }
}
