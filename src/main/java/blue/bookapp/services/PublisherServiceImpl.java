package blue.bookapp.services;

import blue.bookapp.commands.PublisherCommand;
import blue.bookapp.converters.PublisherCommandToPublisher;
import blue.bookapp.domain.Publisher;
import blue.bookapp.repositories.PublisherRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class PublisherServiceImpl implements PublisherService{

    private PublisherRepository publisherRepository;
    private PublisherCommandToPublisher publisherCommandToPublisher;

    public PublisherServiceImpl(PublisherRepository publisherRepository, PublisherCommandToPublisher publisherCommandToPublisher) {
        this.publisherRepository = publisherRepository;
        this.publisherCommandToPublisher = publisherCommandToPublisher;
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
    @Transactional
    public void removePublisherById(Long id) {
        Optional<Publisher> publisherOptional = publisherRepository.findById(id);
        if (!publisherOptional.isPresent())
        {
            throw new RuntimeException("Author not found!");
        }
        Publisher publisher = publisherOptional.get();
        publisher.getBooks().forEach(book -> book.setPublisher(null));
        publisher.setBooks(null);
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

    @Override
    public Set<Publisher> findAll() {
        Set<Publisher> publishers = new HashSet<>();
        publisherRepository.findAll().forEach(publishers::add);
        return publishers;
    }

    @Override
    public PublisherCommand updatePublisher(PublisherCommand publisherCommand) {
        Optional<Publisher> authorOptional = publisherRepository.findById(publisherCommand.getId());
        if (!authorOptional.isPresent())
        {
            log.error("Author not found!");
            throw new RuntimeException("Author not found!");
        }
        publisherRepository.save(publisherCommandToPublisher.convert(publisherCommand));

        return publisherCommand;
    }
}
