package lt.staupasedvinas.blog.service.post;

import lombok.RequiredArgsConstructor;
import lt.staupasedvinas.blog.exceptions.NoSuchPostException;
import lt.staupasedvinas.blog.model.Post;
import lt.staupasedvinas.blog.repository.PostRepository;
import lt.staupasedvinas.blog.service.CommentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService implements IService<Post> {

    private final PostRepository postRepository;

    private final CommentService commentService;

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
        Optional<Post> postOptional = find(id);
        if (postOptional.isPresent()) {
            return postOptional.get();
        } else {
            throw new NoSuchPostException(id);
        }
    }

    @Override
    public Optional<Post> find(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Page<Post> findAllPaginated(Pageable page) {
        return postRepository.findAll(page);
    }
}
