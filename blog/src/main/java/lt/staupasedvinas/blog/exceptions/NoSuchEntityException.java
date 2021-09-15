package lt.staupasedvinas.blog.exceptions;

public abstract class NoSuchEntityException extends Exception {
    public NoSuchEntityException(String entity, Long id) {
        super("No " + entity + " with id: " + id);
    }
}
