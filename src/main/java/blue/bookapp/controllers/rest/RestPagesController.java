package blue.bookapp.controllers.rest;

import blue.bookapp.api.v1.model.PagesDTO;
import blue.bookapp.services.rest_services.RestPagesService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(RestPagesController.BASE_URL)
public class RestPagesController {
    public static final String BASE_URL = "/api/v1/pages";

    private final RestPagesService restPagesService;

    public RestPagesController(RestPagesService restPagesService) {
        this.restPagesService = restPagesService;
    }

    @GetMapping({"","/"})
    @ResponseStatus(HttpStatus.OK)
    private List<PagesDTO> getPages()
    {
        return restPagesService.getPages();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private PagesDTO getPagesById(@PathVariable Long id)
    {
        return restPagesService.getPagesById(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private PagesDTO patchPage(@PathVariable Long id, @RequestBody PagesDTO pagesDTO)
    {
        return restPagesService.patchPage(id, pagesDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private String deletePagesById(@PathVariable Long id)
    {
        return restPagesService.deletePagesById(id);
    }
}
