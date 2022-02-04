package facebookapi.business.exceptions;


public class CommentNotExistException extends RuntimeException{
    public CommentNotExistException(int commentId) {
        super("Komentarz o podanym ID: " + commentId + " nie istnieje");
    }
}
