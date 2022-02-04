package facebookapi.business.exceptions;

public class UserNotExistException extends RuntimeException {
    public UserNotExistException(int userId) {
        super("Uzytkownik o podanym Id: " + userId + " nie istnieje");
    }
}
