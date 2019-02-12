package blue.bookapp.api.v1.mapping;

import blue.bookapp.api.v1.model.PagesDTO;
import blue.bookapp.domain.Pages;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PagesMapper {
    PagesMapper INSTANCE = Mappers.getMapper(PagesMapper.class);

    PagesDTO pagesToPagesDTO(Pages pages);

    Pages pagesDtoToPages(PagesDTO pagesDTO);
}
