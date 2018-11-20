package blue.bookapp.services;

import blue.bookapp.commands.AuthorCommand;
import blue.bookapp.domain.Author;

import java.util.Set;

public interface AuthorService {
    AuthorCommand addAuthor(AuthorCommand authorCommand);

    void removeAuthor(AuthorCommand authorCommand);

    void removeById(Long id);

    AuthorCommand findCommandById(Long id);

    AuthorCommand updateAuthor(AuthorCommand authorCommand);

    Set<Author> findAll();
}
