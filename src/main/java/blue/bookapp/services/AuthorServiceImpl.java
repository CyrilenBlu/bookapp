package blue.bookapp.services;

import blue.bookapp.domain.Author;
import blue.bookapp.repositories.AuthorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void addAuthor(Author author) {
        Optional<Author> optionalAuthor = authorRepository.findById(author.getId());
        if (!optionalAuthor.isPresent())
        {
            throw new RuntimeException("Author already exists.");
        }

        log.debug("Saving new author.");
        authorRepository.save(author);
    }

    @Override
    public void removeAuthor(Author author) {
        authorRepository.delete(author);
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

    @Override
    public void changeAuthorImageById(Long id, Byte[] image) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if (!optionalAuthor.isPresent())
        {
            throw new RuntimeException("Author not found!");
        }
        Author author = optionalAuthor.get();
        author.setImage(image);
        authorRepository.save(author);
    }
}
