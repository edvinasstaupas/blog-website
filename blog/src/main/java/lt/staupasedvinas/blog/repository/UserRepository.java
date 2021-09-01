package lt.staupasedvinas.blog.repository;

import lt.staupasedvinas.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public User getByEmail(String email);
}
