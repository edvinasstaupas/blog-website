package lt.staupasedvinas.blog.exceptions.entity.error;

public abstract class EntityErrorException extends Exception {
    public EntityErrorException(String entity) {
        super(entity + " had errors.");
    }
}
