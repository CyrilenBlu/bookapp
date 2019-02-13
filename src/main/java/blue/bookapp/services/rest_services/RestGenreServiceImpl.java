package blue.bookapp.services.rest_services;

import blue.bookapp.api.v1.mapping.GenreMapper;
import blue.bookapp.api.v1.model.GenreDTO;
import blue.bookapp.domain.Genre;
import blue.bookapp.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestGenreServiceImpl implements RestGenreService {

    private final BookRepository bookRepository;
    private final GenreMapper genreMapper;

    public RestGenreServiceImpl(BookRepository bookRepository, GenreMapper genreMapper) {
        this.bookRepository = bookRepository;
        this.genreMapper = genreMapper;
    }

    @Override
    public List<GenreDTO> getGenres() {
        return Arrays.
        stream(Genre.values()).
        map(genreMapper::genreToGenreDTO).
        collect(Collectors.toList());
    }
}
