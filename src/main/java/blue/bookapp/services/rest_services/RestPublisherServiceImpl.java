package blue.bookapp.services.rest_services;

import blue.bookapp.api.v1.mapping.PublisherMapper;
import blue.bookapp.api.v1.model.PublisherDTO;
import blue.bookapp.repositories.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestPublisherServiceImpl implements RestPublisherService {

    private final PublisherMapper publisherMapper;
    private final PublisherRepository publisherRepository;

    public RestPublisherServiceImpl(PublisherMapper publisherMapper, PublisherRepository publisherRepository) {
        this.publisherMapper = publisherMapper;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public List<PublisherDTO> getPublishers() {
        List<PublisherDTO> publisherDTOS = new ArrayList<>();
        publisherRepository.findAll().forEach(publisher ->
        {
            publisherDTOS.add(publisherMapper.publisherToPublisherDTO(publisher));
        });
        return publisherDTOS;
    }
}
