package blue.bookapp.services;

import blue.bookapp.domain.Publisher;
import blue.bookapp.repositories.PublisherRepository;
import org.springframework.stereotype.Service;

@Service
public class PublisherServiceImpl implements PublisherService{

    private PublisherRepository publisherRepository;

    public PublisherServiceImpl(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void addPublisher(Publisher publisher) {
        publisherRepository.save(publisher);
    }
}
