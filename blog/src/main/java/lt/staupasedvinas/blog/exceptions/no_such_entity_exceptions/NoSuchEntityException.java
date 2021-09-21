package lt.staupasedvinas.blog.exceptions.no_such_entity_exceptions;

public abstract class NoSuchEntityException extends Exception {
    public NoSuchEntityException(String entity, Long id) {
        super("No " + entity + " with id: " + id);
    }
}
