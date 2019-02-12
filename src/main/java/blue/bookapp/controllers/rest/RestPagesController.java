package blue.bookapp.controllers.rest;

import blue.bookapp.api.v1.model.PagesDTO;
import blue.bookapp.services.rest_services.RestPagesService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(RestPagesController.BASE_URL)
public class RestPagesController {
    public static final String BASE_URL = "/api/v1/pages";

    private final RestPagesService restPagesService;

    public RestPagesController(RestPagesService restPagesService) {
        this.restPagesService = restPagesService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    private List<PagesDTO> getPages()
    {
        return restPagesService.getPages();
    }
}
