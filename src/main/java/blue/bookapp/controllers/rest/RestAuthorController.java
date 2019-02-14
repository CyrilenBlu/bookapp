package blue.bookapp.controllers.rest;

import blue.bookapp.api.v1.model.AuthorDTO;
import blue.bookapp.services.rest_services.RestAuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(RestAuthorController.BASE_URL)
public class RestAuthorController {
    public static final String BASE_URL = "/api/v1/authors";

    private final RestAuthorService restAuthorService;

    public RestAuthorController(RestAuthorService restAuthorService) {
        this.restAuthorService = restAuthorService;
    }

    @GetMapping({"","/"})
    @ResponseStatus(HttpStatus.OK)
    private List<AuthorDTO> getAuthors()
    {
        return restAuthorService.getAuthors();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private AuthorDTO getAuthorById(@PathVariable Long id)
    {
        return restAuthorService.getAuthorById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private AuthorDTO createNewAuthor(@RequestBody AuthorDTO authorDTO)
    {
        return restAuthorService.createNewAuthor(authorDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private AuthorDTO updateAuthor(@PathVariable Long id, @RequestBody AuthorDTO authorDTO)
    {
        return restAuthorService.updateAuthor(id, authorDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private String deleteAuthorById(@PathVariable Long id)
    {
        return restAuthorService.deleteAuthorById(id);
    }
}
