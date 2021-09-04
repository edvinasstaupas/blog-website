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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogInController {

    private final UserRepository userRepository;
    private final EntryRepository entryRepository;
    private final MessageService messageService;

    @GetMapping("/login")
    public String loginView(Model model) {
        model.addAttribute("logIn", new User());
        return "login";
    }

    @PostMapping("/login")
    public String forward(Model model, User user) {
        return "forward:/loginForward";
    }

    @PostMapping("/loginForward")
    public String loginForward(Model model, User user, HttpServletRequest httpServletRequest) {
        User dbUser = userRepository.getByEmail(user.getEmail());
        if (dbUser != null && dbUser.getPassword().equals(user.getPassword())) {
            httpServletRequest.getSession().setAttribute("user", dbUser);
            return "redirect:";
        } else {
            model.addAttribute("logIn", user);
            model.addAttribute("message", messageService.getMessage("bad.psw.or.no.user"));
            return "login";
        }
    }
}
