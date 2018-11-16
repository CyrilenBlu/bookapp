package blue.bookapp.controllers;

import blue.bookapp.services.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@Slf4j
public class BookController {
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("book/{id}/info")
    public String viewBok(Model model, @PathVariable String id)
    {
        model.addAttribute("book", bookService.bookInfoById(Long.valueOf(id)));
        return "book/viewBook";
    }
}
