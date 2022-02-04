package facebookapi.business.exceptions;

public class UsernameAlreadyExistException extends RuntimeException {

    public UsernameAlreadyExistException(String userName) {
        super("Uzytkownik o nazwie '" + userName + "' juz istnieje");
    }
}
