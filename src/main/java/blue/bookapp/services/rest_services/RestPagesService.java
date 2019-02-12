package blue.bookapp.services.rest_services;

import blue.bookapp.api.v1.model.PagesDTO;

import java.util.List;

public interface RestPagesService {
    List<PagesDTO> getPages();
}
