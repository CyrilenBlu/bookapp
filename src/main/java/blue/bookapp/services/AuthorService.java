package blue.bookapp.services;

import blue.bookapp.commands.AuthorCommand;
import blue.bookapp.domain.Author;

import java.util.Set;

public interface AuthorService {
    void addAuthor(AuthorCommand authorCommand);

    void removeAuthor(AuthorCommand authorCommand);

    void removeAuthorById(Long id);

    Author findAuthorById(Long id);

    Set<Author> findAll();
}
