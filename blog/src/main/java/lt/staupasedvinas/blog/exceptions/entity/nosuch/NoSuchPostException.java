package lt.staupasedvinas.blog.exceptions.entity.nosuch;

public class NoSuchPostException extends NoSuchEntityException {
    public NoSuchPostException(Long id) {
        super("User", id);
    }
}
