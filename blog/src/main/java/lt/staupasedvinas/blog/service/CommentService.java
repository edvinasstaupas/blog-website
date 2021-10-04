package lt.staupasedvinas.blog.service;

import lombok.RequiredArgsConstructor;
import lt.staupasedvinas.blog.exceptions.entity_error_exceptions.CommentErrorException;
import lt.staupasedvinas.blog.exceptions.no_such_entity_exceptions.NoSuchCommentException;
import lt.staupasedvinas.blog.model.Comment;
import lt.staupasedvinas.blog.model.Post;
import lt.staupasedvinas.blog.model.User;
import lt.staupasedvinas.blog.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CommentService implements IModelService<Comment> {

    private final CommentRepository commentRepository;

    public void create(Comment comment, BindingResult result, User user, Post post) throws CommentErrorException {
        if (result.hasErrors()) {
            throw new CommentErrorException();
        }

        if (user.getId() != -1 && comment != null) {
            comment.setAuthor(user);
            comment.setPost(post);
            save(comment);
        }
    }

    public List<Comment> findAll(Post post) {
        List<Comment> commentList = post.getCommentList();
        commentList.sort(Collections.reverseOrder(Comparator.comparing(Comment::getPostDate)));
        return commentList;
    }

    public void deletePostComments(Post post) {
        post.getCommentList().forEach(this::delete);
    }

    @Override
    public void save(Comment comment) {
        comment.setPostDate(new Date());
        commentRepository.save(comment);
    }

    @Override
    public void delete(Comment comment) {
        commentRepository.delete(comment);
    }

    @Override
    public Comment findById(Long id) throws NoSuchCommentException {
        Optional<Comment> optionalComment = commentRepository.findById(id);;
        if (optionalComment.isPresent())
            return optionalComment.get();
        throw new NoSuchCommentException(id);
    }
}
