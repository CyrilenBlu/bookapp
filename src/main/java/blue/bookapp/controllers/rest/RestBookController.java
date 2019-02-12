package blue.bookapp.controllers.rest;

import blue.bookapp.api.v1.model.BookDTO;
import blue.bookapp.services.rest_services.RestBookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(RestBookController.BASE_URL)
public class RestBookController {
    public static final String BASE_URL = "/api/v1/books";

    private final RestBookService restBookService;

    public RestBookController(RestBookService restBookService) {
        this.restBookService = restBookService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    private List<BookDTO> getBooks()
    {
        return restBookService.getBooks();
    }


}
