package lt.staupasedvinas.blog.repository;

import lt.staupasedvinas.blog.model.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTypeRepository extends JpaRepository<UserType, Long> {
}
