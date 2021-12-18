package facebookapi.business.exceptions;

public class UsernameNotExistException extends RuntimeException{
    public UsernameNotExistException(String userName){
        super("Uzytkownik o podanej nazwie: '" + userName + "' nie istnieje");
    }
}
