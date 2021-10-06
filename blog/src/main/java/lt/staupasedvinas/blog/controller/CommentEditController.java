package lt.staupasedvinas.blog.controller;

import lombok.RequiredArgsConstructor;
import lt.staupasedvinas.blog.DTO.CommentEditDTO;
import lt.staupasedvinas.blog.DTO.EditOrDeleteObj;
import lt.staupasedvinas.blog.model.Comment;
import lt.staupasedvinas.blog.service.CommentService;
import lt.staupasedvinas.blog.exceptions.no_such_entity_exceptions.NoSuchCommentException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/edit-comment")
public class CommentEditController {

    private Comment classComment;

    private final CommentService commentService;

    @GetMapping()
    public String editCommentCreateView(Model model, HttpServletRequest httpServletRequest) {
        Map<String, ?> flashAttributes = RequestContextUtils.getInputFlashMap(httpServletRequest);
        EditOrDeleteObj editOrDeleteObj = (EditOrDeleteObj) flashAttributes.get("editOrDeleteObj");
        try {
            classComment = commentService.findById(editOrDeleteObj.getObjId());
        } catch (NoSuchCommentException e) {
            return "error";
        }
        if (editOrDeleteObj.getAction().equals("edit")) {
            model.addAttribute("comment", classComment);
            model.addAttribute("editedComment", new CommentEditDTO());
            return "/comment/edit-comment";
        } else if (editOrDeleteObj.getAction().equals("delete")) {
            Long postId = classComment.getPost().getId();
            commentService.delete(classComment);
            return "redirect:/post/?postId=" + postId;
        }
        //if more options were added input ifs here
        return "redirect:/";
    }

    @PostMapping()
    public String editCommentPost(CommentEditDTO editedComment) {
        classComment.setText(editedComment.getText());
        commentService.save(classComment);
        return "redirect:/post/?postId=" + classComment.getPost().getId();
    }
}
