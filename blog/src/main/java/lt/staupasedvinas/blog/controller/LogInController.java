package lt.staupasedvinas.blog.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Slf4j
@Controller
@RequiredArgsConstructor
public class LogInController {

    @GetMapping("/login")
    public String loginView() {
        return "log-reg/login";
    }
}
