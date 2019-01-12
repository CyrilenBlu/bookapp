package blue.bookapp.controllers;

import blue.bookapp.services.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class NavController {

    private BookService bookService;

    public NavController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping({"/","/homepage"})
    public String indexPage()
    {
        log.debug("Loading index page.");
        return "home";
    }

    @GetMapping("/listpage")
    public String listPage(Model model)
    {
        model.addAttribute("books", bookService.listBooks());
        log.debug("Loading book list page.");
        return "book/list";
    }

    @GetMapping("/contactpage")
    public String contactPage()
    {
        log.debug("Loading contact page.");
        return "contact";
    }

}
