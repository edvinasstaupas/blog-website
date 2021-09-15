package lt.staupasedvinas.blog.exceptions;

public class NoSuchUserException extends NoSuchEntityException {
    public NoSuchUserException(Long id) {
        super("User", id);
    }
}
