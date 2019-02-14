package blue.bookapp.services.rest_services;

import blue.bookapp.api.v1.mapping.PublisherMapper;
import blue.bookapp.api.v1.model.PublisherDTO;
import blue.bookapp.domain.Publisher;
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

    @Override
    public PublisherDTO getPublisherById(Long id) {
        if (publisherRepository.findById(id).isPresent())
        {
            return publisherMapper.publisherToPublisherDTO(publisherRepository.findById(id).get());
        } else return null;
    }

    @Override
    public PublisherDTO createNewPublisher(PublisherDTO publisherDTO) {
        PublisherDTO publisher = new PublisherDTO();
        if (publisher.getId() == null)
        {
            Publisher savedPublisher = publisherRepository.save(publisherMapper.publisherDtoToPublisher(publisher));
            return publisherMapper.publisherToPublisherDTO(savedPublisher);
        } else return null;
    }

    @Override
    public PublisherDTO updatePublisher(Long id, PublisherDTO publisherDTO) {
        return publisherRepository.findById(id)
                .map(publisher ->
                {
                    publisher = publisherMapper.publisherDtoToPublisher(publisherDTO);
                    return publisherMapper.publisherToPublisherDTO(publisherRepository.save(publisher));
                }).orElseThrow(null);
    }

    @Override
    public String deletePublisherById(Long id) {
        if (publisherRepository.findById(id).isPresent())
        {
            PublisherDTO publisherDTO = publisherMapper.publisherToPublisherDTO(publisherRepository.findById(id).get());
            publisherRepository.deleteById(id);
            return "Publisher " + id + " Deleted.\n"
                    + "Publisher name: " + publisherDTO.getName()
                    + "\n Publisher country: " + publisherDTO.getCountry();
        } else return "Publisher not found.";
    }
}
