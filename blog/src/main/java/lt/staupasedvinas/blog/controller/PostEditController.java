package lt.staupasedvinas.blog.controller;

import lombok.RequiredArgsConstructor;
import lt.staupasedvinas.blog.DTO.EditOrDeleteObj;
import lt.staupasedvinas.blog.DTO.PostEditDTO;
import lt.staupasedvinas.blog.exceptions.no_such_entity_exceptions.NoSuchPostException;
import lt.staupasedvinas.blog.model.Post;
import lt.staupasedvinas.blog.service.entity_services.post.PostService;
import org.springframework.security.access.prepost.PreAuthorize;
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
@PreAuthorize("hasRole('MEMBER')")
@RequestMapping("/edit-post")
public class PostEditController {

    private Post classPost;

    private final PostService postService;

    //TODO kazka daryt su exception
    @GetMapping()
    public String editPostCreateView(Model model, HttpServletRequest httpServletRequest) throws NoSuchPostException {
        Map<String, ?> flashAttributes = RequestContextUtils.getInputFlashMap(httpServletRequest);
        EditOrDeleteObj editOrDeleteObj = (EditOrDeleteObj) flashAttributes.get("editOrDeleteObj");
        classPost = postService.findById(editOrDeleteObj.getObjId());
        if (editOrDeleteObj.getAction().equals("edit")) {
            model.addAttribute("post", classPost);
            model.addAttribute("editedPost", new PostEditDTO());
            return "/post/edit-post";
        } else if (editOrDeleteObj.getAction().equals("delete")) {
            postService.delete(classPost);
            return "redirect:/";
        }
        //if more options were added input ifs here
        return "redirect:/";
    }

    @PostMapping()
    public String editPostPost(PostEditDTO editedPost) {
        classPost.setTitle(editedPost.getTitle());
        classPost.setText(editedPost.getText());
        postService.save(classPost);
        return "redirect:/post/?postId=" + classPost.getId();
    }

    //TODO add isEdited ir editDate
}
