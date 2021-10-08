package lt.staupasedvinas.blog.exceptions.entity_error_exceptions;

public abstract class EntityErrorException extends Exception {
    public EntityErrorException(String entity) {
        super(entity + " had errors.");
    }
}
