package lt.staupasedvinas.blog.service.post;

import lombok.RequiredArgsConstructor;
import lt.staupasedvinas.blog.exceptions.NoSuchPostException;
import lt.staupasedvinas.blog.model.Post;
import lt.staupasedvinas.blog.repository.PostRepository;
import lt.staupasedvinas.blog.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    private final CommentService commentService;

    public void save(Post post) {
        postRepository.save(post);
    }

    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public List<Post> getPostList() {
        List<Post> postList = findAll();
        postList.sort(Comparator.comparing(Post::getPostDate).reversed());
        return postList;
    }

    public Post getPost(Long postId) throws NoSuchPostException {
        Optional<Post> postOptional = findById(postId);
        if (postOptional.isPresent()) {
            return postOptional.get();
        } else {
            throw new NoSuchPostException(postId);
        }
    }

    public void deletePost(Post post) {
        commentService.deletePostComments(post);
        postRepository.delete(post);
    }
}
