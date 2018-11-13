package blue.bookapp.services;

import blue.bookapp.domain.Publisher;

public interface PublisherService {
    void addPublisher(Publisher publisher);

    void removePublisher(Publisher publisher);

    void removePublisherById(Long id);

    Publisher findPublisherById(Long id);
}
