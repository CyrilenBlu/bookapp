package blue.bookapp.services.rest_services;

import blue.bookapp.api.v1.model.GenreDTO;

import java.util.List;

public interface RestGenreService {

    List<GenreDTO> getGenres();
}
