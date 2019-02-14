package blue.bookapp.services.rest_services;

import blue.bookapp.api.v1.model.BookDTO;
import blue.bookapp.api.v1.model.PagesDTO;

import java.util.List;

public interface RestBookService {
    List<BookDTO> getBooks();

    BookDTO getBookById(Long id);

    BookDTO getBookByIdExclImg(Long id);

    PagesDTO getPageNumberByBookId(Long bookId, int pageNumber);

    List<PagesDTO> getPagesByBookId(Long bookId);

    BookDTO createBook(BookDTO bookDTO);

    PagesDTO createNewPage(Long bookId, PagesDTO pagesDTO);

    BookDTO saveBook();

    BookDTO patchBook();

    String deleteBookById(Long id);
}
