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
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class CommentService implements IModelService<Comment> {

    private final CommentRepository commentRepository;

    private final MessageService messageService;

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

    public String getTimeFromNow(Comment comment) {
        long diffInMillis = new Date().getTime() - comment.getPostDate().getTime();
        long diffInMinutes = diffInMillis / 60000;
        long diff;
        String timeUnit;
        if (diffInMinutes < 60) { //smaller than one hours
            diff = TimeUnit.MILLISECONDS.toMinutes(diffInMillis);
            timeUnit = "minute";
        } else if (diffInMinutes >= 60 && diffInMinutes < 1440) { //more than one hour
            diff = TimeUnit.MILLISECONDS.toHours(diffInMillis);
            timeUnit = "hour";
        } else if (diffInMinutes >= 1400 && diffInMinutes < 43829) { //more than one day
            diff = TimeUnit.MILLISECONDS.toDays(diffInMillis);
            timeUnit = "day";
        } else if (diffInMinutes >= 43829 && diffInMinutes < 525948) { //more than one month
            diff = TimeUnit.MILLISECONDS.toDays(diffInMillis) / 30;
            timeUnit = "month";
        } else { //more than one year
            diff = TimeUnit.MILLISECONDS.toDays(diffInMillis) / 365;
            timeUnit = "year";
        }
        return messageService.commentTimeFormatter(diff, timeUnit);
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
        Optional<Comment> optionalComment = commentRepository.findById(id);
        ;
        if (optionalComment.isPresent())
            return optionalComment.get();
        throw new NoSuchCommentException(id);
    }
}
