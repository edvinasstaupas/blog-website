package lt.staupasedvinas.blog.exceptions.entity_error_exception;

public abstract class EntityErrorException extends Exception {
    public EntityErrorException(String entity) {
        super(entity + " had errors.");
    }
}
