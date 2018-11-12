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
}
