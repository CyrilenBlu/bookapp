package blue.bookapp.services.rest_services;

import blue.bookapp.api.v1.model.BookDTO;

import java.util.List;

public interface RestBookService {
    List<BookDTO> getBooks();
}
