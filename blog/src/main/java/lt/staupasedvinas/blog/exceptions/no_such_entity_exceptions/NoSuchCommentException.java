package lt.staupasedvinas.blog.exceptions.no_such_entity_exceptions;

public class NoSuchCommentException extends NoSuchEntityException {
    public NoSuchCommentException(Long id) {
        super("comment", id);
    }
}
