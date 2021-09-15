package lt.staupasedvinas.blog.service;

import lombok.RequiredArgsConstructor;
import lt.staupasedvinas.blog.model.PostSearch;
import lt.staupasedvinas.blog.model.User;
import lt.staupasedvinas.blog.service.post.PostService;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class ModelService {

    private final PostService postService;

    private final LocaleResolver localeResolver;

    private final MessageService messageService;

    public void updateModel(Model model, User user, HttpServletRequest httpServletRequest) {
        model.addAttribute("posts", postService.getPostList());
        model.addAttribute("postSearch", new PostSearch());
        model.addAttribute("loggedUser", user);
        model.addAttribute("lang", localeResolver.resolveLocale(httpServletRequest).getLanguage());
        if (httpServletRequest.getSession().getAttribute("noSearchError") == null
                || (Boolean) httpServletRequest.getSession().getAttribute("noSearchError")) {
            model.addAttribute("searchPlaceholder", messageService.getMessage("home.search-placeholder"));
        } else {
            //TODO if ever want to implement search, change message
            //TODO if not remove PostRepository method findByIdAndText and HomeController POST methods
            model.addAttribute("searchPlaceholder", messageService.getMessage("home.search-placeholder-error"));
            httpServletRequest.getSession().setAttribute("noSearchError", Boolean.TRUE);
        }
    }
}
