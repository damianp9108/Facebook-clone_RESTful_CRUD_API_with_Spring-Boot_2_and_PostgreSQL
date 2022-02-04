package facebookapi.business.exceptions;

public class RoleNotFoundException extends RuntimeException{
    public RoleNotFoundException() {
        super("Error: Nie znaleziono podanej roli.");
    }
}
