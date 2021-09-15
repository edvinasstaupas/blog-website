package lt.staupasedvinas.blog.repository;

import lt.staupasedvinas.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User getByEmail(String email);

    User getByUsername(String username);
}
