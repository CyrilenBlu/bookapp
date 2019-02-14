package blue.bookapp.services.rest_services;

import blue.bookapp.api.v1.mapping.BookMapper;
import blue.bookapp.api.v1.mapping.PagesMapper;
import blue.bookapp.api.v1.model.BookDTO;
import blue.bookapp.api.v1.model.PagesDTO;
import blue.bookapp.domain.Book;
import blue.bookapp.domain.Pages;
import blue.bookapp.repositories.BookRepository;
import blue.bookapp.repositories.PagesRepository;
import blue.bookapp.services.PagesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class RestBookServiceImpl implements RestBookService { //todo error response implementation for null returns.

    private final BookMapper bookMapper;
    private final PagesMapper pagesMapper;
    private final BookRepository bookRepository;
    private final PagesRepository pagesRepository;
    private final PagesService pagesService;

    public RestBookServiceImpl(BookMapper bookMapper, PagesMapper pagesMapper, BookRepository bookRepository, PagesRepository pagesRepository, PagesService pagesService) {
        this.bookMapper = bookMapper;
        this.pagesMapper = pagesMapper;
        this.bookRepository = bookRepository;
        this.pagesRepository = pagesRepository;
        this.pagesService = pagesService;
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
    public PagesDTO getPageNumberByBookId(Long bookId, int pageNumber) {
        final PagesDTO[] pagesDTO = {new PagesDTO()};
        if (bookRepository.findById(bookId).isPresent())
        {
            pagesRepository.findAll().forEach(pages -> {
                if (pages.getBook().getId().equals(bookId) && pages.getPage() == pageNumber)
                {
                    pagesDTO[0] = pagesMapper.pagesToPagesDTO(pages);
                }
            });
            return pagesDTO[0];
        } else return null;
    }

    @Override
    public List<PagesDTO> getPagesByBookId(Long bookId) {
        List<PagesDTO> pagesDTOS = new ArrayList<>();
        pagesRepository.findAll().forEach(pages ->
        {
            if (pages.getBook().getId() == null)
            {
                log.error("Null bookID found for page: " + pages.getId());
            } else
            {
                if (pages.getBook().getId().equals(bookId))
                    pagesDTOS.add(pagesMapper.pagesToPagesDTO(pages));
            }
        });

        if (pagesDTOS.size() > 0)
        {
            return pagesDTOS;
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
    public PagesDTO createNewPage(Long bookId, PagesDTO pagesDTO) {
        if (bookRepository.findById(bookId).isPresent()) {
            if (pagesDTO.getId() == null) {
                Pages pages = pagesMapper.pagesDtoToPages(pagesDTO);
                Book book = bookRepository.findById(bookId).get();

                pages.setBook(book);
                book.getPages().add(pages);

                pagesRepository.save(pages);
                bookRepository.save(book);

                return pagesMapper.pagesToPagesDTO(pages);
            } else return null;
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
