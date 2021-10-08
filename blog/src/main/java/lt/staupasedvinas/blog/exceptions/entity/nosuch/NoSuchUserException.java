package lt.staupasedvinas.blog.exceptions.entity.nosuch;

public class NoSuchUserException extends NoSuchEntityException {
    public NoSuchUserException(Long id) {
        super("User", id);
    }
}
