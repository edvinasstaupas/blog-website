package lt.staupasedvinas.blog.controller;

import lombok.RequiredArgsConstructor;
import lt.staupasedvinas.blog.model.User;
import lt.staupasedvinas.blog.repository.EntryRepository;
import lt.staupasedvinas.blog.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterController {

    private final UserRepository userRepository;
    private final EntryRepository entryRepository;

    @GetMapping
    public String getRegisterView(Model model) {
        model.addAttribute("register", new User());
        return "register";
    }

    @PostMapping
    public String register(Model model, User user) {
        userRepository.save(user);

        model.addAttribute("entrySearch", new EntrySearch());
        return "home/home";
    }
}
