package blue.bookapp.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class BookController {
    @GetMapping("/list")
    public String listPage()
    {
        log.debug("Loading book list page.");
        return "list";
    }
}
