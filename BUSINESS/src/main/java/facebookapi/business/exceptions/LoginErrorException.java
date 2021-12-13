package facebookapi.business.exceptions;

public class LoginErrorException extends RuntimeException {
    public LoginErrorException() {
        super("bledna nazwa uzytkownika lub haslo");
    }
}
