package blue.bookapp.controllers.rest;

import blue.bookapp.api.v1.model.AuthorDTO;
import blue.bookapp.services.rest_services.RestAuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(RestAuthorController.BASE_URL)
public class RestAuthorController {
    public static final String BASE_URL = "/api/v1/authors";

    private final RestAuthorService restAuthorService;

    public RestAuthorController(RestAuthorService restAuthorService) {
        this.restAuthorService = restAuthorService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    private List<AuthorDTO> getAuthors()
    {
        return restAuthorService.getAuthors();
    }
}
