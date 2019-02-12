package blue.bookapp.controllers.rest;

import blue.bookapp.api.v1.model.PublisherDTO;
import blue.bookapp.services.rest_services.RestPublisherService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(RestPublisherController.BASE_URL)
public class RestPublisherController {
    public static final String BASE_URL = "/api/v1/publishers";

    private final RestPublisherService restPublisherService;

    public RestPublisherController(RestPublisherService restPublisherService) {
        this.restPublisherService = restPublisherService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    private List<PublisherDTO> getPublishers()
    {
        return restPublisherService.getPublishers();
    }
}
