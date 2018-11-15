package blue.bookapp.controllers;

import blue.bookapp.services.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class BookController {
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/list")
    public String listPage(Model model)
    {
        model.addAttribute("books", bookService.listBooks());
        log.debug("Loading book list page.");
        return "book/list";
    }
}
