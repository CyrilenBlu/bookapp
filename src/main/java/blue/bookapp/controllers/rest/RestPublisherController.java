package blue.bookapp.controllers.rest;

import blue.bookapp.api.v1.model.PublisherDTO;
import blue.bookapp.services.rest_services.RestPublisherService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(RestPublisherController.BASE_URL)
public class RestPublisherController {
    public static final String BASE_URL = "/api/v1/publishers";

    private final RestPublisherService restPublisherService;

    public RestPublisherController(RestPublisherService restPublisherService) {
        this.restPublisherService = restPublisherService;
    }

    @GetMapping({"","/"})
    @ResponseStatus(HttpStatus.OK)
    private List<PublisherDTO> getPublishers()
    {
        return restPublisherService.getPublishers();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private PublisherDTO getPublisherById(@PathVariable Long id) { return restPublisherService.getPublisherById(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private PublisherDTO createNewPublisher(@RequestBody PublisherDTO publisherDTO)
    {
        return restPublisherService.createNewPublisher(publisherDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private String deletePublisherById(@PathVariable Long id) { return restPublisherService.deletePublisherById(id); }
}
