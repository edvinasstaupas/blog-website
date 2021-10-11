package lt.staupasedvinas.blog.exceptions.entity.nosuch;

public abstract class NoSuchEntityException extends Exception {
    public NoSuchEntityException(String entity, Long id) {
        super("No " + entity + " with id: " + id);
    }
}
