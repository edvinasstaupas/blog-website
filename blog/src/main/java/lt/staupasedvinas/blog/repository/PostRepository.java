package lt.staupasedvinas.blog.repository;

import lt.staupasedvinas.blog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Long> {
    Post findByIdAndText(Long id, String text);
}
