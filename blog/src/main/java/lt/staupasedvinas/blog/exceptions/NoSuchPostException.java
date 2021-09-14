package lt.staupasedvinas.blog.exceptions;

public class NoSuchPostException extends Exception {
    public NoSuchPostException(Long id) {
        super("No user with id " + id);
    }
}
