package blue.bookapp.services;

import blue.bookapp.domain.Publisher;
import blue.bookapp.repositories.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    @Override
    public void removePublisher(Publisher publisher) {
        publisherRepository.delete(publisher);
    }

    @Override
    public void removePublisherById(Long id) {
        publisherRepository.deleteById(id);
    }

    @Override
    public Publisher findPublisherById(Long id) {
        Optional<Publisher> optionalPublisher = publisherRepository.findById(id);
        if (!optionalPublisher.isPresent())
        {
            throw new RuntimeException("Publisher not found!");
        }
        Publisher publisher = optionalPublisher.get();

        return publisher;
    }
}
