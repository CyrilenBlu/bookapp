package blue.bookapp.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class IndexController {

    @GetMapping({"/","/home"})
    public String indexPage(Model model)
    {
        log.debug("Loading index page.");
        return "home";
    }
}
