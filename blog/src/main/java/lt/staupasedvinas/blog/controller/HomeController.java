package lt.staupasedvinas.blog.controller;

import lombok.RequiredArgsConstructor;
import lt.staupasedvinas.blog.model.Entry;
import lt.staupasedvinas.blog.model.LogIn;
import lt.staupasedvinas.blog.service.EntryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final EntryService entryService;

    @GetMapping
    public String getHomeView(Model model) {
        model.addAttribute("entrySearch", new EntrySearch());
        model.addAttribute("logIn", new LogIn());
        //TODO remove
        System.out.println("a");
        System.out.println(entryService.findByIdAndText());
        return "home/home";
    }

    @GetMapping("/register")
    public String getRegisterView() {
        return "register";
    }

    @PostMapping
    public String searchForEntry(Model model, EntrySearch entrySearch) {
        return "blog";
    }
}
