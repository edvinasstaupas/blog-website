package lt.staupasedvinas.blog.controller;

import lombok.RequiredArgsConstructor;
import lt.staupasedvinas.blog.model.PostSearch;
import lt.staupasedvinas.blog.model.User;
import lt.staupasedvinas.blog.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private User user;

    private final LocaleResolver localeResolver;

    private final PostService postService;

    @GetMapping
    public String getHomeView(Model model, HttpServletRequest httpServletRequest) {
        var httpUser = (User) httpServletRequest.getSession().getAttribute("user");

        if (httpUser == null) {
            user = new User();
            user.setId(-1L);
        } else {
            user = httpUser;
        }
        System.out.println(user);

        model.addAttribute("postSearch", new PostSearch());
        model.addAttribute("loggedUser", user);
        model.addAttribute("lang", localeResolver.resolveLocale(httpServletRequest).getLanguage());
        System.out.println(localeResolver.resolveLocale(httpServletRequest).getLanguage());
        return "home/home";
    }

    @PostMapping
    public String searchForPost(Model model, PostSearch postSearch) {
        return "post";
    }
}
