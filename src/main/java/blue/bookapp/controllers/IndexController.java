package blue.bookapp.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class IndexController {

    @GetMapping("/")
    public String indexPage(Model model)
    {
        log.debug("Loading index page.");
        return "home";
    }

    @GetMapping("/list")
    public String listPage(Model model)
    {
        return "list";
    }
}
