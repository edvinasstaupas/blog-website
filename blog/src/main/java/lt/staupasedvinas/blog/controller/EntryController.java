package lt.staupasedvinas.blog.controller;

import lombok.RequiredArgsConstructor;
import lt.staupasedvinas.blog.model.Entry;
import lt.staupasedvinas.blog.service.EntryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class EntryController {

    private final EntryService entryService;

    @GetMapping("/create-entry")
    public String createEntry(Model model) {
        model.addAttribute("entry", new Entry());
        return "entry/create-entry";
    }

}
