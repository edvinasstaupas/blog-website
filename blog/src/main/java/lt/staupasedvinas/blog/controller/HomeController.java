package lt.staupasedvinas.blog.controller;

import lombok.RequiredArgsConstructor;
import lt.staupasedvinas.blog.model.EntrySearch;
import lt.staupasedvinas.blog.model.User;
import lt.staupasedvinas.blog.service.EntryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private User user;

    private final EntryService entryService;

    @GetMapping
    public String getHomeView(Model model, User modelUser) {
        user = modelUser;
        System.out.println(user);

        model.addAttribute("entrySearch", new EntrySearch());
        model.addAttribute("user", user);

        return "home/home";
    }

    @PostMapping
    public String searchForEntry(Model model, EntrySearch entrySearch) {
        return "blog";
    }
}
