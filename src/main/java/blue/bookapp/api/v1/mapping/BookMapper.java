package blue.bookapp.api.v1.mapping;

import blue.bookapp.api.v1.model.BookDTO;
import blue.bookapp.domain.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    @Mappings({
            @Mapping(target = "genreDTO", source = "genre"),
            @Mapping(target = "authorDTO", source = "author"),
            @Mapping(target = "pagesDTOS", source = "pages"),
            @Mapping(target = "publisherDTO", source = "publisher")
    })
    BookDTO bookToBookDTO(Book book);

    @Mappings({
            @Mapping(target = "genre", source = "genreDTO"),
            @Mapping(target = "author", source = "authorDTO"),
            @Mapping(target = "pages", source = "pagesDTOS"),
            @Mapping(target = "publisher", source = "publisherDTO")
    })
    Book bookDtoToBook(BookDTO bookDTO);

//    default GenreDTO map(Genre genre)
//    {
//        switch (genre)
//        {
//            case HORROR:
//                return GenreDTO.HORROR;
//            case DRAMA:
//                return GenreDTO.DRAMA;
//            case COMEDY:
//                return GenreDTO.COMEDY;
//            case THRILLER:
//                return GenreDTO.THRILLER;
//            case SHORT_STORY:
//                return GenreDTO.SHORT_STORY;
//            case FANTASY:
//                return GenreDTO.FANTASY;
//            case MYSTERY:
//                return GenreDTO.MYSTERY;
//            case ROMANCE:
//                return GenreDTO.ROMANCE;
//            case SUPERNATURAL:
//                return GenreDTO.SUPERNATURAL;
//            case TEEN_FICTION:
//                return GenreDTO.TEEN_FICTION;
//            case CHILDREN_BOOKS:
//                return GenreDTO.CHILDREN_BOOKS;
//            default:
//                return null;
//        }
//    }
}
