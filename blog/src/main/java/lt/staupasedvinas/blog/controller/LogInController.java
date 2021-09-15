package lt.staupasedvinas.blog.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lt.staupasedvinas.blog.model.User;
import lt.staupasedvinas.blog.repository.PostRepository;
import lt.staupasedvinas.blog.repository.UserRepository;
import lt.staupasedvinas.blog.service.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LogInController {

    private final UserRepository userRepository;

    private final PostRepository postRepository;

    private final MessageService messageService;

    @GetMapping("/login")
    public String loginView(Model model) {
        model.addAttribute("logIn", new User());
        return "log-reg/login";
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
            model.addAttribute("msg", messageService.getMessage("bad-psw-or-no-user"));
            log.info("User tried to log in with bad password or there is no user with such email");
            return "log-reg/login";
        }
    }
}
