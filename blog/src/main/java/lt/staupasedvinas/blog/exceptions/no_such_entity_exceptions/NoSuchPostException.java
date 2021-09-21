package lt.staupasedvinas.blog.exceptions.no_such_entity_exceptions;

public class NoSuchPostException extends NoSuchEntityException {
    public NoSuchPostException(Long id) {
        super("User", id);
    }
}
