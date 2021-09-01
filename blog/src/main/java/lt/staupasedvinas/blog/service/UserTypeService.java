package lt.staupasedvinas.blog.service;

import lombok.RequiredArgsConstructor;
import lt.staupasedvinas.blog.model.User;
import lt.staupasedvinas.blog.repository.UserRepository;
import lt.staupasedvinas.blog.repository.UserTypeRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserTypeService {
    private final UserTypeRepository userTypeRepository;
}
