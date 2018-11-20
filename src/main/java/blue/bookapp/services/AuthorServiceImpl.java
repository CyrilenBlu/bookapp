package blue.bookapp.services;

import blue.bookapp.commands.AuthorCommand;
import blue.bookapp.converters.AuthorCommandToAuthor;
import blue.bookapp.converters.AuthorToAuthorCommand;
import blue.bookapp.domain.Author;
import blue.bookapp.repositories.AuthorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;
    private AuthorCommandToAuthor authorCommandToAuthor;
    private AuthorToAuthorCommand authorToAuthorCommand;
    private BookService bookService;

    public AuthorServiceImpl(AuthorRepository authorRepository, AuthorCommandToAuthor authorCommandToAuthor, AuthorToAuthorCommand authorToAuthorCommand, BookService bookService) {
        this.authorRepository = authorRepository;
        this.authorCommandToAuthor = authorCommandToAuthor;
        this.authorToAuthorCommand = authorToAuthorCommand;
        this.bookService = bookService;
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
    public void removeById(Long id) {
        Optional<Author> authorOptional = authorRepository.findById(id);
        if (!authorOptional.isPresent())
        {
            throw new RuntimeException("Author not found!");
        }
        Author author = authorOptional.get();
        author.getBooks().forEach(book -> book.setAuthor(null));
        author.setBooks(null);
        authorRepository.deleteById(id);
    }

    @Override
    public AuthorCommand findCommandById(Long id) {
        Optional<Author> authorOptional = authorRepository.findById(id);
        if (!authorOptional.isPresent())
        {
            throw new RuntimeException("Author with ID not found!");
        }

        return authorToAuthorCommand.convert(authorOptional.get());
    }

    @Override
    public Set<Author> findAll() {
        Set<Author> authors = new HashSet<>();
        authorRepository.findAll().forEach(authors::add);
        return authors;
    }

    @Override
    public AuthorCommand updateAuthor(AuthorCommand authorCommand) {
        Optional<Author> authorOptional = authorRepository.findById(authorCommand.getId());
        if (!authorOptional.isPresent())
        {
            log.error("Author not found!");
            throw new RuntimeException("Author not found!");
        }
        authorRepository.save(authorCommandToAuthor.convert(authorCommand));

        return authorCommand;
    }
}
