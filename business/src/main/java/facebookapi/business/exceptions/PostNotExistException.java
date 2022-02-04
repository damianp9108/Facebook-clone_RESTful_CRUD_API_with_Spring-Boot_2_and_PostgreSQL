package facebookapi.business.exceptions;


public class PostNotExistException extends RuntimeException{
    public PostNotExistException(int postId) {
        super("Post o podanym Id: " + postId + " nie istnieje");
    }
}
