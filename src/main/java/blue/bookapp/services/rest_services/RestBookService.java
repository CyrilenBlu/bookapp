package blue.bookapp.services.rest_services;

import blue.bookapp.api.v1.model.BookDTO;

import java.util.List;

public interface RestBookService {
    List<BookDTO> getBooks();

    BookDTO getBookById(Long id);

    BookDTO getBookByIdExclImg(Long id);

    BookDTO createBook(BookDTO bookDTO);

    BookDTO saveBook();

    BookDTO patchBook();

    String deleteBookById(Long id);
}
