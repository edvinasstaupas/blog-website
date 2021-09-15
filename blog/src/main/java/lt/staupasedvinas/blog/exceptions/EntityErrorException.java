package lt.staupasedvinas.blog.exceptions;

public abstract class EntityErrorException extends Exception {
    public EntityErrorException(String entity) {
        super(entity + " had errors.");
    }
}
