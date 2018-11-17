package blue.bookapp.services;

import blue.bookapp.commands.AuthorCommand;
import blue.bookapp.converters.AuthorCommandToAuthor;
import blue.bookapp.domain.Author;
import blue.bookapp.repositories.AuthorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;
    private AuthorCommandToAuthor authorCommandToAuthor;

    public AuthorServiceImpl(AuthorRepository authorRepository, AuthorCommandToAuthor authorCommandToAuthor) {
        this.authorRepository = authorRepository;
        this.authorCommandToAuthor = authorCommandToAuthor;
    }

    @Override
    public void addAuthor(AuthorCommand authorCommand) {
        Optional<Author> optionalAuthor = authorRepository.findById(authorCommandToAuthor.convert(authorCommand).getId());
        if (!optionalAuthor.isPresent())
        {
            throw new RuntimeException("Author already exists.");
        }

        log.debug("Saving new author.");
        authorRepository.save(authorCommandToAuthor.convert(authorCommand));
    }

    @Override
    public void removeAuthor(AuthorCommand authorCommand) {
        authorRepository.delete(authorCommandToAuthor.convert(authorCommand));
    }

    @Override
    public void removeAuthorById(Long id) {
        authorRepository.deleteById(id);
    }

    @Override
    public Author findAuthorById(Long id) {
        Optional<Author> authorOptional = authorRepository.findById(id);
        if (!authorOptional.isPresent())
        {
            throw new RuntimeException("Author with ID not found!");
        }
        Author author = authorOptional.get();

        return author;
    }
}
