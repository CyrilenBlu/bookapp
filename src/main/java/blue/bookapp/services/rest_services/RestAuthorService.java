package blue.bookapp.services.rest_services;

import blue.bookapp.api.v1.model.AuthorDTO;

import java.util.List;

public interface RestAuthorService {
    List<AuthorDTO> getAuthors();
}
