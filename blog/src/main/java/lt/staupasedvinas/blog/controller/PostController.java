package lt.staupasedvinas.blog.controller;

import lombok.RequiredArgsConstructor;
import lt.staupasedvinas.blog.exceptions.CommentErrorException;
import lt.staupasedvinas.blog.exceptions.NoSuchPostException;
import lt.staupasedvinas.blog.model.Comment;
import lt.staupasedvinas.blog.model.Post;
import lt.staupasedvinas.blog.model.User;
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
                model, httpServletRequest, postId, null, null, "post/post");
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
            getModel(model, httpServletRequest, postId, comment, result);
        } catch (NoSuchPostException e) {
            return "error";
        } catch (CommentErrorException e) {
            e.printStackTrace();
        }
        return returnString;
    }

    private void getModel(Model model, HttpServletRequest httpServletRequest, Long postId, Comment comment, BindingResult result) throws NoSuchPostException, CommentErrorException {
        var httpUser = (User) httpServletRequest.getSession().getAttribute("user");
        User user;
        if (httpUser == null) {
            user = new User();
            user.setId(-1L);
        } else {
            user = httpUser;
        }
        Post post = postService.getPost(postId);
        if (comment != null) {
            commentService.createComment(comment, result, user, post);
        }

        modelService.updateModel(model, user, httpServletRequest);

        model.addAttribute("commentList", commentService.getCommentList(post));
    }
}
