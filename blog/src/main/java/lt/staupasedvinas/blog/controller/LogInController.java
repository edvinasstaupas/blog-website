package lt.staupasedvinas.blog.controller;

import lombok.RequiredArgsConstructor;
import lt.staupasedvinas.blog.model.Entry;
import lt.staupasedvinas.blog.model.LogIn;
import lt.staupasedvinas.blog.model.User;
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
        model.addAttribute("logIn", new LogIn());
        return "login";
    }

    @PostMapping
    public String logIn(Model model, LogIn logIn) {
        System.out.println(logIn.toString());
        //TODO remove
        userRepository.save(new User(2L, "a", "a", "a"));
        entryRepository.save(new Entry(1L, "title", "text"));
        model.addAttribute("entrySearch", new EntrySearch());
        return "home/home";
    }
}
