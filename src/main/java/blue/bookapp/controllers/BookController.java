package blue.bookapp.controllers;

import blue.bookapp.services.BookService;
import blue.bookapp.services.PagesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@Slf4j
public class BookController {
    private BookService bookService;
    private PagesService pagesService;

    public BookController(BookService bookService, PagesService pagesService) {
        this.bookService = bookService;
        this.pagesService = pagesService;
    }

    @GetMapping("book/{id}/info")
    public String viewBook(Model model, @PathVariable String id)
    {
        model.addAttribute("book", bookService.bookInfoById(Long.valueOf(id)));
        model.addAttribute("page", pagesService.listPagesByBookId(Long.valueOf(id)));
        return "book/viewBook";
    }

    @GetMapping("book/{id}/read/page/{pageId}")
    public String readBook(@PathVariable String id, @PathVariable String pageId, Model model)
    {
        model.addAttribute("pages", pagesService.listPagesByBookId(Long.valueOf(id)));
        model.addAttribute("page", pagesService.getPagesByBookById(Long.valueOf(id), Long.valueOf(pageId)));
        return "book/read";

    }
}
