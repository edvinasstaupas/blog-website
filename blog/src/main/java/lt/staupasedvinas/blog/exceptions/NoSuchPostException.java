package lt.staupasedvinas.blog.exceptions;

public class NoSuchPostException extends NoSuchEntityException {
    public NoSuchPostException(Long id) {
        super("User", id);
    }
}
