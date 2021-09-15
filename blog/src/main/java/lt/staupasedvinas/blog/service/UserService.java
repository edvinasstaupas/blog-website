package lt.staupasedvinas.blog.service;

import lombok.RequiredArgsConstructor;
import lt.staupasedvinas.blog.model.User;
import lt.staupasedvinas.blog.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUserFromHttpServletRequest(HttpServletRequest httpServletRequest) {
        var httpUser = (User) httpServletRequest.getSession().getAttribute("user");
        User user;
        if (httpUser == null) {
            user = new User();
            user.setId(-1L);
        } else {
            user = httpUser;
        }
        return user;
    }
}
