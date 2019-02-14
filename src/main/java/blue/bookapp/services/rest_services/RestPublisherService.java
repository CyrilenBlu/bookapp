package blue.bookapp.services.rest_services;

import blue.bookapp.api.v1.model.PublisherDTO;

import java.util.List;

public interface RestPublisherService {
    List<PublisherDTO> getPublishers();

    PublisherDTO getPublisherById(Long id);

    PublisherDTO createNewPublisher(PublisherDTO publisherDTO);

    PublisherDTO updatePublisher(Long id, PublisherDTO publisherDTO);

    String deletePublisherById(Long id);
}
