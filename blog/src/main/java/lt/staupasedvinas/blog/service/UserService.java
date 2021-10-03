package lt.staupasedvinas.blog.service;

import lombok.RequiredArgsConstructor;
import lt.staupasedvinas.blog.exceptions.no_such_entity_exceptions.NoSuchUserException;
import lt.staupasedvinas.blog.model.User;
import lt.staupasedvinas.blog.model.UserType;
import lt.staupasedvinas.blog.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IModelService<User>, UserDetailsService {
    private final UserRepository userRepository;

    public User getUserFromHttpServletRequest(HttpServletRequest httpServletRequest) {
        return (User) httpServletRequest.getSession().getAttribute("user");
    }

    public void makeAdmin(Long id) throws NoSuchUserException {
        User user = findById(id);
        user.setUserType(new UserType(2L, "admin"));
        save(user);
    }

    public void removeAdmin(Long id) throws NoSuchUserException {
        User user = findById(id);
        user.setUserType(new UserType(1L, "user"));
        save(user);
    }

    public List<User> findAllSorted() {
        List<User> list = findAll();
        Collections.sort(list);
        return list;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public User findById(Long id) throws NoSuchUserException {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent())
            return optionalUser.get();
        else {
            throw new NoSuchUserException(id);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.getOptionalByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User with username: '" + username + "' not found!"));
    }
}
