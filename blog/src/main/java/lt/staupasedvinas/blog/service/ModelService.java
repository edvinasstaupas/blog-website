package lt.staupasedvinas.blog.service;

import lombok.RequiredArgsConstructor;
import lt.staupasedvinas.blog.exceptions.CommentErrorException;
import lt.staupasedvinas.blog.exceptions.NoSuchPostException;
import lt.staupasedvinas.blog.exceptions.NoUserException;
import lt.staupasedvinas.blog.model.*;
import lt.staupasedvinas.blog.service.post.PostService;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class ModelService {

    private final PostService postService;

    private final UserService userService;

    private final LocaleResolver localeResolver;

    private final MessageService messageService;

    private final CommentService commentService;

    public void updateHomeModel(Model model, HttpServletRequest httpServletRequest) {
        updateBaseModel(model, httpServletRequest);
        model.addAttribute("posts", postService.getPostList());
    }

    public void updatePostModel(Model model, HttpServletRequest httpServletRequest, Long postId, Comment comment, BindingResult result) throws NoSuchPostException, NoUserException, CommentErrorException {
        User user = userService.getUserFromHttpServletRequest(httpServletRequest);
        Post post = postService.getPost(postId);
        commentService.createComment(comment, result, user, post);
        updateBasePostModel(model, httpServletRequest, post);
    }

    public void updatePostModel(Model model, HttpServletRequest httpServletRequest, Long postId) throws NoSuchPostException {
        Post post = postService.getPost(postId);
        updateBasePostModel(model, httpServletRequest, post);
    }

    private void updateBasePostModel(Model model, HttpServletRequest httpServletRequest, Post post) {
        updateBaseModel(model, httpServletRequest);
        if (userService.getUserFromHttpServletRequest(httpServletRequest) != null)
            model.addAttribute("newComment", new Comment());
        model.addAttribute("post", post);
        model.addAttribute("commentList", commentService.getCommentList(post));
        model.addAttribute("editComment", new Comment());
    }

    private void updateBaseModel(Model model, HttpServletRequest httpServletRequest) {
        model.addAttribute("postSearch", new PostSearch());
        model.addAttribute("loggedUser", userService.getUserFromHttpServletRequest(httpServletRequest));
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
