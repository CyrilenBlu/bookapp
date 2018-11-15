package blue.bookapp.services;

import blue.bookapp.commands.AuthorCommand;
import blue.bookapp.domain.Author;

public interface AuthorService {
    void addAuthor(AuthorCommand authorCommand);

    void removeAuthor(AuthorCommand authorCommand);

    void removeAuthorById(Long id);

    Author findAuthorById(Long id);

    void changeAuthorImageById(Long id, Byte[] image);
}
