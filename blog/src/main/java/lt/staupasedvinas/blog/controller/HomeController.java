package lt.staupasedvinas.blog.controller;

import lombok.RequiredArgsConstructor;
import lt.staupasedvinas.blog.model.EntrySearch;
import lt.staupasedvinas.blog.model.User;
import lt.staupasedvinas.blog.service.EntryService;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private User user;

    private final LocaleResolver localeResolver;

    private final EntryService entryService;

    @GetMapping
    public String getHomeView(Model model, HttpServletRequest httpServletRequest) {
        var httpUser = (User) httpServletRequest.getSession().getAttribute("user");

        if (httpUser == null) {
            user = new User();
        } else {
            user = httpUser;
        }
        System.out.println(user);

        model.addAttribute("entrySearch", new EntrySearch());
        model.addAttribute("user", user);
        model.addAttribute("lang", localeResolver.resolveLocale(httpServletRequest).getLanguage());
        System.out.println(localeResolver.resolveLocale(httpServletRequest).getLanguage());
        return "home/home";
    }

    @PostMapping
    public String searchForEntry(Model model, EntrySearch entrySearch) {
        return "blog";
    }
}
