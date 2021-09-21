package lt.staupasedvinas.blog.controller;

import lombok.RequiredArgsConstructor;
import lt.staupasedvinas.blog.DTO.EditOrDeleteObj;
import lt.staupasedvinas.blog.exceptions.NoSuchUserException;
import lt.staupasedvinas.blog.service.ModelService;
import lt.staupasedvinas.blog.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin-panel")
public class AdminPanelController {

    private final UserService userService;

    private final ModelService modelService;

    @GetMapping
    public String getAdminPanelView(Model model, HttpServletRequest httpServletRequest) {
        modelService.updateHomeModel(model, httpServletRequest);
        model.addAttribute("users", userService.findAllSorted());
        EditOrDeleteObj editOrDeleteObj = new EditOrDeleteObj();
        editOrDeleteObj.setObj("user");
        model.addAttribute("editOrDeleteObj", editOrDeleteObj);
        return "admin/admin-panel";
    }

    @PostMapping
    public String postAdminPanelView(EditOrDeleteObj editOrDeleteObj) {
        if (editOrDeleteObj.getAction().equals("makeAdmin")) {
            try {
                userService.makeAdmin(editOrDeleteObj.getObjId());
            } catch (NoSuchUserException e) {
                return "error";
            }
        } else if (editOrDeleteObj.getAction().equals("removeAdmin")) {
            try {
                userService.removeAdmin(editOrDeleteObj.getObjId());
            } catch (NoSuchUserException e) {
                return "error";
            }
        }
        return "redirect:/admin-panel";
    }
}
