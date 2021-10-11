package lt.staupasedvinas.blog.exceptions.entity.nosuch;

public class NoSuchCommentException extends NoSuchEntityException {
    public NoSuchCommentException(Long id) {
        super("comment", id);
    }
}
