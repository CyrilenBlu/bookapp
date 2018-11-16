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

    @GetMapping({"/","/home"})
    public String indexPage(Model model)
    {
        log.debug("Loading index page.");
        return "home";
    }

    @GetMapping("/list")
    public String listPage(Model model)
    {
        model.addAttribute("books", bookService.listBooks());
        log.debug("Loading book list page.");
        return "book/list";
    }

    @GetMapping("/contact")
    public String contactPage()
    {
        log.debug("Loading contact page.");
        return "contact";
    }

    @GetMapping("/admin-cpl")
    public String adminControl(Model model)
    {
        model.addAttribute("books", bookService.listBooks());
        return "admin/admin-cpl";
    }
}
