package lt.staupasedvinas.blog.service.entity.user;

import lombok.RequiredArgsConstructor;
import lt.staupasedvinas.blog.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
}
