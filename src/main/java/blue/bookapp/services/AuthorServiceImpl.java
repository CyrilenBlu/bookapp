package blue.bookapp.services;

import blue.bookapp.commands.AuthorCommand;
import blue.bookapp.converters.AuthorCommandToAuthor;
import blue.bookapp.converters.AuthorToAuthorCommand;
import blue.bookapp.domain.Author;
import blue.bookapp.repositories.AuthorRepository;
import blue.bookapp.repositories.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;
    private AuthorCommandToAuthor authorCommandToAuthor;
    private AuthorToAuthorCommand authorToAuthorCommand;
    private BookRepository bookRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, AuthorCommandToAuthor authorCommandToAuthor, AuthorToAuthorCommand authorToAuthorCommand, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.authorCommandToAuthor = authorCommandToAuthor;
        this.authorToAuthorCommand = authorToAuthorCommand;
        this.bookRepository = bookRepository;
    }

    @Override
    public AuthorCommand addAuthor(AuthorCommand authorCommand) {
        Author author = authorCommandToAuthor.convert(authorCommand);
        if (authorCommand.getId() == null)
        {
            authorRepository.save(author);
        }
        log.debug("Saving new author.");
        return authorToAuthorCommand.convert(author);
    }

    @Override
    public void removeAuthor(AuthorCommand authorCommand) {
        authorRepository.delete(authorCommandToAuthor.convert(authorCommand));
    }

    @Override
    @Transactional
    public void removeById(Long id) {
        Optional<Author> authorOptional = authorRepository.findById(id);
        if (!authorOptional.isPresent()) {
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
        if (!authorOptional.isPresent()) {
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
        if (!authorOptional.isPresent()) {
            log.error("Author not found!");
            throw new RuntimeException("Author not found!");
        }
        authorRepository.save(authorCommandToAuthor.convert(authorCommand));

        return authorCommand;
    }
}
