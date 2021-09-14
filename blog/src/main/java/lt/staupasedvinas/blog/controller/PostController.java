package lt.staupasedvinas.blog.controller;

import lombok.RequiredArgsConstructor;
import lt.staupasedvinas.blog.exceptions.NoSuchPostException;
import lt.staupasedvinas.blog.model.Comment;
import lt.staupasedvinas.blog.model.Post;
import lt.staupasedvinas.blog.model.PostSearch;
import lt.staupasedvinas.blog.model.User;
import lt.staupasedvinas.blog.service.CommentService;
import lt.staupasedvinas.blog.service.MessageService;
import lt.staupasedvinas.blog.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    private final LocaleResolver localeResolver;

    private final MessageService messageService;

    private final CommentService commentService;

    @GetMapping("/create-post")
    public String createPost(Model model) {
        model.addAttribute("post", new Post());
        return "post/create-post";
    }

    @GetMapping("/post")
    public String readPost(@RequestParam Long postId, Model model, HttpServletRequest httpServletRequest) {
        try {
            model = getModel(model, httpServletRequest, postId, null);
        } catch (NoSuchPostException e) {
            return "error";
        }

        return "post/post";
    }

    @PostMapping("/post")
    public String createCommentForward(@RequestParam Long postId, Model model, Comment comment, HttpServletRequest httpServletRequest) {
        return "forward:/createComment";
    }

    @PostMapping("createComment")
    public String createComment(@RequestParam Long postId, Model model, Comment comment, HttpServletRequest httpServletRequest) {
        try {
            model = getModel(model, httpServletRequest, postId, comment);
        } catch (NoSuchPostException e) {
            return "error";
        }

        return "redirect:/post?postId=" + postId;
    }

    private Post getPost(Long postId) throws NoSuchPostException {
        Optional<Post> postOptional = postService.findById(postId);
        if (postOptional.isPresent()) {
            return postOptional.get();
        } else {
            //TODO do something here ERROR
            throw new NoSuchPostException(postId);
        }
    }

    private Model getModel(Model model, HttpServletRequest httpServletRequest, Long postId, Comment comment) throws NoSuchPostException {
        var httpUser = (User) httpServletRequest.getSession().getAttribute("user");
        User user;
        if (httpUser == null) {
            user = new User();
            user.setId(-1L);
        } else {
            user = httpUser;
        }

        Post post = getPost(postId);

        if (user.getId() != -1 && comment != null) {
            comment.setPostDate(new Date());
            comment.setAuthor(user);
            comment.setPost(post);

            commentService.saveComment(comment);
            postService.update(post);
        }

        model.addAttribute("postSearch", new PostSearch());
        model.addAttribute("loggedUser", user);
        model.addAttribute("lang", localeResolver.resolveLocale(httpServletRequest).getLanguage());
        model.addAttribute("post", post);
        model.addAttribute("newComment", new Comment());
        if (httpServletRequest.getSession().getAttribute("noSearchError") == null
                || (Boolean) httpServletRequest.getSession().getAttribute("noSearchError")) {
            model.addAttribute("searchPlaceholder", messageService.getMessage("home.search-placeholder"));
        } else {
            model.addAttribute("searchPlaceholder", messageService.getMessage("home.search-placeholder-error"));
            httpServletRequest.getSession().setAttribute("noSearchError", Boolean.TRUE);
        }

        List<Comment> commentList = getCommentList(post);
        model.addAttribute("commentList", commentList);

        return model;
    }

    private List<Comment> getCommentList(Post post) {
        List<Comment> commentList = post.getCommentList();
        commentList.sort(Collections.reverseOrder(Comparator.comparing(Comment::getPostDate)));
        return commentList;
    }
}
