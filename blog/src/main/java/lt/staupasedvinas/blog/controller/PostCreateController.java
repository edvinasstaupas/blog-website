package lt.staupasedvinas.blog.controller;

import lombok.RequiredArgsConstructor;
import lt.staupasedvinas.blog.exceptions.no_such_entity_exceptions.NoUserException;
import lt.staupasedvinas.blog.model.Post;
import lt.staupasedvinas.blog.model.User;
import lt.staupasedvinas.blog.service.post.PostCreateService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class PostCreateController {

    private final PostCreateService postCreateService;

    @GetMapping("/create-post")
    public String createPostView(Model model, HttpServletRequest request) {
        if (request.getSession().getAttribute("user") == null)
            return "redirect:";
        model.addAttribute("newPost", new Post());
        return "post/create-post";
    }

    @PostMapping("/create-post")
    public String createPostForward(Model model, @Valid Post post, BindingResult result) {
        return "forward:create-post-forward";
    }

    @PostMapping("/create-post-forward")
    public String createPost(Model model, @Valid Post post, BindingResult result, HttpServletRequest request) {
        if (result.hasErrors()) {
            //model.addAttribute("errorList", result.getAllErrors());
            model.addAttribute("newPost", post);
            return "post/create-post";
        }
        //TODO do something with that try catch
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            try {
                throw new NoUserException();
            } catch (NoUserException e) {
                return "redirect:";
            }
        }
        postCreateService.create(post, user);
        return "redirect:/post?postId=" + post.getId();
    }
}
