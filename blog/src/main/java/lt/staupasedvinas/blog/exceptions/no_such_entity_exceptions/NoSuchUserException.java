package lt.staupasedvinas.blog.exceptions.no_such_entity_exceptions;

public class NoSuchUserException extends NoSuchEntityException {
    public NoSuchUserException(Long id) {
        super("User", id);
    }
}
