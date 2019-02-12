package blue.bookapp.api.v1.mapping;

import blue.bookapp.api.v1.model.AuthorDTO;
import blue.bookapp.domain.Author;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthorMapper {
    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    AuthorDTO authorToAuthorDTO(Author author);

    Author authorDtoToAuthor(AuthorDTO authorDTO);
}
