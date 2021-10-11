package lt.staupasedvinas.blog.controller;

import lombok.RequiredArgsConstructor;
import lt.staupasedvinas.blog.model.User;
import lt.staupasedvinas.blog.service.entity.user.RoleFactory;
import lt.staupasedvinas.blog.service.entity.user.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class RegisterController {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

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
    public String registerForward(Model model, @Valid User user, BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        var dbUser1 = userService.getByEmailNoException(user.getEmail());
        var dbUser2 = userService.getByUsernameNoException(user.getUsername());
        if (bindingResult.hasErrors() || dbUser1 != null || dbUser2 != null) {
            return "redirect:/register?error";
        } else {
            userService.encodePassword(user, passwordEncoder);
            userService.addRole(user, RoleFactory.getUserRole());
            userService.save(user);
            return "redirect:/login";
        }
    }
}
