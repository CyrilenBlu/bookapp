package blue.bookapp.services.rest_services;

import blue.bookapp.api.v1.mapping.AuthorMapper;
import blue.bookapp.api.v1.model.AuthorDTO;
import blue.bookapp.repositories.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestAuthorServiceImpl implements RestAuthorService {
    private final AuthorMapper authorMapper;
    private final AuthorRepository authorRepository;

    public RestAuthorServiceImpl(AuthorMapper authorMapper, AuthorRepository authorRepository) {
        this.authorMapper = authorMapper;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<AuthorDTO> getAuthors() {
        List<AuthorDTO> authorDTOS = new ArrayList<>();
        authorRepository.findAll().forEach(author ->
        {
            authorDTOS.add(authorMapper.authorToAuthorDTO(author));
        });
        return authorDTOS;
    }
}
