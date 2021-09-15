package lt.staupasedvinas.blog.exceptions;

public class NoUserException extends Exception {
    public NoUserException() {
        super("No user found!");
    }
}
