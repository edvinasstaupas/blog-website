package lt.staupasedvinas.blog.controller;

import lombok.RequiredArgsConstructor;
import lt.staupasedvinas.blog.model.User;
import lt.staupasedvinas.blog.model.UserType;
import lt.staupasedvinas.blog.repository.EntryRepository;
import lt.staupasedvinas.blog.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class LogInController {

    private final UserRepository userRepository;
    private final EntryRepository entryRepository;

    @GetMapping
    public String getLogInView(Model model) {
        model.addAttribute("logIn", new User());
        return "login";
    }

    @PostMapping
    public String logIn(Model model, User user) {
        User dbUser = userRepository.getByEmail(user.getEmail());
        if (dbUser == null || !dbUser.getPassword().equals(user.getPassword())) {
            model.addAttribute("logIn", user);
            model.addAttribute("message", "Bad password or no user with this email.");
            return "login";
        } else {
            model.addAttribute("user", user);
            model.addAttribute("entrySearch", new EntrySearch());
            return "home/home";
        }
    }
}
