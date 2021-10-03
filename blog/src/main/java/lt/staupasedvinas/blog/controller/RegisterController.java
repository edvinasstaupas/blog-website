package lt.staupasedvinas.blog.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lt.staupasedvinas.blog.model.User;
import lt.staupasedvinas.blog.service.MessageService;
import lt.staupasedvinas.blog.service.RoleFactory;
import lt.staupasedvinas.blog.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequiredArgsConstructor
public class RegisterController {

    private final UserService userService;

    private final MessageService messageService;

    @GetMapping("/register")
    public String registerView(Model model) {
        model.addAttribute("register", new User());
        return "log-reg/register";
    }

    @PostMapping("/register")
    public String forward(Model model, User user) {
        return "forward:/registerForward";
    }

    @PostMapping("/registerForward")
    public String registerForward(Model model, User user, HttpServletRequest httpServletRequest) {
        User dbUser1 = userService.getByEmail(user.getEmail());
        User dbUser2 = userService.getByUsername(user.getUsername());
        if (dbUser1 != null) {
            model.addAttribute("msg", messageService.getMessage("user-with-email-exists"));
            model.addAttribute("register", user);
            log.info("User tried to register with already used email.");
            return "log-reg/register";
        } else if (dbUser2 != null) {
            model.addAttribute("msg", messageService.getMessage("user-with-username-exists"));
            model.addAttribute("register", user);
            log.info("User tried to register with already used username.");
            return "log-reg/register";
        } else {
            userService.addRole(user, RoleFactory.getUserRole());
            userService.save(user);
            httpServletRequest.getSession().setAttribute("user", user);
            return "redirect:";
        }
    }
}
