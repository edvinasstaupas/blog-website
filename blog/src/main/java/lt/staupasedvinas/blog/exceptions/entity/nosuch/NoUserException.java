package lt.staupasedvinas.blog.exceptions.entity.nosuch;

public class NoUserException extends Exception {
    public NoUserException() {
        super("No user found!");
    }
}
