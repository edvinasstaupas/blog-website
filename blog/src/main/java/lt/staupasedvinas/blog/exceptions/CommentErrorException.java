package lt.staupasedvinas.blog.exceptions;

public class CommentErrorException extends EntityErrorException {
    public CommentErrorException() {
        super("Comment");
    }
}
