package blue.bookapp.services.rest_services;

import blue.bookapp.api.v1.mapping.AuthorMapper;
import blue.bookapp.api.v1.model.AuthorDTO;
import blue.bookapp.domain.Author;
import blue.bookapp.repositories.AuthorRepository;
import blue.bookapp.services.AuthorService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestAuthorServiceImpl implements RestAuthorService {
    private final AuthorMapper authorMapper;
    private final AuthorRepository authorRepository;
    private final AuthorService authorService;

    public RestAuthorServiceImpl(AuthorMapper authorMapper, AuthorRepository authorRepository, AuthorService authorService) {
        this.authorMapper = authorMapper;
        this.authorRepository = authorRepository;
        this.authorService = authorService;
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

    @Override
    public AuthorDTO getAuthorById(Long id) {
        if (authorRepository.findById(id).isPresent())
        {
            return authorMapper.authorToAuthorDTO(authorRepository.findById(id).get());
        } else return null;
    }

    @Override
    public String deleteAuthorById(Long id) {
        if (authorRepository.findById(id).isPresent())
        {
            AuthorDTO authorDTO = authorMapper.authorToAuthorDTO(authorRepository.findById(id).get());
            authorService.removeById(id);
            return "Author " + id + " Deleted.\n"
                    + "Author name: " + authorDTO.getName()
                    + "\n Author age: " + authorDTO.getAge();
        } else return "Author not found.";
    }

    @Override
    public AuthorDTO createNewAuthor(AuthorDTO authorDTO) {
        AuthorDTO author = new AuthorDTO();
        if (author.getId() == null)
        {
            Author savedAuthor = authorRepository.save(authorMapper.authorDtoToAuthor(author));
            return authorMapper.authorToAuthorDTO(savedAuthor);
        } else return null;
    }
}
