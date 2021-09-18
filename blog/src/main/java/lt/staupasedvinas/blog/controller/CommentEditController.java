package lt.staupasedvinas.blog.controller;

import lombok.RequiredArgsConstructor;
import lt.staupasedvinas.blog.DTO.CommentEditDTO;
import lt.staupasedvinas.blog.DTO.EditOrDeleteObj;
import lt.staupasedvinas.blog.model.Comment;
import lt.staupasedvinas.blog.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class CommentEditController {

    private Comment classComment;

    private final CommentService commentService;

    @GetMapping("/edit-comment")
    public String editCommentCreateView(Model model, HttpServletRequest httpServletRequest) {
        Map<String, ?> flashAttributes = RequestContextUtils.getInputFlashMap(httpServletRequest);
        EditOrDeleteObj editOrDeleteObj = (EditOrDeleteObj) flashAttributes.get("editOrDeleteObj");
        classComment = commentService.getComment(editOrDeleteObj.getObjId());
        if (editOrDeleteObj.getAction().equals("edit")) {
            model.addAttribute("comment", classComment);
            model.addAttribute("editedComment", new CommentEditDTO());
            return "/comment/edit-comment";
        } else if (editOrDeleteObj.getAction().equals("delete")) {
            Long postId = classComment.getPost().getId();
            commentService.deleteComment(classComment);
            return "redirect:/post/?postId=" + postId;
        }
        //if more options were added input ifs here
        return "redirect:/";
    }

    @PostMapping("edit-comment")
    public String editCommentPost(CommentEditDTO editedComment) {
        classComment.setText(editedComment.getText());
        commentService.saveComment(classComment);
        return "redirect:/post/?postId=" + classComment.getPost().getId();
    }

    /*//TODO add isEdited ir editDate
    @PostMapping("/edit-comment")
    public String editComment(Comment comment) {
        commentService.saveComment(comment);
        return "redirect:/post?postId=" + comment.getPost().getId();
    }

    @GetMapping("/delete-comment")
    public String deleteComment(Comment comment) {
        commentService.deleteComment(comment);
        return "redirect:/post?postId=" + comment.getPost().getId();
    }*/
}
