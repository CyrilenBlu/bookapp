package blue.bookapp.services;

import blue.bookapp.commands.PublisherCommand;
import blue.bookapp.domain.Publisher;

import java.util.Set;

public interface PublisherService {
    PublisherCommand addPublisher(PublisherCommand publisherCommand);

    void removePublisher(Publisher publisher);

    void removeById(Long id);

    Publisher findPublisherById(Long id);

    Set<Publisher> findAll();

    PublisherCommand updatePublisher(PublisherCommand publisherCommand);
}
