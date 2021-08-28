package lt.staupasedvinas.blog.controller;

import lombok.extern.java.Log;
import lt.staupasedvinas.blog.model.LogIn;
import lt.staupasedvinas.blog.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LogInController {

    @GetMapping
    public String getLogInView(Model model) {
        model.addAttribute("logIn", new LogIn());
        return "login";
    }

    @PostMapping
    public String logIn(Model model, LogIn logIn) {
        System.out.println(logIn.toString());
        model.addAttribute("entrySearch", new EntrySearch());
        return "home/home";
    }
}
