package blue.bookapp.controllers.rest;

import blue.bookapp.api.v1.model.BookDTO;
import blue.bookapp.api.v1.model.PagesDTO;
import blue.bookapp.services.rest_services.RestBookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(RestBookController.BASE_URL)
public class RestBookController {
    public static final String BASE_URL = "/api/v1/books";

    private final RestBookService restBookService;

    public RestBookController(RestBookService restBookService) {
        this.restBookService = restBookService;
    }

    @GetMapping({"","/"})
    @ResponseStatus(HttpStatus.OK)
    private List<BookDTO> getBooks()
    {
        return restBookService.getBooks();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private BookDTO getBookById(@PathVariable Long id)
    {
        return restBookService.getBookById(id);
    }

    @GetMapping("/{id}/exclimg")
    @ResponseStatus(HttpStatus.OK)
    private BookDTO getBookByIdExclImg(@PathVariable Long id)
    {
        return restBookService.getBookByIdExclImg(id);
    }

    @GetMapping("/{bookId}/page/{pageNumber}")
    @ResponseStatus(HttpStatus.OK)
    private PagesDTO getPageNumberByBookId(@PathVariable Long bookId, @PathVariable int pageNumber)
    {
        return restBookService.getPageNumberByBookId(bookId, pageNumber);
    }

    @GetMapping("/{bookId}/pages")
    @ResponseStatus(HttpStatus.OK)
    private List<PagesDTO> getPagesByBookId(@PathVariable Long bookId)
    {
        return restBookService.getPagesByBookId(bookId);
    }

    @PostMapping("{bookId}/pages")
    @ResponseStatus(HttpStatus.CREATED)
    private PagesDTO createNewPage(@PathVariable Long bookId, @RequestBody PagesDTO pagesDTO)
    {
        return restBookService.createNewPage(bookId, pagesDTO);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private BookDTO createNewBook(@RequestBody BookDTO bookDTO)
    {
        return restBookService.createBook(bookDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private String deleteBookById(@PathVariable Long id)
    {
        return restBookService.deleteBookById(id);
    }
}
