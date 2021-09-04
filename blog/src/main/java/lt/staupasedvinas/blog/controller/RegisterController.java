package lt.staupasedvinas.blog.controller;

import lombok.RequiredArgsConstructor;
import lt.staupasedvinas.blog.model.EntrySearch;
import lt.staupasedvinas.blog.model.User;
import lt.staupasedvinas.blog.model.UserType;
import lt.staupasedvinas.blog.repository.EntryRepository;
import lt.staupasedvinas.blog.repository.UserRepository;
import lt.staupasedvinas.blog.service.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class RegisterController {

    private final UserRepository userRepository;
    private final EntryRepository entryRepository;
    private final MessageService messageService;

    @GetMapping("/register")
    public String registerView(Model model) {
        model.addAttribute("register", new User());
        return "register";
    }

    @PostMapping("/register")
    public String forward(Model model, User user) {
        return "forward:/registerForward";
    }

    @PostMapping("/registerForward")
    public String registerForward(Model model, User user, HttpServletRequest httpServletRequest) {
        User dbUser = userRepository.getByEmail(user.getEmail());
        if (dbUser == null) {
            user.setUserType(new UserType(1L, "member"));
            userRepository.save(user);
            httpServletRequest.getSession().setAttribute("user", user);
            return "redirect:";
        } else {
            model.addAttribute("message", messageService.getMessage("user.with.email.exists"));
            model.addAttribute("register", user);
            return "register";
        }
    }
}
