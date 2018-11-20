package blue.bookapp.services;

import blue.bookapp.commands.PublisherCommand;
import blue.bookapp.domain.Publisher;

import java.util.Set;

public interface PublisherService {
    void addPublisher(Publisher publisher);

    void removePublisher(Publisher publisher);

    void removePublisherById(Long id);

    Publisher findPublisherById(Long id);

    Set<Publisher> findAll();

    PublisherCommand updatePublisher(PublisherCommand publisherCommand);
}
