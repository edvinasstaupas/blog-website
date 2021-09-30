package lt.staupasedvinas.blog.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lt.staupasedvinas.blog.model.PostSearch;
import lt.staupasedvinas.blog.model.User;
import lt.staupasedvinas.blog.service.ModelService;
import lt.staupasedvinas.blog.service.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ModelService modelService;

    private final UserService userService;

    @GetMapping
    public String getHomeView(Model model, HttpServletRequest httpServletRequest, @PageableDefault(size = 1, sort = {"postDate"}, direction = Sort.Direction.DESC) Pageable page) {
        modelService.updateHomeModel(model, httpServletRequest, page);
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
        httpServletRequest.getSession().setAttribute("noSearchError", Boolean.TRUE);
        return "redirect:";
    }
}
