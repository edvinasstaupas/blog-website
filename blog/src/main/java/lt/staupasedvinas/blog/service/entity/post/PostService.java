package lt.staupasedvinas.blog.service.entity.post;

import lombok.RequiredArgsConstructor;
import lt.staupasedvinas.blog.exceptions.entity.nosuch.NoSuchPostException;
import lt.staupasedvinas.blog.model.Comment;
import lt.staupasedvinas.blog.model.Post;
import lt.staupasedvinas.blog.model.User;
import lt.staupasedvinas.blog.repository.PostRepository;
import lt.staupasedvinas.blog.service.entity.IEntityService;
import lt.staupasedvinas.blog.service.entity.CommentService;
import lt.staupasedvinas.blog.service.entity.user.RoleFactory;
import lt.staupasedvinas.blog.service.entity.user.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService implements IEntityService<Post> {

    private final PostRepository postRepository;

    private final CommentService commentService;

    private final UserService userService;

    @Override
    public void save(Post post) {
        postRepository.save(post);
    }

    @Override
    public void delete(Post post) {
        commentService.deletePostComments(post);
        postRepository.delete(post);
    }

    @Override
    public Post findById(Long id) throws NoSuchPostException {
        Optional<Post> postOptional = postRepository.findById(id);
        if (postOptional.isPresent()) {
            return postOptional.get();
        } else {
            throw new NoSuchPostException(id);
        }
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Page<Post> findAllPaginated(Pageable page) {
        return postRepository.findAll(page);
    }

    public boolean canEditOrDeletePost(String username, Post post) {
        User user;
        try {
            user = userService.getByUsername(username);
        } catch (UsernameNotFoundException e) {
            return false;
        }
        return user.getRoles().contains(RoleFactory.getAdminRole()) || post.getAuthor().equals(user);
    }

    public boolean canEditOrDeleteComment(String username, Comment comment) {
        User user;
        try {
            user = userService.getByUsername(username);
        } catch (UsernameNotFoundException e) {
            return false;
        }
        return user.getRoles().contains(RoleFactory.getAdminRole()) || comment.getAuthor().equals(user);
    }
}
