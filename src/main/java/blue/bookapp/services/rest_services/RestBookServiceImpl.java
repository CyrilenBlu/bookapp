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

    @Override
    public BookDTO getBookById(Long id) {
        if (bookRepository.findById(id).isPresent()) {
            return bookMapper.bookToBookDTO(bookRepository.findById(id).get());
        } else return null;
    }

    @Override
    public BookDTO getBookByIdExclImg(Long id) {
        if (bookRepository.findById(id).isPresent()) {
            BookDTO bookDTO = bookMapper.bookToBookDTO(bookRepository.findById(id).get());
            bookDTO.setImage(new Byte[0]);
            return bookDTO;
        } else return null;
    }

    @Override
    public BookDTO createBook(BookDTO bookDTO) {
        BookDTO book = new BookDTO();
        if (book.getId() == null)
        {
            Book savedBook = bookRepository.save(bookMapper.bookDtoToBook(bookDTO));
            return bookMapper.bookToBookDTO(savedBook);
        } else return null;
    }

    @Override
    public BookDTO saveBook() {
        return null;
    }

    @Override
    public BookDTO patchBook() {
        return null;
    }

    @Override
    public String deleteBookById(Long id) {
        if (bookRepository.findById(id).isPresent()) {
            BookDTO bookDTO = bookMapper.bookToBookDTO(bookRepository.findById(id).get());
            bookRepository.deleteById(id);
            return "Book " + id + " deleted. \n" + "Book Deleted:\n" +
                    "Title: \t" + bookDTO.getTitle() + "\n" +
                    "Desc: \t" + bookDTO.getDescription() + "\n" +
                    "Genre: \t" + bookDTO.getGenreDTO();
        } else return "Book not found.";
    }
}
