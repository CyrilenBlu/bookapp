package blue.bookapp.services;

import blue.bookapp.domain.Author;
import blue.bookapp.repositories.AuthorRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void addAuthor(Author author) {
        authorRepository.save(author);
    }
}
