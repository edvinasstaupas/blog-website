package lt.staupasedvinas.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
    @GetMapping
    public String getHomeView(Model model) {
        model.addAttribute("entrySearch", new EntrySearch());
        return "home/home";
    }
/*

    @GetMapping("/login")
    public String getLogInView() {
        return "login";
    }

    @GetMapping("/register")
    public String getRegisterView() {
        return "register";
    }
*/

    @PostMapping
    public String searchForEntry(Model model, EntrySearch entrySearch){

        return "blog";
    }
}
