package blue.bookapp.api.v1.mapping;

import blue.bookapp.api.v1.model.PagesDTO;
import blue.bookapp.domain.Pages;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PagesMapper {
    PagesMapper INSTANCE = Mappers.getMapper(PagesMapper.class);

    @Mappings({
            @Mapping(target = "bookId", source = "book.id"),
            @Mapping(target = "bookTitle", source = "book.title")
    })
    PagesDTO pagesToPagesDTO(Pages pages);

    Pages pagesDtoToPages(PagesDTO pagesDTO);
}
