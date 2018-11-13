package blue.bookapp.services;

import blue.bookapp.domain.Author;

public interface AuthorService {
    void addAuthor(Author author);

    void removeAuthor(Author author);

    void removeAuthorById(Long id);

    Author findAuthorById(Long id);

    void changeAuthorImageById(Long id, Byte[] image);
}
