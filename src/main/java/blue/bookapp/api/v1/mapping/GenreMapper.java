package blue.bookapp.api.v1.mapping;

import blue.bookapp.api.v1.model.GenreDTO;
import blue.bookapp.domain.Genre;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GenreMapper {
    GenreMapper INSTANCE = Mappers.getMapper(GenreMapper.class);

    GenreDTO genreToGenreDTO(Genre genre);

    Genre genreDtoToGenre(GenreDTO genreDTO);
}
