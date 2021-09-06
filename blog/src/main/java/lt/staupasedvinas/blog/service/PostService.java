package lt.staupasedvinas.blog.service;

import lombok.RequiredArgsConstructor;
import lt.staupasedvinas.blog.model.Post;
import lt.staupasedvinas.blog.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public void createPost(Post post) {
        postRepository.save(post);
        post.getAuthor().addPost(post);
    }
}
