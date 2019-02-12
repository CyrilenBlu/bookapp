package blue.bookapp.services.rest_services;

import blue.bookapp.api.v1.mapping.BookMapper;
import blue.bookapp.api.v1.mapping.PagesMapper;
import blue.bookapp.api.v1.model.BookDTO;
import blue.bookapp.domain.Book;
import blue.bookapp.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestBookServiceImpl implements RestBookService {

    private final BookMapper bookMapper;
    private final PagesMapper pagesMapper;
    private final BookRepository bookRepository;

    public RestBookServiceImpl(BookMapper bookMapper, PagesMapper pagesMapper, BookRepository bookRepository) {
        this.bookMapper = bookMapper;
        this.pagesMapper = pagesMapper;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<BookDTO> getBooks() {
        List<BookDTO> bookDTOS = new ArrayList<>();
        bookRepository.findAll().forEach(book -> {
            Book bookToConvert = book;
            bookToConvert.setImage(new Byte[0]);
            bookDTOS.add(bookMapper.bookToBookDTO(bookToConvert));
        });
        return bookDTOS;
    }
}
