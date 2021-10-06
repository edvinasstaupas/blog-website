package lt.staupasedvinas.blog.service.user;

import lombok.RequiredArgsConstructor;
import lt.staupasedvinas.blog.exceptions.authentication_exceptions.EmailNotFoundException;
import lt.staupasedvinas.blog.exceptions.no_such_entity_exceptions.NoSuchUserException;
import lt.staupasedvinas.blog.model.Role;
import lt.staupasedvinas.blog.model.User;
import lt.staupasedvinas.blog.repository.UserRepository;
import lt.staupasedvinas.blog.service.IModelService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
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
        var userPrinciple = httpServletRequest.getUserPrincipal();
        if (userPrinciple == null)
            return null;
        return getByUsername(userPrinciple.getName());
    }

    public void makeAdmin(Long id) throws NoSuchUserException {
        User user = findById(id);
        addRole(user, RoleFactory.getAdminRole());
        save(user);
    }

    public void removeAdmin(Long id) throws NoSuchUserException {
        User user = findById(id);
        removeRole(user, RoleFactory.getAdminRole());
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

    public User getByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new EmailNotFoundException("User with email: '" + email + "' not found!"));
    }

    public User getByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User with username: '" + username + "' not found!"));
    }

    public User getByEmailNoException(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public User getByUsernameNoException(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public void removeRole(User user, Role role) {
        user.getRoles().removeIf(r -> r.equals(role));

    }

    public void addRole(User user, Role role) {
        user.getRoles().add(role);
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
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User with username: '" + username + "' not found!"));
    }

    public void encodePassword(User user, PasswordEncoder passwordEncoder) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }
}
