package lt.staupasedvinas.blog.repository;

import lt.staupasedvinas.blog.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
