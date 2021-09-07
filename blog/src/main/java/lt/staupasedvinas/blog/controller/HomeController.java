package lt.staupasedvinas.blog.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lt.staupasedvinas.blog.model.PostSearch;
import lt.staupasedvinas.blog.model.User;
import lt.staupasedvinas.blog.service.MessageService;
import lt.staupasedvinas.blog.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private User user;

    private final LocaleResolver localeResolver;

    private final PostService postService;

    private final MessageService messageService;

    @GetMapping
    public String getHomeView(Model model, HttpServletRequest httpServletRequest) {
        var httpUser = (User) httpServletRequest.getSession().getAttribute("user");

        if (httpUser == null) {
            user = new User();
            user.setId(-1L);
        } else {
            user = httpUser;
        }
        log.info(user.toString());

        model.addAttribute("postSearch", new PostSearch());
        model.addAttribute("loggedUser", user);
        model.addAttribute("lang", localeResolver.resolveLocale(httpServletRequest).getLanguage());
        if (httpServletRequest.getSession().getAttribute("noSearchError") == null
                || (Boolean) httpServletRequest.getSession().getAttribute("noSearchError")) {
            model.addAttribute("searchPlaceholder", messageService.getMessage("home.search-placeholder"));
        } else {
            model.addAttribute("searchPlaceholder", messageService.getMessage("home.search-placeholder-error"));
            httpServletRequest.getSession().setAttribute("noSearchError", Boolean.TRUE);
        }
        log.info(localeResolver.resolveLocale(httpServletRequest).getLanguage());

        return "home/home";
    }

    @PostMapping
    public String forward(Model model, PostSearch postSearch) {
        return "forward:searchForward";
    }

    @PostMapping("searchForward")
    public String searchForPostRedirect(Model model, @Valid PostSearch postSearch, BindingResult errors, HttpServletRequest httpServletRequest) {
        if (errors.hasErrors()) {
            httpServletRequest.getSession().setAttribute("noSearchError", Boolean.FALSE);
            return "redirect:";
        }
        return "post/post";
    }
}
