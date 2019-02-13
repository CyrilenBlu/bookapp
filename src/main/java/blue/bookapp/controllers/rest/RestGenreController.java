package blue.bookapp.controllers.rest;

import blue.bookapp.api.v1.model.GenreDTO;
import blue.bookapp.services.rest_services.RestGenreService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(RestGenreController.BASE_URL)
public class RestGenreController {
    public static final String BASE_URL = "/api/v1/genres";

    private final RestGenreService restGenreService;

    public RestGenreController(RestGenreService restGenreService) {
        this.restGenreService = restGenreService;
    }

    @GetMapping({"","/"})
    @ResponseStatus(HttpStatus.OK)
    private List<GenreDTO> getGenres()
    {
        return restGenreService.getGenres();
    }
}
