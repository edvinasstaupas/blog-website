package lt.staupasedvinas.blog.controller;

import lombok.RequiredArgsConstructor;
import lt.staupasedvinas.blog.exceptions.CommentErrorException;
import lt.staupasedvinas.blog.exceptions.NoSuchPostException;
import lt.staupasedvinas.blog.exceptions.NoUserException;
import lt.staupasedvinas.blog.model.Comment;
import lt.staupasedvinas.blog.service.CommentService;
import lt.staupasedvinas.blog.service.ModelService;
import lt.staupasedvinas.blog.service.post.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    private final CommentService commentService;

    private final ModelService modelService;

    @GetMapping("/post")
    public String readPost(@RequestParam Long postId, Model model, HttpServletRequest httpServletRequest) {
        return getModelAfterExceptions(
                model, httpServletRequest, postId);
    }

    @PostMapping("/post")
    public String createCommentForward(@RequestParam Long postId, Model model, @Valid Comment comment, BindingResult result, HttpServletRequest httpServletRequest) {
        return "forward:/createComment";
    }

    @PostMapping("/createComment")
    public String postCreateComment(@RequestParam Long postId, Model model, @Valid Comment comment, BindingResult result, HttpServletRequest httpServletRequest) {
        return getModelAfterExceptions(
                model, httpServletRequest, postId, comment, result, "redirect:/post?postId=" + postId);
    }

    private String getModelAfterExceptions(Model model, HttpServletRequest httpServletRequest, Long postId, Comment comment, BindingResult result, String returnString) {
        try {
            modelService.updatePostModel(model, httpServletRequest, postId, comment, result);
        } catch (NoSuchPostException e) {
            return "error";
        } catch (CommentErrorException e) {
            return "error";
        } catch (NoUserException e) {
            return "error";
        }
        return returnString;
    }

    private String getModelAfterExceptions(Model model, HttpServletRequest httpServletRequest, Long postId) {
        try {
            modelService.updatePostModel(model, httpServletRequest, postId);
        } catch (NoSuchPostException e) {
            return "error";
        }
        return "post/post";
    }
}
