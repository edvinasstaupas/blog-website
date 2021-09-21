package lt.staupasedvinas.blog.exceptions.no_such_entity_exceptions;

public class NoUserException extends Exception {
    public NoUserException() {
        super("No user found!");
    }
}
