package lt.staupasedvinas.blog.service;

import lombok.RequiredArgsConstructor;
import lt.staupasedvinas.blog.exceptions.CommentErrorException;
import lt.staupasedvinas.blog.model.Comment;
import lt.staupasedvinas.blog.model.Post;
import lt.staupasedvinas.blog.model.User;
import lt.staupasedvinas.blog.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public void saveComment(Comment comment) {
        comment.setPostDate(new Date());
        commentRepository.save(comment);
    }

    public void createComment(Comment comment, BindingResult result, User user, Post post) throws CommentErrorException {
        if (result.hasErrors()) {
            throw new CommentErrorException();
        }

        if (user.getId() != -1 && comment != null) {
            comment.setAuthor(user);
            comment.setPost(post);
            saveComment(comment);
        }
    }

    public List<Comment> getCommentList(Post post) {
        List<Comment> commentList = post.getCommentList();
        commentList.sort(Collections.reverseOrder(Comparator.comparing(Comment::getPostDate)));
        return commentList;
    }

    public Comment getComment(Long commentId) {
        return commentRepository.getById(commentId);
    }

    public void deleteComment(Comment comment) {
        commentRepository.delete(comment);
    }
}
