package blue.bookapp.services.rest_services;

import blue.bookapp.api.v1.model.PagesDTO;

import java.util.List;

public interface RestPagesService {
    List<PagesDTO> getPages();

    PagesDTO getPagesById(Long id);

    PagesDTO patchPage(Long id, PagesDTO pagesDTO);

    String deletePagesById(Long id);
}
