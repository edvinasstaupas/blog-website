package lt.staupasedvinas.blog.service.post;

import lombok.RequiredArgsConstructor;
import lt.staupasedvinas.blog.model.Post;
import lt.staupasedvinas.blog.model.User;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class PostCreateService {

    private final PostService postService;

    public void create(Post post, User user) {
        post.setPostDate(new Date());
        post.setAuthor(user);
        postService.save(post);
    }
}
