package blue.bookapp.services;

import blue.bookapp.commands.PublisherCommand;
import blue.bookapp.domain.Publisher;

import java.util.Set;

public interface PublisherService {
    PublisherCommand addPublisher(PublisherCommand publisherCommand);

    void removeById(Long id);

    PublisherCommand findPublisherById(Long id);

    Set<Publisher> findAll();

    PublisherCommand updatePublisher(PublisherCommand publisherCommand);
}
