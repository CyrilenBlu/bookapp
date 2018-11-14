package blue.bookapp.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class ContactController {
    @GetMapping("/contact")
    public String contactPage()
    {
        log.debug("Loading contact page.");
        return "contact";
    }
}
