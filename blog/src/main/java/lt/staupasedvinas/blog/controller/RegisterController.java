package lt.staupasedvinas.blog.controller;

import lombok.RequiredArgsConstructor;
import lt.staupasedvinas.blog.model.EntrySearch;
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
@RequestMapping()
@RequiredArgsConstructor
public class RegisterController {

    private final UserRepository userRepository;
    private final EntryRepository entryRepository;

    @GetMapping("/register")
    public String registerView(Model model) {
        model.addAttribute("register", new User());
        return "register";
    }

    @PostMapping("/register")
    public String redirect(Model model, User user) {
        return "redirect:/registerRedirect";
    }

    @GetMapping("/registerRedirect")
    public String registerRedirect(Model model, User user) {
        User dbUser = userRepository.getByEmail(user.getEmail());
        if (dbUser == null) {
            user.setUserType(new UserType(1L, "member"));
            userRepository.save(user);
            model.addAttribute("entrySearch", new EntrySearch());
            return "home/home";
        } else {
            model.addAttribute("message", "There already is a user with this email, please log in");
            model.addAttribute("register", new User());
            return "register";
        }
    }
}
