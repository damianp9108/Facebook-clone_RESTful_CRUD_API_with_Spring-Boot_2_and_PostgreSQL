package facebookapi.business.exceptions;

public class UserAlreadyExistException extends RuntimeException {

    public UserAlreadyExistException(String userName) {
        super("Uzytkownik o nazwie '" + userName + "' juz istnieje");
    }
}
